package com.mysite.sbb;

import com.mysite.sbb.answer.entity.Answer;
import com.mysite.sbb.answer.repository.AnswerRepository;
import com.mysite.sbb.question.entity.Question;
import com.mysite.sbb.question.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // 테스트케이스를 영문자숫자순으로 실행하도록 설정
@SpringBootTest
class SbbApplicationTests {
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private AnswerRepository answerRepository;

	@Test
	@DisplayName("DB에 데이터 추가")
	void testJpa_1() {
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
	void testJpa_2() {
		List<Question> all = this.questionRepository.findAll();
		assertEquals(2, all.size()); // 해당 결과가 2가 나와야 한다. (기대값, 실제값)

		Question q = all.get(0);
		assertEquals("sbb가 무엇인가요?", q.getSubject());
	}

	@Test
	@DisplayName("Question 엔티티 id값으로 데이터 조회")
	void testJpa_3() {
		Optional<Question> oq = this.questionRepository.findById(1);
		if (oq.isPresent()) {
			Question q = oq.get();
			assertEquals("sbb가 무엇인가요?", q.getSubject());
		}
	}

	@Test
	@DisplayName("findBySubject")
	void testJpa_4() {
		Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
		assertEquals(1, q.getId());
	}

	@Test
	@DisplayName("findBySubjectAndContent")
	void testJpa_5() {
		Question q = this.questionRepository.findBySubjectAndContent(
				"sbb가 무엇인가요?", "sbb에 대해서 알고 싶습니다.");
		assertEquals(1, q.getId());
	}

	@Test
	@DisplayName("findBySubjectLike")
	void testJpa_6() {
		List<Question> qList = this.questionRepository.findBySubjectLike("sbb%"); //sbb로 시작하는 모든 문자열
		Question q = qList.get(0);
		assertEquals("sbb가 무엇인가요?", q.getSubject());
	}

	@Test
	@DisplayName("데이터 수정")
	void testJpa_7() {
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
	void testJpa_8() {
		// 데이터가 정상적으로 두 개가 들어가 있는지 확인하고
		assertEquals(2, this.questionRepository.count()); // 해당 리포지터리의 총 데이터 건수를 리턴
		Optional<Question> oq = this.questionRepository.findById(1);
		assertTrue(oq.isPresent()); // oq가 존재하면
		Question q = oq.get(); //oq객체를 얻어와서
		this.questionRepository.delete(q); // 삭제
		assertEquals(1, this.questionRepository.count()); // 삭제하고 행이 2개에서 1개로 변경되었는지 확인
	}


	// Answer 엔티티에 대한 데이터 처리
	@Test
	@DisplayName("답변 데이터 생성 후 저장하기")
	void testJpa_9() {
		Optional<Question> oq = this.questionRepository.findById(2);
		assertTrue(oq.isPresent());
		Question q = oq.get();

		Answer a = new Answer();
		a.setContent("네 자동으로 생성됩니다.");
		a.setQuestion(q);  // 어떤 질문의 답변인지 알기위해서 Question 객체가 필요하다.
		a.setCreateDate(LocalDateTime.now());
		this.answerRepository.save(a);
	}

	//문제발생 부분
	@Test
	@DisplayName("답변에 연결된 질문 찾기")
	void testJpa_10() {
		Optional<Answer> oa = this.answerRepository.findById(1); // 1번 답변을 찾아서
		assertTrue(oa.isPresent()); // 답변이 존재하면
		Answer a = oa.get();
		assertEquals(2, a.getQuestion().getId()); //가져온 답변에 연결된 질문의 id가 2번인지 확인
	}

	@Test
	@Transactional // DB세션을 종료하지 않고 유지
	@DisplayName("질문에 달린 답변 찾기")
	void testJpa_11() {
		Optional<Question> oq = this.questionRepository.findById(2); //2번 질문을 가져온다
		assertTrue(oq.isPresent());
		Question q = oq.get();

		List<Answer> answerList = q.getAnswerList(); // 가져온 질문의 답변 리스트를 가져옴

		assertEquals(1, answerList.size()); // 답변 리스트의 크기가 1인지 체크
		assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent()); // 0번 질문이 일치하는지 체크
	}
	}