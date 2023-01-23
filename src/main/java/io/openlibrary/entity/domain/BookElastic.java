package io.openlibrary.entity.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;


import javax.persistence.Id;

@Getter
@Setter
@Document(indexName = "bookElastic")
public class BookElastic {
    @Id
    private String bookMasterId;

    @Field(type = FieldType.Text, name = "title")
    private String title;

    @Field(type = FieldType.Text, name = "author")
    private String author;
}
