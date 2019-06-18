package idu.cs.service;

import java.util.List;

import idu.cs.domain.Answer;

public interface AnswerService {
	Answer getAnswerById(long id); // primary key인 id 값을 가진 질문 조회
	List<Answer> getAnswers(); // 모든 질문  조회
	
	void saveAnswer(Answer answer); // 질문 생성
	void updateAnswer(Answer answer); // 질문 수정
	void deleteAnswer(Answer answer); // 질문 삭제
}