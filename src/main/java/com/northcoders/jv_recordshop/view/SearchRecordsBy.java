package com.northcoders.jv_recordshop.view;

import com.northcoders.jv_recordshop.model.Genre;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class SearchRecordsBy {
    public void searchRecordsBy(Scanner scanner) {

        System.out.println("How would you like to search through our catalogues?");
        System.out.println("""
                1: by album I.D.
                2: by Artist
                3: by Genre
                4: by release year
                5: by album title
                """);
        int choice = scanner.nextInt();
        scanner.nextLine();
        String searchingBy;
        String input;

        switch (choice) {
            case 1 -> {
                searchingBy = "id";
                promptUserForInput(searchingBy);
                getRecordsBy(searchingBy, scanner.nextLine());
            }
            case 2 -> {
                searchingBy = "artist";
                promptUserForInput(searchingBy);
                getRecordsBy(searchingBy, scanner.nextLine());
            }
            case 3 -> {
                searchingBy = "genre";

                boolean isValidGenre = false;
                while (!isValidGenre) {
                    promptUserForInput(searchingBy);
                    String genreString = scanner.nextLine();
                    try {
                        Genre.valueOf(genreString.toUpperCase());
                        getRecordsBy(searchingBy, genreString.toUpperCase());
                        isValidGenre = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println("""
                                the current genres we have in stock are:
                                    HIPHOP,
                                    ROCK,
                                    CLASSICAL,
                                    JAZZ,
                                    AMBIENT,
                                    EXPERIMENTAL,
                                    ELECTRONIC,
                                    POP
                                """);
                    }
                }
            }
            case 4 -> {
                searchingBy = "year";
                promptUserForInput(searchingBy);
                getRecordsBy(searchingBy, scanner.nextLine());
            }
            case 5 -> {
                searchingBy = "title";
                promptUserForInput(searchingBy);
                getRecordsBy(searchingBy, scanner.nextLine());
            }
        }
    }

    public void promptUserForInput(String searchingBy) {
        System.out.println("Please input the " + searchingBy + " of the song/s you are searching for");
    }

    public void getRecordsBy(String searchingBy, String input) {
        try {
            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://127.0.0.1:8080/api/v1/records/by?" + searchingBy + "=" + input))
                    .GET()
                    .build();

            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            new TerminalProgram().prettyPrint(response.body());
        } catch (IOException | InterruptedException e) {
            System.out.println("IO excetpion");
        }
    }
}