package io.openlibrary.entity.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@NoArgsConstructor
@Entity
@Getter
public class BookDetail {
    @Id
    @GeneratedValue
    private Long bookDetailId;


}
