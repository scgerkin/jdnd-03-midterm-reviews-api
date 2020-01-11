package com.udacity.course3.reviews.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Product not found.")
public class ProductNotFoundException extends NotFoundException {
    public ProductNotFoundException(Long id) {
        super("Product", id);
    }
}
