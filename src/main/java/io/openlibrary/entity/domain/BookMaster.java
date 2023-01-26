package io.openlibrary.entity.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Table(name = "BOOK_MASTER", indexes = {
        @Index(name = "IDX_ISBN_CODE", columnList = "ISBN_CODE", unique = false)
})
@Entity
public class BookMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BOOK_MASTER_ID")
    private Long bookMasterId;
    @Column(name = "TITLE", columnDefinition = "VARCHAR(500), FULLTEXT IDX_FULLTEXT_TITLE (TITLE)")
    private String title;
    @Column(name = "AUTHOR")
    private String author;
    @Column(name = "PUBLISHER")
    private String publisher;
    @Column(name = "PUBLICATION_YEAR")
    private Integer publicationYear;
    @Column(name = "ISBN_CODE")
    private String isbnCode;
    @Column(name = "KDC_CODE", length = 10)
    private String kdcCode;
    @Column(name = "INFO_URL")
    private String infoUrl;

    @Builder
    public BookMaster(String title, String author, String publisher, Integer publicationYear, String isbnCode,String kdcCode, String infoUrl) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.isbnCode = isbnCode;
        this.kdcCode = kdcCode;
        this.infoUrl = infoUrl;
    }


    public static String cleaningAuthor(String author) {
        if (author.length() > 100) {
            return author.substring(0, 99);
        }
        return author;
    }

    public static int convertPublicationYear(String publicationYear) {
        if (publicationYear.isBlank()) {
            return 0;
        }
        if (publicationYear.length() == 4 && Character.isDigit(publicationYear.charAt(0)) && Character.isDigit(publicationYear.charAt(3))) {
            return Integer.parseInt(publicationYear);
        }
        if (publicationYear.length() == 5 && publicationYear.endsWith("-")) {
            return Integer.parseInt(publicationYear.substring(0, 3));
        }
        return 0;
    }

    public static String cleaningKdcCode(String kdcCode) {
        if (kdcCode.length() >= 10) {
            return kdcCode.substring(0, 9);
        }
        return kdcCode;
    }
}


