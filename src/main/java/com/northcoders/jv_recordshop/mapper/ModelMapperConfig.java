package com.northcoders.jv_recordshop.mapper;
import com.northcoders.jv_recordshop.DTO.AlbumDTO;
import com.northcoders.jv_recordshop.model.Album;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }



}