package com.mainapp;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.config.MyConfiguration;

public class Launch {
	public static void main(String[] args) {

		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MyConfiguration.class);
		Crud crud = (Crud) ac.getBean("crud");
		crud.readAllPojoBased();

	}
}
