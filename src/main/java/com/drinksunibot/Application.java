package com.drinksunibot;

import com.drinksunibot.configs.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
	
	public static void main(String[] args) {
		
		try {
			new AnnotationConfigApplicationContext(AppConfig.class);
		} catch (Exception e) {
			System.out.println("Error of Spring context initialization");
			e.printStackTrace();
		}
		
	}
	
}
