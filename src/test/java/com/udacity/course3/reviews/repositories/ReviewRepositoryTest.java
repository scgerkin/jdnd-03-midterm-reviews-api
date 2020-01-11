package com.udacity.course3.reviews.repositories;

import com.udacity.course3.reviews.entities.Product;
import com.udacity.course3.reviews.entities.Review;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReviewRepositoryTest {

    @Autowired
    DataSource dataSource;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    EntityManager entityManager;
    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ReviewRepository reviewRepository;

    private static Product product;
    private static Review review;

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(testEntityManager).isNotNull();
        assertThat(productRepository).isNotNull();
        assertThat(reviewRepository).isNotNull();
    }

    @Test
    public void testCreateReviewForProduct() {
        Product product = new Product();
        product.setName("testCreateReviewForProduct Product Title");
        product.setDescription("testCreateReviewForProduct Product Description");
        product = productRepository.save(product);
        Review review = new Review();
        review.setTitle("testCreateReviewForProduct Review Title");
        review.setContent("testCreateReviewForProduct Review Content");
        review.setProduct(product);
        review.setDatePosted(new Date(System.currentTimeMillis()));
        Review expected = reviewRepository.save(review);
        assertThat(expected).isNotNull();
    }

    @Test
    public void testFindAll() {
        Product product = new Product();
        product.setName("testFindAll Product Title");
        product.setDescription("testFindAll Product Description");
        product = productRepository.save(product);
        Review review = new Review();
        review.setTitle("testFindAll Review Title");
        review.setContent("testFindAll Review Content");
        review.setProduct(product);
        review.setDatePosted(new Date(System.currentTimeMillis()));
        reviewRepository.save(review);
        List<Review> reviewList = reviewRepository.findAll();
        assertThat(reviewList.size()).isEqualTo(1);
    }

    @Test
    public void testListReviewsForProduct() {
        Product product = new Product();
        product.setName("testListReviewsForProduct Product Title");
        product.setDescription("testListReviewsForProduct Product Description");
        product = productRepository.save(product);
        Review review = new Review();
        review.setTitle("testListReviewsForProduct Review Title");
        review.setContent("testListReviewsForProduct Review Content");
        review.setProduct(product);
        review.setDatePosted(new Date(System.currentTimeMillis()));
        review = reviewRepository.save(review);
        List<Review> reviewList = reviewRepository.findAllByProduct(product);
        assertThat(reviewList.size()).isEqualTo(1);
    }

}
