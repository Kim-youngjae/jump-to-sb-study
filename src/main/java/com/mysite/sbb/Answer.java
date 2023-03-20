package com.mysite.sbb;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;
    @ManyToOne // N:1관계 답변은 하나의 질문에 여러개가 달릴 수 있는 구조
    // @ManyToOne은 부모 자식 관계를 갖는 구조에서 사용한다.
    // 여기서 부모는 Question, 자식은 Answer라고 할 수 있다.
    private Question question;
}