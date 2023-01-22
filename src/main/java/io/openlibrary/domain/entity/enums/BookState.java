package io.openlibrary.domain.entity.enums;

public enum BookState {
    SHELF,  // 서가에 비치된 상태
    DESK_READY, // 예약도서가 데스크에 있을때
    ON_RENT,  // 대여중
    DELIVERY,  // 이동중에
    INTERLIBRARY_LOAN, //도서간상호대차
    UNAVAILABLE, //운영싱의 이유로 책이 제공되지 못할때
    LOST,
}
