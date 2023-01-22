package io.openlibrary.connect;

import io.openlibrary.connect.dto.AccountResponseDto;
import io.openlibrary.connect.dto.BookMasterResponseDto;
import io.openlibrary.connect.dto.BookStockResponseDto;
import io.openlibrary.service.AccountService;
import io.openlibrary.service.BookMasterService;
import io.openlibrary.service.BookStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v0/admin")
public class AdminWebController {

    private final AccountService adminService;
    private final BookMasterService bookMasterService;
    private final BookStockService bookStockService;


    @GetMapping("/accounts")
    public ResponseEntity<?> accountList() {
        //todo #101 회원목록
        adminService.accountList();
        return ResponseEntity.ok(null);
    }

    @GetMapping("/account")
    public ResponseEntity<AccountResponseDto> accountDetail() {
        //todo #102 회원상세조회 : 파라미터 받지말고 쿠키에 있는 토큰으로 찾아오기
        adminService.accoutDetail();
        return ResponseEntity.ok(null);
    }

    @GetMapping("/books")
    public ResponseEntity<List<BookStockResponseDto>> books() {
        //todo #103  //장서조회list >> 이거는 일반이용자도 쓸수있는 API 이므로 Book 에 가라
        bookStockService.list();
        return ResponseEntity.ok(null);
    }

    @GetMapping("/book-master")
    public ResponseEntity<List<BookMasterResponseDto>> bookMasterList() {
        //todo #104 //북마스터 조회 list
        bookMasterService.list();
        return ResponseEntity.ok(null);
    }

    @PostMapping("/book-master/register")
    public ResponseEntity<BookMasterResponseDto> bookMasterAdd() {
        //todo #105 //북마스터 추가
        bookMasterService.add();
        return ResponseEntity.ok(null);
    }

    @PatchMapping("/book-master/{isbnCode}")
    public ResponseEntity<?> apiName(@RequestParam String isbnCode) {
        //todo #106//북마스터 변경
        bookMasterService.modify(isbnCode);
        return ResponseEntity.ok(null);
    }


}
