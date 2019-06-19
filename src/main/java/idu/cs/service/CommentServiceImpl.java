package idu.cs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idu.cs.domain.Comment;
import idu.cs.entity.CommentEntity;
import idu.cs.repository.CommentRepository;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
	@Autowired private CommentRepository repository;
	
	@Override
	public Comment getCommentById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Comment> getComments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveComment(Comment comment) {
		CommentEntity entity = new CommentEntity();
		entity.buildEntity(comment);
		repository.save(entity);
	}

	@Override
	public void updateComment(Comment comment) {
		CommentEntity entity = new CommentEntity();
		entity.buildEntity(comment);
		repository.save(entity);
	}

	@Override
	public void deleteComment(Comment comment) {
		CommentEntity entity = new CommentEntity();
		entity.buildEntity(comment);
		repository.delete(entity);
	}

}
