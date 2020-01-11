package com.udacity.course3.reviews.entities;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Product extends BaseEntity{

    @NotEmpty(message = "Product must have a name.")
    private String name;

    private String description;

    public Product() {
    }
}
