package com.mysite.sbb.question.service;

import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.question.entity.Question;
import com.mysite.sbb.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    public List<Question> getList() {
        return this.questionRepository.findAll();
    }

    public Question getQuestion(Integer id) {
        Optional<Question> question = this.questionRepository.findById(id); // questionRepository에서 id에 해당하는 객체를 반환
        if (question.isPresent()) { // 존재하면
            return question.get(); // 객체를 얻어옴
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

    public void create(String subject, String content) {
        Question question = new Question(); // 새 질문을 저장할 객체
        question.setSubject(subject);
        question.setContent(content);
        question.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(question); // 생성한 질문을 리포지터리에 저장 요청
    }
}
