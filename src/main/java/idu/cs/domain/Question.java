package idu.cs.domain;

import java.time.LocalDateTime;
import java.util.List;

import idu.cs.entity.UserEntity;

public class Question {
	private Long id; 	
	private String title;
	private User writer;
	private String contents;
	private LocalDateTime createTime;
	private List<Comment> comments;
	
	public Question() {}  // 생성자 필수!!!!!!!!!!!! 생성자를 추가한 경우 디폴트 생성자를 생성해야함
	public Question(String title, User writer, String contents) {
		super();
		this.title = title;
		this.writer = writer;
		this.contents = contents;
		this.createTime = LocalDateTime.now();
	}
	
	public List<Comment> getComments() {
		return comments;
	}
	public void setAnswers(List<Comment> comments) {
		this.comments = comments;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public User getWriter() {
		return writer;
	}
	public void setWriter(User writer) {
		this.writer = writer;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public LocalDateTime getCreateTime() {
		return createTime;
	}
	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

}