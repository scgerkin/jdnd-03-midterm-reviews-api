package com.udacity.course3.reviews.persistence.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Review extends BaseEntity{

    @NotEmpty(message = "Review must have a title.")
    private String title;

    private String content;

    private Date datePosted;

    @NotNull(message = "Review must belong to a product")
    @ManyToOne
    private Product product;

    public Review() {
    }
}
