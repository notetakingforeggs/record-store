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
    public Album getAlbumById(Long id) {
        return recordItemRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @Override
    public Album addAlbum(Album album) {
        return recordItemRepository.save(album);

    }

    @Override
    public Album updateAlbum(Album album) {
        if( recordItemRepository.findById(album.getId()).isPresent()){
            recordItemRepository.deleteById(album.getId());
            return recordItemRepository.save(album);
            //return recordItemRepository.findById(album.getId()).get();
        }else return null;
    }
}
