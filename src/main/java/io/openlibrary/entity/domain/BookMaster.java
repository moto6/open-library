package io.openlibrary.entity.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.function.Function;

@NoArgsConstructor
@Getter
@Table(name = "BOOK_MASTER", indexes = {
        @Index(name = "IDX_ISBN_CODE", columnList = "ISBN_CODE", unique = false)
})
@Entity
public class BookMaster {
    @Id
    @GeneratedValue
    @Column(name = "BOOK_MASTER_ID")
    private Long bookMasterId;
    @Column
    private String title;//텍스트인덱싱 필요할꺼같은데?
    @Column
    private String author;
    @Column
    private String publisher;
    @Column(name = "PUBLICATION_YEAR")
    private Integer publicationYear;
    @Column(name = "ISBN_CODE")
    private String isbnCode;
    @Column
    private String infoUrl;

    //어딧 필요??
    @Builder
    public BookMaster(String title, String author, String publisher, Integer publicationYear, String isbnCode, String infoUrl) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.isbnCode = isbnCode;
        this.infoUrl = infoUrl;
    }

}


