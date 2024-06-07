package com.northcoders.jv_recordshop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
    @GeneratedValue
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
    Boolean isInStock = false;




}
