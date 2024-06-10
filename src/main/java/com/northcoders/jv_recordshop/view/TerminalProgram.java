package com.northcoders.jv_recordshop.view;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.northcoders.jv_recordshop.service.RecordServiceImpl;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TerminalProgram {
    public void terminalProgram() {

        boolean ended = false;
        Scanner scanner = new Scanner(System.in);

        while (!ended) {
            RecordServiceImpl recordService = new RecordServiceImpl();
            new Menu().menu();
            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> {
                        // view all records
                        HttpClient client = HttpClient.newHttpClient();
                        HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create("http://127.0.0.1:8080/api/v1/records"))
                                .GET()
                                .build();
                        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
                        prettyPrint(response.body());
                    }
                    case 2 -> {
                        new SearchRecordsBy().searchRecordsBy(scanner);
                    }
                    case 3 -> {
                        // Delete
                        System.out.println("please input the ID of the record you would like to remove");
                        int id = scanner.nextInt();
                        HttpClient client = HttpClient.newHttpClient();
                        HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create("http://127.0.0.1:8080/api/v1/records/delete/by?id=" + id))
                                .DELETE()
                                .build();

                       var response =  client.send(request, HttpResponse.BodyHandlers.ofString());
                        System.out.println(response.body());                    }
                    case 4 -> {
                        System.out.println("Please paste into the terminal the JSON representation of the completed edit you would like to execute");

                        HttpClient client = HttpClient.newHttpClient();
                        StringBuilder requestBody = new StringBuilder();
                        while (scanner.hasNextLine()) {
                            requestBody.append(scanner.nextLine());
                        }

                        HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create("http://127.0.0.1:8080/api/v1/records"))
                                .PUT(HttpRequest.BodyPublishers.ofString(requestBody.toString()))
                                .build();

                        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
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
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void prettyPrint(String string) {
        ObjectMapper objectMapper = new ObjectMapper();
        Object jsonObject = null;
        try {
            jsonObject = objectMapper.readValue(string, Object.class);
            String prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject);
            System.out.println(prettyJson);
        } catch (JsonProcessingException e) {
            System.out.println("issue parsing text as json");
        }

    }

}

