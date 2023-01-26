package io.openlibrary.connect;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminWebController {

    @GetMapping("/main")
    public String apiName() {
        //todo #1
        return "admin/main";
    }
}
