package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.persistence.documents.ReviewDocument;
import com.udacity.course3.reviews.persistence.entities.Product;
import com.udacity.course3.reviews.persistence.entities.Review;
import com.udacity.course3.reviews.exceptions.ProductNotFoundException;
import com.udacity.course3.reviews.repositories.jpa.ProductJpaRepository;
import com.udacity.course3.reviews.repositories.jpa.ReviewJpaRepository;
import com.udacity.course3.reviews.repositories.mongo.ReviewMongoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Spring REST controller for working with review entity.
 */
@RestController
public class ReviewsController {

    private final ReviewJpaRepository reviewJpaRepository;
    private final ReviewMongoRepository reviewMongoRepository;
    private final ProductJpaRepository productJpaRepository;


    public ReviewsController(ReviewJpaRepository reviewJpaRepository,
                             ReviewMongoRepository reviewMongoRepository,
                             ProductJpaRepository productJpaRepository) {
        this.reviewJpaRepository = reviewJpaRepository;
        this.reviewMongoRepository = reviewMongoRepository;
        this.productJpaRepository = productJpaRepository;
    }

    /**
     * Creates a review for a product.
     * <p>
     * 1. Add argument for review entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of product.
     * 3. If product not found, return NOT_FOUND.
     * 4. If found, save review.
     *
     * @param productId The id of the product.
     * @return The created review or 404 if product id is not found.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.POST)
    public ResponseEntity<Review> createReviewForProduct(@PathVariable("productId") Long productId, @RequestBody Review review) {
        Optional<Product> optionalProduct = productJpaRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            review.setProduct(optionalProduct.get());
            review = reviewJpaRepository.save(review);
            reviewMongoRepository.save(new ReviewDocument(review));
            return ResponseEntity.ok(review);
        }
        else {
            throw new ProductNotFoundException(productId);
        }
    }

    /**
     * Lists reviews by product.
     * @param productId The id of the product.
     * @return The list of reviews.
     * @throws ProductNotFoundException if the product ID is invalid or does not exist.
     */
    @RequestMapping(value = "/reviews/products/{productId}", method = RequestMethod.GET)
    public ResponseEntity<List<ReviewDocument>> listReviewsForProduct(@PathVariable("productId") Long productId) {
        Optional<Product> optionalProduct = productJpaRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            return ResponseEntity.ok(reviewMongoRepository.findAllByProductId(optionalProduct.get().getId()));
        }
        else {
            throw new ProductNotFoundException(productId);
        }
    }
}