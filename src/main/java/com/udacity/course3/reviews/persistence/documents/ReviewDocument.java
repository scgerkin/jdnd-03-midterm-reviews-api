package com.udacity.course3.reviews.persistence.documents;

import com.udacity.course3.reviews.persistence.entities.Review;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document
@Getter
@Setter
public class ReviewDocument {
    @Id
    private String id;

    private Long jpaId;

    @NotEmpty(message = "Review must have a title.")
    private String title;

    @NotNull(message = "Review content cannot be null.")
    private String content;

    private Date datePosted;

    @NotNull(message = "Review must belong to a product.")
    private Long productId;

    private List<CommentDocument> comments = new ArrayList<>();

    public ReviewDocument() {
    }

    public ReviewDocument(Review review) {
        this.jpaId = review.getId();
        this.title = review.getTitle();
        this.content = review.getContent();
        this.datePosted = review.getDatePosted();
        this.productId = review.getProduct().getId();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ReviewDocument{" +
                                                 "id='" + id + '\'' +
                                                 ", jpaId=" + jpaId +
                                                 ", title='" + title + '\'' +
                                                 ", content='" + content + '\'' +
                                                 ", datePosted=" + datePosted +
                                                 ", productId=" + productId +
                                                 ", comments:\n");
        comments.forEach(c-> sb.append(c.toString()).append("\n"));
        sb.append("}");
        return sb.toString();
    }
}
