package io.openlibrary.service.result_object;

import io.openlibrary.entity.domain.enums.BookState;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookDetailRO {
    private Long bookMasterId;
    private String title;
    private String author;
    private String publisher;
    private LocalDateTime publicationDateTime;
    private String isbnCode;
    private String infoUrl;
    private Long bookStockId;
    private String stockCount;
    private Long TotalRentCount;
    private String bookBarCode;
    private BookState state;
    private Long lastRentAccountId;
}
