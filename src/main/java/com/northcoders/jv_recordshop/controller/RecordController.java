package com.northcoders.jv_recordshop.controller;

import com.northcoders.jv_recordshop.service.RecordService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<Record>> getRecords() {
        // this just a standin for method call
        List<Record> recordsList = new ArrayList<>();

        return new ResponseEntity<>(recordsList, HttpStatus.OK);

    }
}