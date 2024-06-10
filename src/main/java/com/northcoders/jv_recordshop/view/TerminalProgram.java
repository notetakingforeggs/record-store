package com.northcoders.jv_recordshop.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.northcoders.jv_recordshop.controller.RecordController;
import nonapi.io.github.classgraph.json.JSONUtils;
import org.apache.catalina.mapper.Mapper;

import java.awt.*;
import java.net.URI;
import java.net.http.HttpRequest;
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
                        StringBuilder requestBody = new StringBuilder();
                        while (scanner.hasNextLine()) {
                            requestBody.append(scanner.nextLine());
                        }

                        HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create("http://127.0.0.1:8080/api/v1/records"))
                                .PUT(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                                .build();
                    }
                    case 5 -> {
                        System.out.println("Please paste into the terminal the JSON representation of the record you would like to add to the catalogue. type 'end' to finish ");

                        StringBuilder requestBody = new StringBuilder();
                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            if (line.toUpperCase().equals("END")) {
                                break;
                            } else {
                                requestBody.append(line);
                            }

                        }
                        System.out.println(requestBody.toString());

                        System.out.println("out of loop");
                        HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create("http://127.0.0.1:8080/api/v1/records"))
                                .POST(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                                .build();

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

