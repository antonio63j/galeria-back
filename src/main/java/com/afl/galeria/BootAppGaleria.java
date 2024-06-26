package com.afl.galeria;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.afl.galeria.controllers.UsuarioController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class BootAppGaleria {
	private Logger log = LoggerFactory.getLogger(UsuarioController.class);
	
	public static void main(String[] args) {

		SpringApplication.run(BootAppGaleria.class, args);
	}
	
//    @Bean
//	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//		return args -> {
//
//			System.out.println("Let's inspect the beans provided by Spring Boot:");
//			String[] beanNames = ctx.getBeanDefinitionNames();
//			Arrays.sort(beanNames);
//			for (String beanName : beanNames) {
//				log.info(beanName +  " ------ tipo: " + ctx.getType(beanName));
//			}
//
//		};
//	}

}
