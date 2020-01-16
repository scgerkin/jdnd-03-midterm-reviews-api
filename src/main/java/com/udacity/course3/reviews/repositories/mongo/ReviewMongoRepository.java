package com.udacity.course3.reviews.repositories.mongo;

import com.udacity.course3.reviews.persistence.documents.ReviewDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewMongoRepository extends MongoRepository<ReviewDocument, String> {
    List<ReviewDocument> findAllByProductId(Long productId);
}
