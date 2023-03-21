package com.mysite.sbb.question.controller;

import com.mysite.sbb.question.service.QuestionService;
import com.mysite.sbb.question.entity.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@RequestMapping("/question") // url prefix
@Controller
@RequiredArgsConstructor //final이 붙은 속성을 포함하는 생성자를 자동으로 생성하는 역할을 한다.
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/list")
//    @ResponseBody
    public String list(Model model) { // 모델 클래스를 활용해서 템플릿으로 전달할 수 있다.
        List<Question> questionList = this.questionService.getList();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }

    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) { // 위 GetMapping에 사용한 {id}이름과 매개변수명 동일해야한다
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }
}
