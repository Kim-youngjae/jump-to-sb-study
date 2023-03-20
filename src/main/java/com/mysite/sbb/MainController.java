package com.mysite.sbb;

import com.mysite.sbb.question.entity.Question;
import com.mysite.sbb.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequiredArgsConstructor //final이 붙은 속성을 포함하는 생성자를 자동으로 생성하는 역할을 한다.
@Controller // MainController클래스는 스프링부트의 컨트롤러로 지정한다는 의미
public class MainController {

    private final QuestionRepository questionRepository;

    @GetMapping("/sbb")
    @ResponseBody
    public String index() {
        return "index실행";
    }

    @GetMapping("/question/list")
//    @ResponseBody
    public String list(Model model) { // 모델 클래스를 활용해서 템플릿으로 전달할 수 있다.
        List<Question> questionList = this.questionRepository.findAll();
        model.addAttribute("questionList", questionList);
        return "question_list";
    }
}
