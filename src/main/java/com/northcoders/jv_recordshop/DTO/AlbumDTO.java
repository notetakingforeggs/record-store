package com.northcoders.jv_recordshop.DTO;

import com.northcoders.jv_recordshop.model.Genre;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AlbumDTO {

        Long id;

        @NotBlank(message = "Album must have a Title.")
        String albumTitle;

        @NotBlank(message = "Album must have an artist")
        String artist;


        Genre genre;

        @NotNull(message = "album must have a realease year")
        @Max(value = 2024, message = "Album cannot be released in the future")
        @Min(value = 1880, message = "The earliest album was released in 1880")
        int releaseYear;


        @Min(value = 0, message = "price cannot be negative")
        Long pricePence;
    }

