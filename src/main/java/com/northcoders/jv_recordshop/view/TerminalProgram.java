package com.northcoders.jv_recordshop.view;

import com.northcoders.jv_recordshop.controller.RecordController;
import nonapi.io.github.classgraph.json.JSONUtils;

import java.awt.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TerminalProgram {
    public void terminalProgram() {

        boolean ended = false;
        Scanner scanner = new Scanner(System.in);

        while (!ended) {
             RecordController recordController = new RecordController();
            new Menu().menu();
            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> {
                        // view all records
                        recordController.getRecords().getBody().stream().forEach(System.out::println);
                    }
                    case 2 -> {
                    new SearchRecordsBy().searchRecordsBy(scanner, recordController);
                    }
                    case 3 -> {
                        System.out.println("Please input the ID of the record to delete");
                        String id = scanner.nextLine();
                        recordController.deleteAlbumById(id);
                    }
                    case 4 -> {
                        System.out.println("Please paste into the terminal the JSON representation of the completed edit you would like to execute");

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

