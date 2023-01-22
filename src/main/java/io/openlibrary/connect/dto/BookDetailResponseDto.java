package io.openlibrary.connect.dto;

import io.openlibrary.service.result_object.BookDetailRO;

public class BookDetailResponseDto {
    public BookDetailResponseDto(BookDetailRO bookDetailRO) {
        //RO 에서 DTO 로 변환하는 코드 만드셈
    }
    // bookStock + bookmaster + detail 세가지 정보 모두를 담아서 리턴
}
