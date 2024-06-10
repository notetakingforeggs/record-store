package com.northcoders.jv_recordshop;

import com.northcoders.jv_recordshop.view.ASCIIArt;
import com.northcoders.jv_recordshop.view.TerminalProgram;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO enum validation, and also reconfiguring tests to pass with DTO change

@SpringBootApplication
public class JvRecordshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(JvRecordshopApplication.class, args);

        System.out.println("Welcome to the record shop..");
        new ASCIIArt().printRecordShop();
        new TerminalProgram().terminalProgram();
    }

}
