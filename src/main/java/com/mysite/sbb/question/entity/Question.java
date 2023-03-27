package com.mysite.sbb.question.entity;

import com.mysite.sbb.answer.entity.Answer;
import com.mysite.sbb.user.entity.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity // 엔티티를 의미하는 애너테이션
@EntityListeners(AuditingEntityListener.class)
public class Question {
    // 엔티티 클래스
    @Id // id속성을 기본 키로 지정해주는 애너테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 값 증가 auto_increment 속성
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    //하나의 질문에 답변이 여러개가 있을 수 있음 1(질문) : N(답변)
    //그래서 Quesetion엔티티의 답변 속성이 리스트 형태
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;

    @ManyToOne
    private SiteUser author;

    private LocalDateTime modifyDate;
}
