package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller // MainController클래스는 스프링부트의 컨트롤러로 지정한다는 의미
public class MainController {
    @GetMapping("/sbb")
    @ResponseBody
    public String index() {
        return "index실행";
    }
}
