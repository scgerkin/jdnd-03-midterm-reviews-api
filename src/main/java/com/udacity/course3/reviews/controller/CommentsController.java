package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.persistence.documents.CommentDocument;
import com.udacity.course3.reviews.persistence.documents.ReviewDocument;
import com.udacity.course3.reviews.persistence.entities.Comment;
import com.udacity.course3.reviews.persistence.entities.Review;
import com.udacity.course3.reviews.exceptions.ReviewNotFoundException;
import com.udacity.course3.reviews.repositories.jpa.CommentJpaRepository;
import com.udacity.course3.reviews.repositories.jpa.ReviewJpaRepository;
import com.udacity.course3.reviews.repositories.mongo.CommentMongoRepository;
import com.udacity.course3.reviews.repositories.mongo.ReviewMongoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentsController {

    private final CommentJpaRepository commentJpaRepository;
    private final CommentMongoRepository commentMongoRepository;
    private final ReviewJpaRepository reviewJpaRepository;
    private final ReviewMongoRepository reviewMongoRepository;

    public CommentsController(CommentJpaRepository commentJpaRepository,
                              CommentMongoRepository commentMongoRepository,
                              ReviewJpaRepository reviewJpaRepository,
                              ReviewMongoRepository reviewMongoRepository) {
        this.commentJpaRepository = commentJpaRepository;
        this.commentMongoRepository = commentMongoRepository;
        this.reviewJpaRepository = reviewJpaRepository;
        this.reviewMongoRepository = reviewMongoRepository;
    }


    /**
     * Creates a comment for a review.
     *
     * 1. Add argument for comment entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, save comment.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.POST)
    public ResponseEntity<Comment> createCommentForReview(@PathVariable("reviewId") Long reviewId, @RequestBody Comment comment) {
        Optional<Review> optionalReview = reviewJpaRepository.findById(reviewId);
        if (optionalReview.isPresent()) {
            comment.setReview(optionalReview.get());
            comment = commentJpaRepository.save(comment);
            Optional<ReviewDocument> optionalReviewDocument = reviewMongoRepository.findByJpaId(optionalReview.get().getId());
            if (optionalReviewDocument.isPresent()) {
                ReviewDocument reviewDocument = optionalReviewDocument.get();
                List<CommentDocument> commentDocuments = reviewDocument.getComments();
                commentDocuments.add(new CommentDocument(comment));
                reviewDocument.setComments(commentDocuments);
                reviewMongoRepository.save(reviewDocument);
            }
            return  ResponseEntity.ok(comment);
        }
        else throw new ReviewNotFoundException(reviewId);
    }

    /**
     * List comments for a review.
     *
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, return list of comments.
     *
     * @param reviewId The id of the review.
     */
    @RequestMapping(value = "/reviews/{reviewId}", method = RequestMethod.GET)
    public ResponseEntity<List<Comment>> listCommentsForReview(@PathVariable("reviewId") Long reviewId) {
        Optional<Review> optionalReview = reviewJpaRepository.findById(reviewId);
        if (optionalReview.isPresent()) {
            return ResponseEntity.ok(commentJpaRepository.findAllByReview(optionalReview.get()));
        }
        else {
            throw new ReviewNotFoundException(reviewId);
        }
    }
}