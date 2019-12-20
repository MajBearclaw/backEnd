package com.krry.repository;

import com.krry.entity.CollectBlogs;
import com.krry.entity.Fan;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FansRepository extends MongoRepository<Fan, String> {
    //return one's fans
    public List<Fan> findFansByStar(String username);
    //return one's stars
    public List<Fan> findFansByFans(String username);
    public Fan findFanByStarAndFans(String fans,String star);
}
