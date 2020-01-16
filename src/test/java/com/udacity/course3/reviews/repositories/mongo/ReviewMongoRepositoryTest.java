package com.udacity.course3.reviews.repositories.mongo;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.udacity.course3.reviews.persistence.documents.ReviewDocument;
import com.udacity.course3.reviews.persistence.entities.Product;
import com.udacity.course3.reviews.persistence.entities.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataMongoTest
public class ReviewMongoRepositoryTest {
    @Autowired
    MongoTemplate mongoTemplate;
    @Autowired
    ReviewMongoRepository reviewMongoRepository;
    @Autowired
    CommentMongoRepository commentMongoRepository;

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(mongoTemplate).isNotNull();
        assertThat(reviewMongoRepository).isNotNull();
        assertThat(commentMongoRepository).isNotNull();
    }

    @Test
    public void testMongoTemplate() {
        DBObject dbObject = BasicDBObjectBuilder.start()
                                .add("key", "value")
                                .get();
        mongoTemplate.save(dbObject, "collectionName");
        assertThat(mongoTemplate.findAll(DBObject.class, "collectionName"))
            .extracting("key")
            .containsOnly("value");
    }

    @Test
    public void testCreateReview() {
        Review review = getReview(1);
        ReviewDocument reviewDocument = reviewMongoRepository.save(new ReviewDocument(review));
        assertThat(reviewDocument).isNotNull();
        assertThat(reviewDocument.getJpaId()).isEqualTo(review.getId());
        assertThat(reviewDocument.getTitle()).isEqualTo(review.getTitle());
        assertThat(reviewDocument.getContent()).isEqualTo(review.getContent());
        assertThat(reviewDocument.getDatePosted()).isEqualTo(review.getDatePosted());
        assertThat(reviewDocument.getProductId()).isEqualTo(review.getProduct().getId());
    }

    @Test
    public void testFindAll() {
        final int numReviews = 10;
        List<Review> reviews = new ArrayList<>(numReviews);
        for (int i = 0; i < numReviews; i++) {
            Review review = getReview(i);
            reviews.add(review);
            reviewMongoRepository.save(new ReviewDocument(review));
        }
        List<ReviewDocument> reviewDocuments = reviewMongoRepository.findAll();
        assertThat(reviewDocuments.size()).isEqualTo(numReviews);
    }

    private Review getReview(int reviewNumber) {
        Product product = new Product();
        product.setId(1L);
        product.setName("Product Name");
        product.setDescription("Product description");
        Review review = new Review();
        review.setId((long) reviewNumber);
        review.setTitle("Review " + reviewNumber + " title");
        review.setContent("Review " + reviewNumber + " content");
        review.setDatePosted(new Date());
        review.setProduct(product);
        return review;
    }

}
