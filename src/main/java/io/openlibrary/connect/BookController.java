package io.openlibrary.connect;

import io.openlibrary.common.aop.advice.ConnectLogger;
import io.openlibrary.connect.dto.BookDetailResponseDto;
import io.openlibrary.connect.dto.BookMasterResponseDto;
import io.openlibrary.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@ConnectLogger
@RequiredArgsConstructor
@RequestMapping("/api/v0/books")
public class BookController {

    private final BookService bookService;

    @GetMapping("/default")
    public ResponseEntity<List<BookMasterResponseDto>> bookMasterDefaultList() {
        //todo #201 - bookMaster 리턴해줘야함. 왜냐하면 고객들은 장서를 찾는게 아니라 책의 종류(book-master)로 인식하기 떄문
        bookService.stockList();
        return ResponseEntity.ok(null);
    }


    @GetMapping("/search/v0/title")
    public ResponseEntity<List<BookMasterResponseDto>> searchTitleQueryV0(@RequestParam("keyword") String keyword) {
        //todo #202 - bookMaster 리턴해줘야함. 제목기준으로 full-text-scan

        return ResponseEntity.ok(bookService.searchByTitleLike(keyword)
                .stream()
                .map(BookMasterResponseDto::new)
                .collect(Collectors.toList()));
    }

    @GetMapping("/search/v0/author")
    public ResponseEntity<List<BookMasterResponseDto>> searchAuthorQueryV0(@RequestParam String keyword) {
        //todo #202 - bookMaster 리턴해줘야함. 저자기준으로 검색
        return ResponseEntity.ok(bookService
                .searchByAuthorLike(keyword)
                .stream()
                .map(BookMasterResponseDto::new)
                .collect(Collectors.toList())
        );
    }



    @GetMapping("/search/v1/title")
    public ResponseEntity<List<BookMasterResponseDto>> searchTitleQueryV1(@RequestParam String keyword) {
        //todo #202 - bookMaster 리턴해줘야함. 제목기준으로 full-text-scan
        bookService.searchByTitleLike(keyword);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/search/v1/author")
    public ResponseEntity<List<BookMasterResponseDto>> searchAuthorQueryV1(@RequestParam String keyword) {
        //todo #202 - bookMaster 리턴해줘야함. 저자기준으로 검색
        bookService.searchByAuthorLike(keyword);
        return ResponseEntity.ok(null);
    }





    @GetMapping("/{bookMasterCode}")
    public ResponseEntity<BookDetailResponseDto> detailByCode(@RequestParam String bookMasterCode) {
        //todo #203 - bookMasterCode 기준으로 책 세부내용 조회
        return ResponseEntity.ok(new BookDetailResponseDto(bookService.detailByCode(bookMasterCode)));
    }

    @GetMapping("/{isbnCode}")
    public ResponseEntity<BookDetailResponseDto> bookMasterIsbn(@RequestParam String isbnCode) {
        //todo #204 - isbnCode 기준으로 책 세부내용 조회
        return ResponseEntity.ok(new BookDetailResponseDto(bookService.detailByisbn(isbnCode)));
    }
}
