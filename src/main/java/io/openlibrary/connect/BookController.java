package io.openlibrary.connect;

import io.openlibrary.connect.dto.BookDetailResponseDto;
import io.openlibrary.connect.dto.BookMasterResponseDto;
import io.openlibrary.connect.dto.BookQueryDto;
import io.openlibrary.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
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



    @GetMapping("/search")
    public ResponseEntity<List<BookMasterResponseDto>> bookMasterQuery(@RequestBody BookQueryDto bookQueryDto) {
        //todo #202 - bookMaster 리턴해줘야함. 왜냐하면 고객들은 장서를 찾는게 아니라 책의 종류(book-master)로 인식하기 떄문
        bookService.search(bookQueryDto);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{bookMasterCode}")
    public ResponseEntity<BookDetailResponseDto> bookMaster(@RequestParam String bookMasterCode) {
        //todo #1
        bookService.detailByCode(bookMasterCode);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{isbnCode}")
    public ResponseEntity<BookDetailResponseDto> bookMasterIsbn(@RequestParam String isbnCode) {
        //todo #1

        return ResponseEntity.ok(new BookDetailResponseDto(bookService.detailByisbn(isbnCode)));
    }


}
