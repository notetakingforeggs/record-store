package com.northcoders.jv_recordshop.service;

import com.northcoders.jv_recordshop.repository.RecordItemRepository;
import com.northcoders.jv_recordshop.model.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    RecordItemRepository recordItemRepository;

    @Override
    public List<Album> getAllAlbums() {
        List<Album> albumList = new ArrayList<>();
        recordItemRepository.findAll().forEach(albumList::add);
        return albumList;
    }

    @Override
    public Album getAlbumById(Long ID) {
        return recordItemRepository.findById(ID).orElseThrow(RuntimeException::new);
    }

    @Override
    public Album addAlbum(Album album) {
        return recordItemRepository.save(album);

    }

    @Override
    public void deleteAlbumById(Long Id) {
        recordItemRepository.deleteById(Id);
    }

    @Override
    public Album updateAlbum(Album album) {
        if (recordItemRepository.findById(album.getId()).isPresent()) {
            Album albumToUpdate = recordItemRepository.findById(album.getId()).get();
            albumToUpdate.setPricePence(album.getPricePence());
            albumToUpdate.setAlbumTitle(album.getAlbumTitle());
            albumToUpdate.setGenre(album.getGenre());
            albumToUpdate.setReleaseYear(album.getReleaseYear());
            albumToUpdate.setArtist(album.getArtist());

            return recordItemRepository.save(albumToUpdate);
        } else return null;
    }

    //list all albums by a given artist

    public List<Album> getAlbumsByArtist(String artist) {
        return getAllAlbums().stream()
                .filter(a -> a.getArtist().equals(artist))
                .toList();
    }

    //list all albums by a given release year
    public List<Album> getAlbumsByYear(int year) {
        return getAllAlbums().stream()
                .filter(a -> a.getReleaseYear() == year)
                .toList();
    }

    //list all albums by a given genre
    public List<Album> getAlbumsByGenre(String genre) {
        return getAllAlbums().stream()
                .filter(a -> a.getGenre().equals(Album.Genre.valueOf(genre)))
                .toList();
    }

    //get album information by album name
    public List<Album> getAlbumInfoByAlbumTitle(String albumTitle) {
        return getAllAlbums().stream()
                .filter(s -> s.getAlbumTitle().equals(albumTitle))
                .toList();
    }

}
