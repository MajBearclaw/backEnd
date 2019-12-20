package com.krry.repository;

import com.krry.entity.Blog;
import com.krry.entity.CollectBlogs;
import com.krry.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CollectBlogsRepository extends MongoRepository<CollectBlogs, String> {
    public List<CollectBlogs> findCollectBlogsByUsername(String username);
    public List<CollectBlogs> findCollectBlogsByBid(String bid);
    public CollectBlogs findByUsernameAndBid(String username,String bid);
}
