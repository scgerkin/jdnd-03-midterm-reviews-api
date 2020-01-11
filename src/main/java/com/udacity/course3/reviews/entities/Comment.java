package com.udacity.course3.reviews.entities;

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
public class Comment extends BaseEntity {

    @NotEmpty(message = "Comment requires a title.")
    private String title;

    private String content;

    private Date datePosted;

    @NotNull(message = "Comments must belong to a review")
    @ManyToOne
    private Review review;

    public Comment() {
    }
}
