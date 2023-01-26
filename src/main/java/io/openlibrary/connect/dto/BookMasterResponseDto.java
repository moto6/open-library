package io.openlibrary.connect.dto;

import io.openlibrary.entity.domain.BookMaster;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
public class BookMasterResponseDto {

    public BookMasterResponseDto(BookMaster bookMaster) {
        this.bookMasterId = bookMaster.getBookMasterId();
        this.title = bookMaster.getTitle();
        this.author = bookMaster.getAuthor();
        this.publisher = bookMaster.getPublisher();
    }

    private Long bookMasterId;
    private String title;
    private String author;
    private String publisher;
}
