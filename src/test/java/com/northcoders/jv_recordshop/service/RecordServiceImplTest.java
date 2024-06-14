package com.northcoders.jv_recordshop.service;

import com.northcoders.jv_recordshop.model.Album;
import com.northcoders.jv_recordshop.model.Genre;
import org.junit.jupiter.api.Test;
import com.northcoders.jv_recordshop.repository.RecordItemRepository;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@DataJpaTest
class RecordServiceTest {

    @Mock
    private RecordItemRepository recordItemRepository;

    @InjectMocks
    private RecordServiceImpl recordServiceImpl;


    @Test
    @DisplayName("Get all Albums - retreives record list from db")
    void testGetAllAlbums() {
        // Arrange
        List<Album> albums = List.of(new Album(), new Album(), new Album());
        when(recordItemRepository.findAll()).thenReturn(albums);

        // Act
        List<Album> results = recordServiceImpl.getAllAlbums();

        // Assert
        assertThat(results).hasSize(albums.size());
        assertThat(results).isEqualTo(albums);
        verify(recordItemRepository, times(1)).findAll();

    }

    @Test
    @DisplayName("Get Album by id")
    void getAlbumById() {
        // Arrange
        Album album = new Album();
        when(recordItemRepository.findById(1L)).thenReturn(Optional.of(album));

        // Act
        Album result = recordServiceImpl.getAlbumById(1L);

        // Assert
        assertThat(result).isEqualTo(album);
        verify(recordItemRepository, times(1)).findById(1L);


    }

    @Test
    @DisplayName("Add Album")
    void addAlbumTest() {
        // Arrange
        Album album = new Album();
        when(recordItemRepository.save(album)).thenReturn(album);

        // Act
        Album result = recordServiceImpl.addAlbum(album);

        // Assert
        assertThat(result).isEqualTo(album);
        verify(recordItemRepository, times(1)).save(album);

    }

    @Test
    @DisplayName("Update Album")
    void updateAlbumTest() {
        // Arrange
        Album oldAlbum = new Album();
        oldAlbum.setId(1L);

        Album newAlbum = new Album();
        newAlbum.setId(1L);
        when(recordItemRepository.findById(1L)).thenReturn(Optional.of(oldAlbum));
        when(recordItemRepository.save(newAlbum)).thenReturn(newAlbum);

        // Act
        Album result = recordServiceImpl.updateAlbum(newAlbum);

        // Assert//
        assertThat(result).isEqualTo(newAlbum);
        verify(recordItemRepository, times(2)).findById(1L);
        verify(recordItemRepository, times(1)).save(newAlbum);

    }

    @Test
    @DisplayName("Delete Album")
    void deleteAlbumTest() {

        // Act
        recordServiceImpl.deleteAlbumById(1L);

        // Assert
        verify(recordItemRepository, times(1)).deleteById(1L);

    }

    @Test
    @DisplayName("Get albums by artist")
    void getAlbumsByArtistTest() {
        List<Album> testInput = new ArrayList<>();
        testInput.add(new Album(1L, "title1", "artist1", Genre.valueOf("POP"), 1999, 1000L));
        testInput.add(new Album(2L, "title2", "artist2", Genre.valueOf("ROCK"), 1999, 1500L));
        testInput.add(new Album(3L, "title3", "artist2", Genre.valueOf("JAZZ"), 1999, 1300L));

        List<Album> testOutput = new ArrayList<>();
        testOutput.add(new Album(2L, "title2", "artist2", Genre.valueOf("ROCK"), 1999, 1500L));
        testOutput.add(new Album(3L, "title3", "artist2", Genre.valueOf("JAZZ"), 1999, 1300L));

        when(recordServiceImpl.getAllAlbums()).thenReturn(testInput);
        assertThat(testOutput).isEqualTo(recordServiceImpl.getAlbumsByArtist("artist2"));

        verify(recordItemRepository, times(1)).findAll();
    }


    @Test
    @DisplayName("Get albums by year")
    void getAlbumsByYearTest() {
        List<Album> testInput = new ArrayList<>();
        testInput.add(new Album(1L, "title1", "artist1", Genre.valueOf("POP"), 1989, 1000L));
        testInput.add(new Album(2L, "title2", "artist2", Genre.valueOf("ROCK"), 1999, 1500L));
        testInput.add(new Album(3L, "title3", "artist3", Genre.valueOf("JAZZ"), 1999, 1300L));

        List<Album> testOutput = new ArrayList<>();
        testOutput.add(new Album(2L, "title2", "artist2", Genre.valueOf("ROCK"), 1999, 1500L));
        testOutput.add(new Album(3L, "title3", "artist3", Genre.valueOf("JAZZ"), 1999, 1300L));

        when(recordServiceImpl.getAllAlbums()).thenReturn(testInput);
        assertThat(testOutput).isEqualTo(recordServiceImpl.getAlbumsByYear(1999));

        verify(recordItemRepository, times(1)).findAll();
    }



    @Test
    @DisplayName("Get albums by genre ")
    void getAlbumsByGenreTest() {

        List<Album> testInput = new ArrayList<>();
        testInput.add(new Album(1L, "title1", "artist1", Genre.valueOf("POP"), 1989, 1000L));
        testInput.add(new Album(2L, "title2", "artist2", Genre.valueOf("ROCK"), 1901, 1500L));
        testInput.add(new Album(3L, "title3", "artist3", Genre.valueOf("ROCK"), 1999, 1300L));

        List<Album> testOutput = new ArrayList<>();
        testOutput.add(new Album(2L, "title2", "artist2", Genre.valueOf("ROCK"), 1901, 1500L));
        testOutput.add(new Album(3L, "title3", "artist3", Genre.valueOf("ROCK"), 1999, 1300L));

        when(recordServiceImpl.getAllAlbums()).thenReturn(testInput);
//        assertThat(testOutput).isEqualTo(recordServiceImpl.getAlbumsByGenre("ROCK"));

        verify(recordItemRepository, times(1)).findAll();

    }

    @Test
    @DisplayName(" Get album info by name")
    void getAlbumInfoByName() {
        String name = "title2";
        List<Album> testInput = new ArrayList<>();
        testInput.add(new Album(1L, "title1", "artist1", Genre.valueOf("POP"), 1989, 1000L));
        testInput.add(new Album(2L, "title2", "artist2", Genre.valueOf("ROCK"), 1901, 1500L));
        testInput.add(new Album(3L, "title2", "artist3", Genre.valueOf("ROCK"), 1999, 1300L));

        List<Album> testOutput = new ArrayList<>();
        testOutput.add(new Album(2L, "title2", "artist2", Genre.valueOf("ROCK"), 1901, 1500L));
        testOutput.add(new Album(3L, "title2", "artist3", Genre.valueOf("ROCK"), 1999, 1300L));

        when(recordServiceImpl.getAllAlbums()).thenReturn(testInput);

        assertThat(testOutput).isEqualTo(recordServiceImpl.getAlbumsByTitle(name));

        verify(recordItemRepository, times(1)).findAll();

    }


}

