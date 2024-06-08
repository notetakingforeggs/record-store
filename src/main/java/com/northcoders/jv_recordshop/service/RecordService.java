package com.northcoders.jv_recordshop.service;
import com.northcoders.jv_recordshop.model.Album;
import java.util.List;

public interface RecordService {

    List<Album> getAllAlbums();
    Album getAlbumById(Long Id);
    Album addAlbum(Album album);
    Album updateAlbum(Album album);
    void deleteAlbumById(Long Id);

}
