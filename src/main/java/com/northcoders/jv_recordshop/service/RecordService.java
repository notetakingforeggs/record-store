package com.northcoders.jv_recordshop.service;
import com.northcoders.jv_recordshop.model.Album;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RecordService {

    List<Album> getAllAlbums();
    List<Album> getAlbumsByArtist(String artist);
    List<Album> getAlbumsByYear(int year);
    List<Album> getAlbumsByGenre(String genre);
    List<Album> getAlbumsByTitle(String title);
    Album getAlbumById(String Id);
    Album addAlbum(Album album);
    Album updateAlbum(Album album);
    void deleteAlbumById(Long Id);
    void deleteAllAlbums();
}
