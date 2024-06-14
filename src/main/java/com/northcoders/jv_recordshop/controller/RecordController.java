package com.northcoders.jv_recordshop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.northcoders.jv_recordshop.DTO.AlbumDTO;
import com.northcoders.jv_recordshop.model.Album;
import com.northcoders.jv_recordshop.model.Genre;
import com.northcoders.jv_recordshop.service.RecordService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/v1/records")

public class RecordController {

    @Autowired
    private RecordService recordService;

    @Autowired
    private ModelMapper modelMapper = new ModelMapper();

    @GetMapping
    public ResponseEntity<List<Album>> getRecords() {
        return new ResponseEntity<>(recordService.getAllAlbums(), HttpStatus.OK);
    }

    @GetMapping("/by")
    @ResponseBody
    public ResponseEntity<?> getAlbumById(@RequestParam(required = false) Long id,
                                          @RequestParam(required = false) String artist,
                                          @RequestParam(required = false) String year,
                                          @RequestParam(required = false) Genre genre,
                                          @RequestParam(required = false) String title) {
        if (id != null) {

            return new ResponseEntity<>(recordService.getAlbumById(id), HttpStatus.OK);
            }

        if (artist != null) {
            if (recordService.getAlbumsByArtist(artist).isEmpty()) {
                return new ResponseEntity<>(new ArrayList<>(List.of("No albums by " + artist)), HttpStatus.BAD_REQUEST);
            } else return new ResponseEntity<>(recordService.getAlbumsByArtist(artist), HttpStatus.OK);
        }
        if (year != null) {
            if (recordService.getAlbumsByYear(Integer.parseInt(year)).isEmpty()) {
                return new ResponseEntity<>(new ArrayList<>(List.of("No albums from the year" + year)), HttpStatus.BAD_REQUEST);
            } else return new ResponseEntity<>(recordService.getAlbumsByYear(Integer.parseInt(year)), HttpStatus.OK);
        }
        if (genre != null) {
            if (recordService.getAlbumsByGenre(genre).isEmpty()) {
                return new ResponseEntity<>(new ArrayList<>(List.of("No albums in genre: " + genre)), HttpStatus.BAD_REQUEST);
            } else return new ResponseEntity<>(recordService.getAlbumsByGenre(genre), HttpStatus.OK);

        }
        if (title != null) {
            if (recordService.getAlbumsByTitle(title).isEmpty()) {
                return new ResponseEntity<>(new ArrayList<>(List.of("No albums titled " + title)), HttpStatus.BAD_REQUEST);
            } else return new ResponseEntity<>(recordService.getAlbumsByTitle(title), HttpStatus.OK);
        } else return getRecords();
    }


    @PostMapping
    public ResponseEntity<?> addAlbum(@Valid @RequestBody AlbumDTO albumDTO) {
        System.out.println("LOOOOO");
        Album album = convertAlbumDTOToAlbum(albumDTO);
        AlbumDTO returnDTO = convertAlbumToDTO(recordService.addAlbum(album));
        return new ResponseEntity<>(returnDTO, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAlbum(@RequestBody AlbumDTO albumDTO) throws JsonProcessingException {
        Album album = convertAlbumDTOToAlbum(albumDTO);
        if (recordService.updateAlbum(album, album.getId()) != null) {
            Long id = album.getId();
            AlbumDTO returnDTO = convertAlbumToDTO(recordService.updateAlbum(album, id));
            return new ResponseEntity<>(returnDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No album with that ID exists", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/by")
    public ResponseEntity<?> deleteAlbumById(@RequestParam Long id) {
        try {
            String albumName = recordService.getAlbumById(id).getAlbumTitle();
            recordService.deleteAlbumById((id));
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

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("everyting good,eeveryting nice");
    }

    public AlbumDTO convertAlbumToDTO(Album album) {
        return modelMapper.map(album, AlbumDTO.class);
    }

    public Album convertAlbumDTOToAlbum(AlbumDTO albumDTO) {
        return modelMapper.map(albumDTO, Album.class);
    }


}