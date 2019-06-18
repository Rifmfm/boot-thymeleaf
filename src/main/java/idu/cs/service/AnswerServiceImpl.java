package idu.cs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idu.cs.domain.Answer;
import idu.cs.entity.AnswerEntity;
import idu.cs.repository.AnswerRepository;

@Service("answerService")
public class AnswerServiceImpl implements AnswerService {
	@Autowired private AnswerRepository repository;
	
	@Override
	public Answer getAnswerById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Answer> getAnswers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveAnswer(Answer answer) {
		AnswerEntity entity = new AnswerEntity();
		entity.buildEntity(answer);
		repository.save(entity);
	}

	@Override
	public void updateAnswer(Answer answer) {
		AnswerEntity entity = new AnswerEntity();
		entity.buildEntity(answer);
		repository.save(entity);
	}

	@Override
	public void deleteAnswer(Answer answer) {
		AnswerEntity entity = new AnswerEntity();
		entity.buildEntity(answer);
		repository.delete(entity);
	}

}
