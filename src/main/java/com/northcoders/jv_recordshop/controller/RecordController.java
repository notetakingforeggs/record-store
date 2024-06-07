package com.northcoders.jv_recordshop.controller;

import com.northcoders.jv_recordshop.model.Album;
import com.northcoders.jv_recordshop.service.RecordService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
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

    @GetMapping
    public ResponseEntity<List<Album>> getRecords() {
        return new ResponseEntity<>(recordService.getAllAlbums(), HttpStatus.OK);
    }

    @GetMapping("/by")
    @ResponseBody
    public ResponseEntity<?> getAlbumById(@RequestParam String id){
        try {
            return new ResponseEntity<>(recordService.getAlbumById(Long.parseLong(id)), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<>("Sorry, there are no albums with that ID - try again (numbers only)", HttpStatus.BAD_REQUEST);
        }
    }
}