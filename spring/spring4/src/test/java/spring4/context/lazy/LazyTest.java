package spring4.context.lazy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring4.ConfigurationWriter;
import spring4.MySmartInitializingSingleton;
import spring4.ApplicationContextDumper;

/**
 * <b>功能：</b>XXX<br>
 * <b>Copyright TCSL</b>
 * <li>版本| 修改日期| 作者| 变更内容</li>
 * <hr>
 * <li>v1.0| 20200914| 曾凡平| 创建</li>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Bean.class, Bean1.class, Bean2.class, LazyBean1.class, LazyBean2.class, EagerBean.class,
		MySmartInitializingSingleton.class, ConfigurationWriter.class, ApplicationContextDumper.class
})
public class LazyTest {
	@Lazy
	@Autowired
	private Bean1 bean1;

	@Autowired
	private Bean2 bean2;

	@Lazy
	@Autowired
	private LazyBean1 lazyBean1;

	@Autowired
	private LazyBean2 lazyBean2;

	@Test
	public void test() {
		// 运行结果
		// postConstruct Bean (未引用，未设置lazy)
		// postConstruct Bean1 (lazy引用，未设置lazy)
		// postConstruct Bean2 (引用，未设置lazy)
		// postConstruct EagerBean (未引用，设置@Lazy(false))
		// 单例Bean实例化完成了 MySmartInitializingSingleton
		// 未初始化 LazyBean1 (lazy引用，设置@Lazy)
		// postConstruct LazyBean2 (引用，设置@Lazy)
	}
}
