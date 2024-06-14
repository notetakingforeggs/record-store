package com.northcoders.jv_recordshop.service;
import com.northcoders.jv_recordshop.model.Album;
import com.northcoders.jv_recordshop.model.Genre;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RecordService {

    List<Album> getAllAlbums();
    List<Album> getAlbumsByArtist(String artist);
    List<Album> getAlbumsByYear(int year);
    List<Album> getAlbumsByGenre(Genre genre);
    List<Album> getAlbumsByTitle(String albumTtle);
    Album getAlbumById(Long Id);
    Album addAlbum(Album album);
    Album updateAlbum(Album album, Long id);
    void deleteAlbumById(Long Id);
    void deleteAllAlbums();
}
