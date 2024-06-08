package com.northcoders.jv_recordshop;

import com.northcoders.jv_recordshop.view.ASCIIArt;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class 	JvRecordshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(JvRecordshopApplication.class, args);

		System.out.println("Welcome to the record shop..");
		new ASCIIArt().printRecordShop();
	}

}
