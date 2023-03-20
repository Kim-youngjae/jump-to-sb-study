package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // MainController클래스는 스프링부트의 컨트롤러로 지정한다는 의미
public class MainController {
    @GetMapping("/sbb")
}
