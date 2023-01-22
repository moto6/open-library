package io.openlibrary.domain.entity;

import io.openlibrary.domain.entity.enums.BookState;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "BOOK_STOCK", indexes = {
        @Index(name="IDX_BOOK_MASTER_ID",columnList = "BOOK_MASTER_ID",unique = false)
})
public class BookStock {
    @Id
    @GeneratedValue
    @Column(name = "BOOK_STOCK_ID")
    private Long bookStockId;
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
    @Column(name = "LAST_RENT_ACCOUNT_ID") //이거 필요없는거 아닌지 고민해봐. 정규화이론에 입각해서 생각해보렴
    private Long lastRentAccountId;
    @Column
    private String note;
}
