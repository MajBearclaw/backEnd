package com.krry.repository;

import com.krry.entity.headPortrait;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface headPortraitRepository extends MongoRepository<headPortrait, String> {
    public headPortrait findByUsername(String username);
    //public headPortrait

}
