package com.northcoders.jv_recordshop.repository;

import com.northcoders.jv_recordshop.model.Album;
import com.northcoders.jv_recordshop.model.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecordItemRepository extends CrudRepository<Album, Long> {

    List<Album> findByartist(String artist);

    List<Album> findByreleaseYear(int year);

    List<Album> findBygenre(Genre genre);

    List<Album> findByalbumTitle(String albumTitle);

//      custom query
//    @Query ("SELECT *")
//    List<Album> somethingelse(String albumTitle);

}
