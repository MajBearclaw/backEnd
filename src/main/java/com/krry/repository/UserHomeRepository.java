package com.krry.repository;

import com.krry.entity.UserHome;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserHomeRepository extends MongoRepository<UserHome, String> {
    public UserHome findByUsername(String username);

}
