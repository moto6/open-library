package io.openlibrary.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@Getter
@Table(name = "BOOK_MASTER", indexes = {
        @Index(name="IDX_ISBN_CODE",columnList = "ISBN_CODE",unique = true)
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
    @Column(name = "PUBLICATION_DATETIME")
    private LocalDateTime publicationDateTime;
    @Column(name = "ISBN_CODE")
    private String isbnCode;
    @Column
    private String infoUrl;
    //어딧 필요
}
