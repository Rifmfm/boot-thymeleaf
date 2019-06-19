package idu.cs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import idu.cs.domain.Comment;
import idu.cs.domain.Question;
import idu.cs.entity.CommentEntity;
import idu.cs.entity.QuestionEntity;
import idu.cs.repository.QuestionRepository;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired QuestionRepository repository;
	
	@Override
	public Question getQuestionById(long id) {
		QuestionEntity entity = repository.findById(id).get();
		Question question = entity.buildDomain();
		
		// QuestionEntity객체로부터 AnswerEntity Arraylist를 가져와서 Domain(?) Arraylist로 변환
		List<Comment> answerList = new ArrayList<Comment>();
		for(CommentEntity answerEntity : entity.getAnswers())
			answerList.add(answerEntity.buildDomain());
		question.setAnswers(answerList);
		return question;
	}

	@Override
	public List<Question> getQuestions() {  // Repository로 부터 모든 자료를 가져와 Enitiy 리스트에 저장
		List<QuestionEntity> entities = repository.findAll(new Sort(Sort.Direction.DESC, "createTime"));
		List<Question> questions = new ArrayList<Question>();
		for(QuestionEntity entity : entities) {
			Question question = entity.buildDomain();
			questions.add(question);
		}
		return questions;
	}

	@Override
	public List<Question> getQuestionsByUser(String user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveQuestion(Question question) {
		QuestionEntity entity = new QuestionEntity();
		entity.buildEntity(question);
		repository.save(entity);
	}

	@Override
	public void updateQuestion(Question question) {
		QuestionEntity entity = new QuestionEntity();
		entity.buildEntity(question);
		repository.save(entity);
	}

	@Override
	public void deleteQuestion(Question question) {
		QuestionEntity entity = new QuestionEntity();
		entity.buildEntity(question);
		repository.delete(entity);
	}

}
