package idu.cs.service;

import java.util.List;

import idu.cs.domain.Comment;

public interface CommentService {
	Comment getCommentById(long id); // primary key인 id 값을 가진 질문 조회
	List<Comment> getComments(); // 모든 질문  조회
	
	void saveComment(Comment comment); // 질문 생성
	void updateComment(Comment comment); // 질문 수정
	void deleteComment(Comment comment); // 질문 삭제
}
