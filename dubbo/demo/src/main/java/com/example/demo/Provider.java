package com.example.demo;


import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Prvoder {
	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:provider.xml");
		context.start();
		System.in.read();

	}
}
