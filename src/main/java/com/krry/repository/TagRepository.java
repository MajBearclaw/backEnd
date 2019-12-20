package com.krry.repository;

import com.krry.entity.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TagRepository extends MongoRepository<Tag, String> {
    List<Tag> findAll();

}
