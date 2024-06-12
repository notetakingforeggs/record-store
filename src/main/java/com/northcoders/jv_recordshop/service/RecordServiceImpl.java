package com.northcoders.jv_recordshop.service;

import com.northcoders.jv_recordshop.model.Genre;
import com.northcoders.jv_recordshop.repository.RecordItemRepository;
import com.northcoders.jv_recordshop.model.Album;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
    public Album getAlbumById(String ID) {
    try {
        Long id = Long.parseLong(ID);
        return recordItemRepository.findById(id).get();

    }catch (IllegalArgumentException | NoSuchElementException e){
        throw new NumberFormatException ("Sorry, there is are no albums with that ID - try again (numbers only)");
    }
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
@Override
    public List<Album> getAlbumsByArtist(String artist) {
        return getAllAlbums().stream()
                .filter(a -> a.getArtist().equals(artist))
                .toList();
    }

    //list all albums by a given release year
    @Override
    public List<Album> getAlbumsByYear(int year) {
        return getAllAlbums().stream()
                .filter(a -> a.getReleaseYear() == year)
                .toList();
    }

    //list all albums by a given genre
    @Override
    public List<Album> getAlbumsByGenre(String genre) {
        return getAllAlbums().stream()
                .filter(a -> a.getGenre().equals(Genre.valueOf(genre)))
                .toList();
    }

    //get album information by album name
    @Override
    public List<Album> getAlbumsByTitle(String title) {
        return getAllAlbums().stream()
                .filter(s -> s.getAlbumTitle().equals(title))
                .toList();
    }

    @Override
    public void deleteAllAlbums(){
        recordItemRepository.deleteAll();
    }

}
