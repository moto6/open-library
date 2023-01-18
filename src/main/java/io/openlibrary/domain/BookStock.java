package io.openlibrary.domain;

import io.openlibrary.domain.enums.BookState;

import javax.persistence.*;

@Entity
@Table(name = "BOOK_STOCK", indexes = {
        @Index(name="IDX_BOOK_MASTER_ID",columnList = "bookMasterId",unique = false)
})
public class BookStock {
    @Id
    private Long bookId;
    @Column(name = "BOOK_MASTER_ID")
    private Long bookMasterId;
    @Column(name = "STOCK_COUNT")
    private String stockCount;
    @Column(name = "TOTAL_RENT_COUNT")
    private Long TotalRentCount;
    @Column
    private String bookBarCode; //todo:똑같은책 두권사이 구분되어야함? 아니라면 바코드는 마스터로 가야됨 >> 같은책으로 마스터를 두개 딸수도 있어서 여기있음
    @Column
    private BookState state;
    @Column
    private Long lastRentAccountId;
    @Column
    private String note;
}
