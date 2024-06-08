package com.northcoders.jv_recordshop.view;

import nonapi.io.github.classgraph.json.JSONUtils;

import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TerminalProgram {
    public void terminalProgram() {

        boolean ended = false;
        Scanner scanner = new Scanner(System.in);

        while (!ended) {
            new Menu().menu();
            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> {


                    }
                    case 2 -> {

                    }
                    case 3 -> {

                    }
                    case 4 -> {

                    }

                    case 5 -> {

                    }

                    case 6 -> {
                        // Quit
                        System.out.println("Exiting program. Goodbye and thank-you for your time.");
                        scanner.close();
                        new ASCIIArt().printRecordShop();
                        ended = true;
                    }
                    default -> System.out.println("Please enter one of the options as its corresponding integer value");
                }

            } catch (InputMismatchException e) {
                System.out.println("Please enter one of the options as its corresponding integer value.");
                scanner.nextLine();
            }
        }
    }
}

