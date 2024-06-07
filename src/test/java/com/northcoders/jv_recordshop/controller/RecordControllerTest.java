package com.northcoders.jv_recordshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.northcoders.jv_recordshop.model.Album;
import com.northcoders.jv_recordshop.service.RecordServiceImpl;
import org.aspectj.lang.annotation.Before;
import org.hibernate.grammars.hql.HqlParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

// this creates a mock of the whole spring api, allowing us to send http request to the controller, without starting a full http server
@AutoConfigureMockMvc
@SpringBootTest
class RecordControllerTest {

    // mocking the recordservice class (where all the logic happens)
    @Mock
    private RecordServiceImpl mockRecordService;

    // injecting it into the controller class ( because here we are testing the controller - how it handles the requests etc, not the logic)
    @InjectMocks
    private RecordController recordController;

    // this just makes the mock mvc available within the test class? like doing blah equals new mock mvc?
    @Autowired
    private MockMvc mockMvcController;

    private ObjectMapper mapper;

    @BeforeEach
    public void setUp() {
        mockMvcController = MockMvcBuilders.standaloneSetup(recordController).build();
        mapper = new ObjectMapper();
    }

    @Test
    @DisplayName(" GET / albums - Controller gets Records (albums?)")
    void getRecords() throws Exception {
        // Arrange
        List<Album> albumList = new ArrayList<>();
        albumList.add(new Album(1L, "title1", "artist1", Album.Genre.valueOf("POP"), Year.of(1999)));
        albumList.add(new Album(2L, "title2", "artist2", Album.Genre.valueOf("ROCK"), Year.of(1999)));
        albumList.add(new Album(3L, "title3", "artist3", Album.Genre.valueOf("JAZZ"), Year.of(1999)));

        when(mockRecordService.getAllAlbums()).thenReturn(albumList);
        // horrific misstep of not noticing title is albumtitle debugging - keeping for future reference
        MvcResult result = this.mockMvcController.perform(MockMvcRequestBuilders.get("/api/v1/records")).andReturn();
        System.out.println(result.getResponse().getContentAsString());
        // Act
        ResultActions resultActions = this.mockMvcController.perform(MockMvcRequestBuilders.get("/api/v1/records"));

        // Assert
        resultActions.andExpectAll(
                (MockMvcResultMatchers.status().isOk()),
                (MockMvcResultMatchers.jsonPath("$[0].id").value(1L)),
                (MockMvcResultMatchers.jsonPath("$[1].albumTitle").value("title2")),
                (MockMvcResultMatchers.jsonPath("$[2].artist").value("artist3"))
        );
    }
    @Test
    @DisplayName("Get Album by ID test")
    void testGetAlbumsById() throws Exception {
        // Arrange
        Album album =  new Album(1L, "title1", "artist1", Album.Genre.valueOf("POP"), Year.of(1999));
        when(mockRecordService.getAlbumById(1L)).thenReturn(album);

        // Act
        ResultActions resultActions = this.mockMvcController.perform((MockMvcRequestBuilders.get("/api/v1/records/by?id=1")));

        MvcResult result = this.mockMvcController.perform(MockMvcRequestBuilders.get("/api/v1/records/by?id=1")).andReturn();
        System.out.println(result.getResponse().getContentAsString());

        // Assert

        resultActions.andExpectAll(
                MockMvcResultMatchers.status().isOk(),
                MockMvcResultMatchers.jsonPath("$.id").value(1L),
                MockMvcResultMatchers.jsonPath("$.albumTitle").value("title1"),
                MockMvcResultMatchers.jsonPath("$.genre").value("POP")
        );



    }

}