package com.udacity.course3.reviews.exceptions;

public abstract class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super();
    }

    public NotFoundException(String msg) {
        super(msg);
    }

    public NotFoundException(String entityName, Long id) {
        super("Search on " + entityName + " ID: " + id + " not found.");
    }
}
