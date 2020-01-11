package com.udacity.course3.reviews.repositories;

import com.udacity.course3.reviews.entities.Comment;
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
public class CommentRepositoryTest {

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
    @Autowired
    CommentRepository commentRepository;

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(testEntityManager).isNotNull();
        assertThat(productRepository).isNotNull();
        assertThat(reviewRepository).isNotNull();
        assertThat(commentRepository).isNotNull();
    }

    @Test
    public void testCreateCommentForReview() {
        Product product = new Product();
        product.setName("testCreateCommentForReview Product Title");
        product.setDescription("testCreateCommentForReview Product Description");
        product = productRepository.save(product);
        Review review = new Review();
        review.setTitle("testCreateCommentForReview Review Title");
        review.setContent("testCreateCommentForReview Review Content");
        review.setProduct(product);
        review.setDatePosted(new Date(System.currentTimeMillis()));
        review = reviewRepository.save(review);
        Comment comment = new Comment();
        comment.setTitle("testCreateCommentForReview Comment Title");
        comment.setContent("testCreateCommentForReview Comment Content");
        comment.setDatePosted(new Date(System.currentTimeMillis()));
        comment.setReview(review);
        Comment expected = commentRepository.save(comment);
        assertThat(expected).isNotNull();
    }

    @Test
    public void testListCommentsForReview() {
        Product product = new Product();
        product.setName("testListCommentsForReview Product Title");
        product.setDescription("testListCommentsForReview Product Description");
        product = productRepository.save(product);
        Review review = new Review();
        review.setTitle("testListCommentsForReview Review Title");
        review.setContent("testListCommentsForReview Review Content");
        review.setProduct(product);
        review.setDatePosted(new Date(System.currentTimeMillis()));
        review = reviewRepository.save(review);
        Comment comment = new Comment();
        comment.setTitle("testListCommentsForReview Comment Title");
        comment.setContent("testListCommentsForReview Comment Content");
        comment.setDatePosted(new Date(System.currentTimeMillis()));
        comment.setReview(review);
        commentRepository.save(comment);
        List<Comment> commentList = commentRepository.findAllByReview(review);
        assertThat(commentList.size()).isEqualTo(1);
    }


}
