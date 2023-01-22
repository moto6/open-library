package io.openlibrary.entity.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.function.Function;

@NoArgsConstructor
@Getter
@Table(name = "BOOK_MASTER", indexes = {
        @Index(name="IDX_ISBN_CODE",columnList = "ISBN_CODE",unique = false)
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

    @Builder
    public BookMaster(String title, String author, String publisher, Integer publicationYear, String isbnCode,String infoUrl) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.isbnCode = isbnCode;
        this.infoUrl = infoUrl;
    }

    public static Function<? super String[], ? extends BookMaster> csvMapper() {

        return new Function<String[], BookMaster>() {
            @Override
            public BookMaster apply(String[] strings) {

                return BookMaster.builder()
                        .title(strings[1])
                        .author(strings[2])
                        .publisher(strings[3])
                        .publicationYear(Integer.parseInt(strings[4]))
                        .isbnCode(strings[5])
                        .build();
                //0-번호,
                // 1-도서명,
                // 2저자,
                // 3출판사,
                // 4발행년도,
                // 5ISBN,
                // 6세트 ISBN,
                // 7부가기호,
                // 8권,
                // 9주제분류번호,
                // 10도서권수,
                // 11대출건수,
                // 12등록일자,
            }
        };
    }
    //어딧 필요
}


