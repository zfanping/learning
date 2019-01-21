package com.example.demo.lazy;

import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBeanTest extends TestCase {
    public void test() throws Exception {
        TestBean b = new TestBean();
        b.afterPropertiesSet();
    }

    public void testLoadCtx() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/lazyContext.xml");
        System.out.println("after load ctx");
        System.out.println("before invoke ");
        TestBean eager = ctx.getBean("eager", TestBean.class);

        System.out.println("before eager.getOther()");
        TestBean lazy = eager.getOther();

        System.out.println("before lazy.getOther()");
        TestBean lazy2 = lazy.getOther();

        System.out.println("before lazy2.getOther");
        lazy2.getOther();


    }

    public void testInject() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/lazyContext.xml");
        System.out.println("after load ctx");
        TestInjectWithResource testInjectWithResource = ctx.getBean(TestInjectWithResource.class);
        TestInjectWithAutowired testInjectWithAutowired = ctx.getBean(TestInjectWithAutowired.class);
        System.out.println("after get bean from ctx");
    }

    public void test3() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/lazyContext.xml");
        System.out.println("after load ctx");
        Object obj = ctx.getBean("service");

        TestBean2 b = (TestBean2) obj;

    }
}