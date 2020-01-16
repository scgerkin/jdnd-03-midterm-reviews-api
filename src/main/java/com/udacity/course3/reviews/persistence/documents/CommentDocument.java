package com.udacity.course3.reviews.persistence.documents;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Document
@Data
public class CommentDocument {
    @Id
    private String id;

    private Long jpaId;

    @NotEmpty(message = "Comment requires a title.")
    private String title;

    @NotNull(message = "Comment content cannot be null.")
    private String content;

    private Date datePosted;

    public CommentDocument() {
    }
}
