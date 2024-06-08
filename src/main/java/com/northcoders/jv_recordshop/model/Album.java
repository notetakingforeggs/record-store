package com.northcoders.jv_recordshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class Album {

    public enum Genre{
        HIPHOP,
        ROCK,
        CLASSICAL,
        JAZZ,
        AMBIENT,
        EXPERIMENTAL,
        ELECTRONIC,
        POP
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false, nullable = false)
    Long id;

    @Column
    String albumTitle;

    @Column
    String artist;

    @Column
    Genre genre;

    @Column
    Year releaseYear;

    @Column
    Long pricePence = 1000L;
}
