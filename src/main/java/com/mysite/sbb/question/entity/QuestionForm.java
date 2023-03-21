package com.mysite.sbb.question.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionForm {
    // NOTNULL 제약 조건
    // Null 또는 빈 문자열("")을 허용하지 않음
    @NotEmpty(message = "제목은 필수사항입니다.")
    @Size(max = 200) // 자료형 조건 VARCHAR(200)과 같은 의미
    private String subject;

    @NotEmpty(message = "내용은 필수항목입니다.")
    private String content;
}
