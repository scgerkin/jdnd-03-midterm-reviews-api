package com.udacity.course3.reviews.entities;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Product extends BaseEntity{

    private String name;
    private String description;

    public Product() {
    }
}
