package com.northcoders.jv_recordshop.view;

import com.northcoders.jv_recordshop.controller.RecordController;
import com.northcoders.jv_recordshop.model.Album;
import nonapi.io.github.classgraph.json.JSONUtils;

import java.util.List;
import java.util.Scanner;

public class SearchRecordsBy {
    public void searchRecordsBy(Scanner scanner, RecordController recordController) {

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

        switch (choice) {
            case 1 -> {
                getRecordById(scanner, recordController);
            }
            case 2 -> {
                getRecordsByArtist(scanner, recordController);
            }
            case 3 -> {
                getRecordsByGenre(scanner, recordController);
            }
            case 4 -> {
                getRecordsByReleaseYear(scanner, recordController);
            }
            case 5 -> {
                getRecordsByTitle(scanner, recordController);
            }

        }
    }

    public void getRecordById(Scanner scanner, RecordController recordController) {
        System.out.println("Please enter the ID of the record you would like to retrieve");
        String id = scanner.nextLine();
        System.out.println(recordController.getAlbumById(id, null, null, null, null).getBody());
    }

    public void getRecordsByArtist(Scanner scanner, RecordController recordController) {
        System.out.println("Please enter the name of the artist you would like to search by");
        String artist = scanner.nextLine();
        List<Album> recordsByArtist = (List<Album>) recordController.getAlbumById(null, artist, null, null, null).getBody();
        recordsByArtist.stream().forEach(System.out::println);
    }

    public void getRecordsByGenre(Scanner scanner, RecordController recordController) {
        System.out.println("Please enter the name of the genre you would like to search by");
        String genre = scanner.nextLine();
        try {
            List<Album> recordsByArtist = (List<Album>) recordController.getAlbumById(null, null, null, genre, null).getBody();
            recordsByArtist.stream().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    public void getRecordsByReleaseYear(Scanner scanner, RecordController recordController) {
        System.out.println("Please enter the name of the genre you would like to search by");
        String releaseYear = scanner.nextLine();
        try {
            List<Album> recordsByArtist = (List<Album>) recordController.getAlbumById(null, null, releaseYear, null, null).getBody();
            recordsByArtist.stream().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("error");
        }
    }

    public void getRecordsByTitle(Scanner scanner, RecordController recordController) {
        System.out.println("Please enter the name of the title you would like to search by");
        String title = scanner.nextLine();
        try {
            List<Album> recordsByArtist = (List<Album>) recordController.getAlbumById(null, null, null, null, title).getBody();
            recordsByArtist.stream().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("error");
        }
    }
}
