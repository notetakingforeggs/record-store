package com.northcoders.jv_recordshop.service;

import com.northcoders.jv_recordshop.model.Genre;
import com.northcoders.jv_recordshop.repository.RecordItemRepository;
import com.northcoders.jv_recordshop.model.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

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
    public Album addAlbum(Album album) {
        return recordItemRepository.save(album);

    }

    @CacheEvict("albums")
    @Override
    public void deleteAlbumById(Long id) {
        recordItemRepository.deleteById(id);
    }

    @CacheEvict(value = "albums", key = "#id")
    @Override
    public Album updateAlbum(Album album, Long id) {
        if (recordItemRepository.findById(id).isPresent()) {
            Album albumToUpdate = recordItemRepository.findById(id).get();
            albumToUpdate.setPricePence(album.getPricePence());
            albumToUpdate.setAlbumTitle(album.getAlbumTitle());
            albumToUpdate.setGenre(album.getGenre());
            albumToUpdate.setReleaseYear(album.getReleaseYear());
            albumToUpdate.setArtist(album.getArtist());
            return recordItemRepository.save(albumToUpdate);

        } else return null;
    }


    @Override
    @Cacheable(value = "albums", key = "#id")
    public Album getAlbumById(Long id) {
        try {
            Album returnAlbum = recordItemRepository.findById(id).get();
            System.out.println("DB call Executed");
            return returnAlbum;

        } catch (IllegalArgumentException | NoSuchElementException e) {
            throw new NumberFormatException("Sorry, there is are no albums with that ID - try again (numbers only)");
        }
    }

    //list all albums by a given artist
    @Override
    public List<Album> getAlbumsByArtist(String artist) {
        return recordItemRepository.findByartist(artist);
    }

    //list all albums by a given release year
    @Override
    public List<Album> getAlbumsByYear(int year) {
        return recordItemRepository.findByreleaseYear(year);
    }

    //list all albums by a given genre
    @Override
    public List<Album> getAlbumsByGenre(Genre genre) {
        return recordItemRepository.findBygenre(genre);

    }

    //get album information by album name
    @Override
    public List<Album> getAlbumsByTitle(String title) {
        return recordItemRepository.findByalbumTitle(title);
    }

    @CacheEvict("albums")
    @Override
    public void deleteAllAlbums() {
        recordItemRepository.deleteAll();
    }

}
