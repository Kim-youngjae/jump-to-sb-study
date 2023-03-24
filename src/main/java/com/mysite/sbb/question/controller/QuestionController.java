package com.mysite.sbb.question.controller;

import com.mysite.sbb.answer.entity.AnswerForm;
import com.mysite.sbb.question.entity.QuestionForm;
import com.mysite.sbb.question.service.QuestionService;
import com.mysite.sbb.question.entity.Question;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/question") // url prefix
@Controller
@RequiredArgsConstructor //final이 붙은 속성을 포함하는 생성자를 자동으로 생성하는 역할을 한다.
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/list")
//    @ResponseBody
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) { // 모델 클래스를 활용해서 템플릿으로 전달할 수 있다.
        Page<Question> paging = this.questionService.getList(page);
        model.addAttribute("paging", paging);
        return "question_list";
    }

    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) { // 위 GetMapping에 사용한 {id}이름과 매개변수명 동일해야한다
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    @GetMapping("/create")
    public String questionCreate(QuestionForm questionForm) {
        return "question_form";
    }

    // 질문 등록하기 버튼을 눌렸을 때에 등록하는 요청을 보낼 메서드
    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // QuestionForm에서 에러가나면 BindingResult에서 에러를 감지한다.
            // 에러가 있으면
            return "question_form";
        }

        // form에서 subject와 content를 넘겨주기 때문에 폼으로부터 데이터를 얻음
        this.questionService.create(questionForm.getSubject(), questionForm.getContent());
        return "redirect:/question/list";
    }

}
