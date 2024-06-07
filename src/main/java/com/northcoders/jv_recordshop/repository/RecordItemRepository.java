package com.northcoders.jv_recordshop.repository;

import com.northcoders.jv_recordshop.model.Album;
import org.springframework.data.repository.CrudRepository;

public interface RecordItemRepository extends CrudRepository <Album, Long> {
}
