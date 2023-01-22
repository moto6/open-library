package io.openlibrary.entity.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Table(name = "BOOK_DETAIL")
@Getter
public class BookDetail {
    @Id
    @GeneratedValue
    @Column(name = "BOOK_DETAIL_ID")
    private Long bookDetailId;
}
