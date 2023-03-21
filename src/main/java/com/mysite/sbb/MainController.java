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

    @GetMapping("/") // http://localhost:8080/ -> 루트 주소
    public String root() {
        return "redirect:/question/list"; //다시 질문 리스트로 리다이렉트하라는 의미 (redirect:/)
    }


}
