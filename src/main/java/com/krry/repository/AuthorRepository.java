package com.krry.repository;

import com.krry.entity.Author;
import com.krry.entity.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AuthorRepository extends MongoRepository<Author, String> {
    @Override
    public List<Author> findAll();
    public Author findAuthorByName(String name);
    public Author findAuthorBy_id(String _id);
    public List<Author> findByNameLike(String name);
    public List<Author> findByTag(String tag);
}
