package com.example.demo;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.CompletableFuture;

public class Consumer {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:consumer.xml");
		context.start();
		DemoService demoService = (DemoService) context.getBean("demoService"); // 获取远程服务代理
		String hello = demoService.sayHello("world"); // 执行远程方法
		System.out.println(hello); // 显示调用结果
	}

	@Test
	public void test() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:consumer.xml");
		context.start();
		AsyncService asyncService = (AsyncService) context.getBean("asyncService"); // 获取远程服务代理
		CompletableFuture<String> f = asyncService.sayHello("world");

		f.whenComplete((v, e) -> {
			if (e != null) {
				e.printStackTrace();
			} else{
				System.out.println(v);
			}
		});
	}
}
