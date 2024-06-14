package com.northcoders.jv_recordshop.service;

import com.northcoders.jv_recordshop.model.Genre;

public class GenreConverter {

    public Genre genreConverter(String genre) {
        try {
            return Genre.valueOf(genre);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

}
