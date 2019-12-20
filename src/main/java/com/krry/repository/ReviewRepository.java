package com.krry.repository;

import com.krry.entity.Blog;
import com.krry.entity.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, String> {
    public List<Review> findReviewsByBid(String bid);
    public Review findReviewBy_id(String id);
}
