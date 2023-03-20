package com.mysite.sbb;

import com.mysite.sbb.answer.repository.AnswerRepository;
import com.mysite.sbb.question.entity.Question;
import com.mysite.sbb.question.repository.QuestionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SbbApplicationTests {
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private AnswerRepository answerRepository;

	@Test
	@DisplayName("DB에 데이터 추가")
	void testJpa1() {
		Question q1 = new Question();
		q1.setSubject("sbb가 무엇인가요?");
		q1.setContent("sbb에 대해서 알고 싶습니다.");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);  // 첫번째 질문 저장

		Question q2 = new Question();
		q2.setSubject("스프링부트 모델 질문입니다.");
		q2.setContent("id는 자동으로 생성되나요?");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);  // 두번째 질문 저장
	}

	@Test
	@DisplayName("DB데이터 조회")
	void testJpa2() {
		List<Question> all = this.questionRepository.findAll();
		assertEquals(2, all.size()); // 해당 결과가 2가 나와야 한다. (기대값, 실제값)

		Question q = all.get(0);
		assertEquals("sbb가 무엇인가요?", q.getSubject());
	}

	@Test
	@DisplayName("Question 엔티티 id값으로 데이터 조회")
	void testJpa3() {
		Optional<Question> oq = this.questionRepository.findById(1);
		if (oq.isPresent()) {
			Question q = oq.get();
			assertEquals("sbb가 무엇인가요?", q.getSubject());
		}
	}

	@Test
	@DisplayName("findBySubject")
	void testJpa4() {
		Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
		assertEquals(1, q.getId());
	}

	@Test
	@DisplayName("findBySubjectAndContent")
	void testJpa5() {
		Question q = this.questionRepository.findBySubjectAndContent(
				"sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
		assertEquals(1, q.getId());
	}

	@Test
	@DisplayName("findBySubjectLike")
	void testJpa6() {
		List<Question> qList = this.questionRepository.findBySubjectLike("sbb%"); //sbb로 시작하는 모든 문자열
		Question q = qList.get(0);
		assertEquals("sbb가 무엇인가요?", q.getSubject());
	}

	@Test
	@DisplayName("데이터 수정")
	void testJpa7() {
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent());
		Question q = oq.get();
		q.setSubject("수정된 제목"); // setter()로 제목을 변경 후
		this.questionRepository.save(q); // 저장
		Question q1 = this.questionRepository.findBySubject("수정된 제목");
		assertEquals(1, q1.getId());
		assertEquals("수정된 제목", q.getSubject());
	}

	@Test
	@DisplayName("데이터 삭제")
	void testJpa8() {
		// 데이터가 정상적으로 두 개가 들어가 있는지 확인하고
		assertEquals(2, this.questionRepository.count()); // 해당 리포지터리의 총 데이터 건수를 리턴
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent()); // oq가 존재하면
		Question q = oq.get(); //oq객체를 얻어와서
		this.questionRepository.delete(q); // 삭제
		assertEquals(1, this.questionRepository.count()); // 삭제하고 행이 2개에서 1개로 변경되었는지 확인
	}

	@Test
	@DisplayName("답변 데이터 생성 후 저장하기")
	void testJpa9() {

	}
}