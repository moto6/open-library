package io.openlibrary.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@Getter
@Entity
public class BookMaster {
    @Id
    @Column(name = "BOOK_MASTER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookMasterId;
    @Column
    private String title;//텍스트인덱싱 필요할꺼같은데?
    @Column
    private String author;
    @Column
    private String publisher;
    @Column(name = "PUBLICATION_DATE")
    private LocalDateTime publicationDate;
    @Column(name = "ISBN_CODE")
    private String isbnCode;
    @Column
    private String infoUrl;
    //어딧 필요
}
