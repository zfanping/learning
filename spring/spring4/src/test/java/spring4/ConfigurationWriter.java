package spring4;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractApplicationContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * <b>功能：</b>XXX<br>
 * <b>Copyright TCSL</b>
 * <li>版本| 修改日期| 作者| 变更内容</li>
 * <hr>
 * <li>v1.0| 20201117| 曾凡平| 创建</li>
 */

public class ConfigurationWriter implements ApplicationContextAware, SmartInitializingSingleton {
	public ArrayList<String> beanDefinitions = new ArrayList<String>();
	private ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.context = applicationContext;
	}

	@Override
	public void afterSingletonsInstantiated() {
		System.out.println("afterSingletonsInstantiated");
		// the context loaded with old fashioned way (classpath scanning)
		String[] tab = context.getBeanDefinitionNames();
		ConfigurableListableBeanFactory factory = ((AbstractApplicationContext) context).getBeanFactory();
		for (String beanName : tab) {
			BeanDefinition bd = factory.getBeanDefinition(beanName);
			if (bd instanceof AnnotatedBeanDefinition) {
				Class clazz = context.getType(beanName);
				String scope = context.isPrototype(beanName) ? "prototype" : "singleton";
				String s = "<bean id=\"" + beanName + "\" class=\"" + clazz.getName() + "\" scope=\"" + scope + "\"/>";
				beanDefinitions.add(s);
			}
		}
		// Collections.addAll(beanDefinitions, tab);
		try {
			this.generateConfiguration();
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("restriction")
	public void generateConfiguration() throws FileNotFoundException {
		File xmlConfig = new File("D:\\springBoost.xml");
		PrintWriter printer = new PrintWriter(xmlConfig);

		generateHeader(printer);

		generateCorpse(printer);

		generateTail(printer);

		printer.checkError();

	}

	@SuppressWarnings("restriction")
	private void generateCorpse(PrintWriter printer) {

		for (String beanPath : beanDefinitions) {
			printer.println(beanPath);
		}

	}

	@SuppressWarnings("restriction")
	private void generateHeader(PrintWriter printer) {
		printer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		printer.println("<beans xmlns=\"http://www.springframework.org/schema/beans\"");
		printer.println("xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
		printer.println("xmlns:context=\"http://www.springframework.org/schema/context\"");
		printer.println("xsi:schemaLocation=\"");
		printer.println("http://www.springframework.org/schema/mvc");
		printer.println("http://www.springframework.org/schema/mvc/spring-mvc-3.0.xsd");
		printer.println("http://www.springframework.org/schema/beans");
		printer.println("http://www.springframework.org/schema/beans/spring-beans-3.0.xsd");
		printer.println("http://www.springframework.org/schema/context");
		printer.println("http://www.springframework.org/schema/context/spring-context-3.0.xsd\"");
		printer.println("default-lazy-init=\"true\">");
	}

	@SuppressWarnings("restriction")
	private void generateTail(PrintWriter printer) {
		// printer.println("<bean class=\"com.xxx.frmwrk.spring.processors.xxxBeanFactoryPostProcessor\"/>");
		printer.println("<bean class=\"com.xxx.frmwrk.spring.processors.xxxPostProcessor\"/>");
		printer.println("</beans>");
	}


}
