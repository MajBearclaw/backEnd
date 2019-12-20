package com.krry.repository;

import com.krry.entity.User;
import com.krry.entity.UsrAuthor;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UsrAuthorRepository extends MongoRepository<UsrAuthor, String> {
   public List<UsrAuthor> findByUsername(String username);
   public UsrAuthor findByUsernameAndAuthorid(String username,String authorid);
}