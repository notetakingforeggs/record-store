package com.northcoders.jv_recordshop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.northcoders.jv_recordshop.model.Album;
import com.northcoders.jv_recordshop.service.RecordService;
//import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/records")

public class RecordController {

    @Autowired
    private RecordService recordService;

    @GetMapping
    public ResponseEntity<List<Album>> getRecords() {
        return new ResponseEntity<>(recordService.getAllAlbums(), HttpStatus.OK);
    }

    @GetMapping("/by")
    @ResponseBody
    public ResponseEntity<?> getAlbumById(@RequestParam String id) {
        try {
            return new ResponseEntity<>(recordService.getAlbumById(Long.parseLong(id)), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Sorry, there are no albums with that ID - try again (numbers only)", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> addAlbum(@RequestBody Album album) {
        return new ResponseEntity<>(recordService.addAlbum(album), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAlbum(@RequestBody Album album) throws JsonProcessingException {

        // better to use conditionals than error catching as above?

        if (recordService.updateAlbum(album) != null) {
            System.out.println(new ObjectMapper().writeValueAsString("Album is " + album));
            return new ResponseEntity<>(recordService.updateAlbum(album), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No album with that ID exists", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/by")
    public ResponseEntity<?> deleteAlbumById(@RequestParam String id){
        try{
            String albumName = recordService.getAlbumById(Long.parseLong(id)).getAlbumTitle();
            recordService.deleteAlbumById(Long.parseLong(id));
            return new ResponseEntity<>(albumName + " has been deleted.", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Invalid ID", HttpStatus.BAD_REQUEST);
        }
    }
}