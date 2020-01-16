package com.udacity.course3.reviews.repositories.mongo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

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
}
