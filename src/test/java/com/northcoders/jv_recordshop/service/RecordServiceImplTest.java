package com.northcoders.jv_recordshop.service;

import com.northcoders.jv_recordshop.model.Album;
import org.junit.jupiter.api.Test;
import com.northcoders.jv_recordshop.repository.RecordItemRepository;
import com.northcoders.jv_recordshop.service.RecordServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
class RecordServiceTest {

    @Mock
    private RecordItemRepository recordItemRepository;

    @InjectMocks
    private RecordServiceImpl recordServiceImpl;


    @Test
    @DisplayName("Get all Albums - retreives record list from db")
    void testGetAllAlbums(){
        // Arrange
        List<Album> albums = List.of(new Album(), new Album(), new Album());
        when(recordItemRepository.findAll()).thenReturn(albums);

        // Act
        List<Album> results = recordServiceImpl.getAllAlbums();

        // Assert
        assertThat(results).hasSize(albums.size());
        assertThat(results).isEqualTo(albums);

    }
    @Test
    @DisplayName("Get Ablum by id")
    void getAlbumById(){
        // Arrange
        Album album = new Album();
        when(recordItemRepository.findById(1L)).thenReturn(Optional.of(album));

        // Act
        Album result = recordServiceImpl.getAlbumById(1L);

        // Assert
        assertThat(result).isEqualTo(album);


    }

}

