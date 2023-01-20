package io.openlibrary.connect;

import io.openlibrary.domain.Account;
import io.openlibrary.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v0")
public class AccountController {
    private final AccountService accountService;
    //login - 클라 어드민 페이지 다른걸로 들어가지게
    //logout
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody String iamCode, HttpServletResponse response) {
        Cookie cookie = new Cookie("AccessToken",accountService.login(iamCode).issueAccessToken());
        cookie.setMaxAge(3600);
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseEntity.ok("sdf");
    }


    @PatchMapping("/logout")
    private ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("AccessToken", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResponseEntity.ok("sdf");
    }

}
