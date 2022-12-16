package spring4.context;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

/**
 * <b>功能：</b>XXX<br>
 * <b>Copyright TCSL</b>
 * <li>版本| 修改日期| 作者| 变更内容</li>
 * <hr>
 * <li>v1.0| 20200916| 曾凡平| 创建</li>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:test.xml"})
public class PropertyPlaceHolderTest {
	@Value("${a}")
	private String a;
	@Value("${b}")
	private String b;

	@Resource
	private PropertyPlaceHolderBean bean;

	@Test
	public void test() {
		System.out.println("bean.getA() = " + bean.getA());
		System.out.println("bean.getB() = " + bean.getB());
		
		assertEquals("2", bean.getA());
		assertEquals("22c", bean.getB());

		assertEquals("2", a);
		assertEquals("22c", b);


	}
}

