package com.northcoders.jv_recordshop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.northcoders.jv_recordshop.DTO.AlbumDTO;
import com.northcoders.jv_recordshop.mapper.ModelMapperConfig;
import com.northcoders.jv_recordshop.model.Album;
import com.northcoders.jv_recordshop.service.RecordService;
//import jdk.swing.interop.SwingInterOpUtils;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/records")

public class RecordController {

    @Autowired
    private RecordService recordService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<Album>> getRecords() {
        return new ResponseEntity<>(recordService.getAllAlbums(), HttpStatus.OK);
    }

    @GetMapping("/by")
    @ResponseBody
    public ResponseEntity<?> getAlbumById(@RequestParam(required = false) String id,
                                          @RequestParam(required = false) String artist,
                                          @RequestParam(required = false) String year,
                                          @RequestParam(required = false) String genre,
                                          @RequestParam(required = false) String title) {
        if (id != null) {
            try {
                return new ResponseEntity<>(recordService.getAlbumById(Long.parseLong(id)), HttpStatus.OK);
            } catch (RuntimeException e) {
                return new ResponseEntity<>("Sorry, there are no albums with that ID - try again (numbers only)", HttpStatus.BAD_REQUEST);
            }
        }

        if (artist != null) {
            return new ResponseEntity<>(recordService.getAlbumsByArtist(artist), HttpStatus.OK);
        }
        if (year != null) {
            return new ResponseEntity<>(recordService.getAlbumsByYear(Integer.parseInt(year)), HttpStatus.OK);
        }
        if (genre != null) {
            return new ResponseEntity<>(recordService.getAlbumsByGenre(genre), HttpStatus.OK);
        }
        if (title != null) {
            return new ResponseEntity<>(recordService.getAlbumsByTitle(title), HttpStatus.OK);
        } else return getRecords();
    }


    @PostMapping
    public ResponseEntity<?> addAlbum(@Valid @RequestBody AlbumDTO albumDTO) {
        Album album = convertAlbumDTOToAlbum(albumDTO);
        AlbumDTO returnDTO = convertAlbumToDTO(recordService.addAlbum(album));
        return new ResponseEntity<>(returnDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAlbum(@RequestBody Album album) throws JsonProcessingException {

        // better to use conditionals than error catching as above?

        if (recordService.updateAlbum(album) != null) {
            return new ResponseEntity<>(recordService.updateAlbum(album), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No album with that ID exists", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/by")
    public ResponseEntity<?> deleteAlbumById(@RequestParam String id) {
        try {
            String albumName = recordService.getAlbumById(Long.parseLong(id)).getAlbumTitle();
            recordService.deleteAlbumById(Long.parseLong(id));
            return new ResponseEntity<>(albumName + " has been deleted.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid ID", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteAll() {
            recordService.deleteAllAlbums();
            return new ResponseEntity<>("All albums deleted.", HttpStatus.OK);
    }


    public AlbumDTO convertAlbumToDTO(Album album) {
        return mapper.map(album, AlbumDTO.class);
    }

    public Album convertAlbumDTOToAlbum(AlbumDTO albumDTO) {
        return mapper.map(albumDTO, Album.class);
    }
}