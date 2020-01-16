package com.udacity.course3.reviews.repositories.mongo;

import com.udacity.course3.reviews.persistence.documents.CommentDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentMongoRepository extends MongoRepository<CommentDocument, String> {
}
