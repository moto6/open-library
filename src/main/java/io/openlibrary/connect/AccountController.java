package io.openlibrary.connect;

import io.openlibrary.common.aop.advice.ConnectLogger;
import io.openlibrary.connect.dto.LoginCommandDto;
import io.openlibrary.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@ConnectLogger
@RequiredArgsConstructor
@RequestMapping("/api/v0/account")
public class AccountController {
    private final AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginCommandDto loginCommandDto, HttpServletResponse response) {
        Cookie cookie = new Cookie("AccessToken", accountService.login(loginCommandDto.getIamCode()).issueAccessToken());
        cookie.setMaxAge(3600);
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseEntity.ok("login ok");
    }

    @PatchMapping("/logout")
    private ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("AccessToken", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResponseEntity.ok("logout ok");
    }
}
