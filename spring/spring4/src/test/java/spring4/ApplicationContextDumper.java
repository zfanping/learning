package spring4;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * <b>功能：</b>XXX<br>
 * <b>Copyright TCSL</b>
 * <li>版本| 修改日期| 作者| 变更内容</li>
 * <hr>
 * <li>v1.0| 20201117| 曾凡平| 创建</li>
 */
public class ApplicationContextDumper implements ApplicationContextAware, SmartInitializingSingleton {
	private Map<String, Integer> beanReferenceCounter = new HashMap<String, Integer>();
	private StringBuilder outputMessage;
	private ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		this.context = context;
	}

	private void dumpBeansWithoutReference() {
		outputMessage.append("Beans without reference:\n");
		for (String bean : beanReferenceCounter.keySet()) {
			if (beanReferenceCounter.get(bean) == 0) {
				outputMessage.append("  ").append(bean).append('\n');
			}
		}
	}

	private void initBeanReferenceIfNotExist(String beanName) {
		Integer count = beanReferenceCounter.get(beanName);
		if (count == null) {
			beanReferenceCounter.put(beanName, 0);
		}
	}

	private void increaseBeanReference(String beanName) {
		Integer count = beanReferenceCounter.get(beanName);
		if (count == null) {
			count = new Integer(0);
		}
		beanReferenceCounter.put(beanName, ++count);
	}

	private void dumpApplicationContext(ApplicationContext context) {
		// Read context id isn't available. https://jira.springsource.org/browse/SPR-8816
		String appContextInfo = String.format("ApplicationContext %s : %s", context.getId(), context.getClass()
				.getName());
		ApplicationContext parent = context.getParent();
		if (parent != null) {
			appContextInfo += String.format(" -> %s", parent.getId());
		}
		outputMessage.append(appContextInfo).append('\n');

		ConfigurableListableBeanFactory factory = ((AbstractApplicationContext) context).getBeanFactory();
		for (String beanName : factory.getBeanDefinitionNames()) {
			if (factory.getBeanDefinition(beanName).isAbstract()) {
				continue;
			}
			initBeanReferenceIfNotExist(beanName);
			Object bean = factory.getBean(beanName);
			outputMessage.append(String.format("  %s : %s\n", beanName, bean.getClass().getName()));
			for (String dependency : factory.getDependenciesForBean(beanName)) {
				outputMessage.append(String.format("    -> %s\n", dependency));
				increaseBeanReference(dependency);
			}
		}

		if (parent != null) {
			outputMessage.append("Parent:\n");
			dumpApplicationContext(parent);
		}
	}

	@Override
	public void afterSingletonsInstantiated() {
		outputMessage = new StringBuilder();
		beanReferenceCounter.clear();
		outputMessage.append("--- ApplicationContextDumper begin ---\n");
		dumpApplicationContext(context);
		dumpBeansWithoutReference();
		outputMessage.append("--- ApplicationContextDumper end ---\n");
		System.out.print(outputMessage);
	}
}
