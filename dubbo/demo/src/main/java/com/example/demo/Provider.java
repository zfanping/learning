package com.example.demo;


import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Provider {
	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:provider.xml");
		context.start();
		System.out.println("enter anything ...");
		System.in.read();

	}
}
