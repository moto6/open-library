package io.openlibrary.connect;

import io.openlibrary.common.aop.advice.ConnectLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@ConnectLogger
@RequiredArgsConstructor
public class WebController {

    @GetMapping
    public String index() {
        //todo #1
        return "index";
    }

    @GetMapping("/admin/login")
    public String adminLogin() {
        //todo #1
        return "admin/login";
    }

    @GetMapping("/search")
    public String search() {
        //todo #1
        return "user/book-search";
    }

    @GetMapping("/login")
    public String userLogin() {
        //todo #1
        return "user/login";
    }



}
