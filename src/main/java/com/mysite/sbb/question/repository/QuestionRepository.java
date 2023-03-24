package com.mysite.sbb.question.repository;

import com.mysite.sbb.question.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
    // findBySubject와 같은 메서드를 기본적으로 제공하지는 않는다.
    // findBySubject 메서드를 사용하려면 다음처럼 QuestionRepository 인터페이스를 변경해야 한다.
    Question findBySubject(String subject);
    Question findBySubjectAndContent(String subject, String content); // And 조건으로 조회
    List<Question> findBySubjectLike(String subject);

    Page<Question> findAll(Pageable pageable);
}