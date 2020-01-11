package com.udacity.course3.reviews.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Comment extends BaseEntity {

    private String title;
    private String text;
    private Date datePosted;
    @ManyToOne
    private Review review;

    public Comment() {
    }
}
