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

}
