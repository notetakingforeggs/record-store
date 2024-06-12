package com.northcoders.jv_recordshop;

import com.northcoders.jv_recordshop.view.ASCIIArt;
import com.northcoders.jv_recordshop.view.TerminalProgram;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO enum validation on post and put.
// TODO Singles as well as albums
// TODO Separate artist out into distinct table and join
// TODO other joins maybe?
// TODO auth

@SpringBootApplication
public class JvRecordshopApplication {

    public static void main(String[] args) {
        SpringApplication.run(JvRecordshopApplication.class, args);

//        System.out.println("Welcome to the record shop..");
//        new ASCIIArt().printRecordShop();
//        new TerminalProgram().terminalProgram();
    }

}
