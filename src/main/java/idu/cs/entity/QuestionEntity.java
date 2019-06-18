package idu.cs.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import idu.cs.domain.Question;

@Entity
@Table(name = "question")
public class QuestionEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;  // primary key
	
	private String title;
	
	@ManyToOne  // N:1 관계(단방향) : 사람 한명이 여러개의 Question 작성 가능
	@JoinColumn(name="fk_question_writer")  // 외래키 fk_question_writer와 매핑 : 로그인한 사람만 작성 가능
	private UserEntity writer;   // !!!!UserEntitiy다. 자동으로 값이 들어감
	
	@OneToMany(mappedBy="question")  // 여러개의 answer값을 가질 수 있다.
	@OrderBy("createTime DESC")
	private List<AnswerEntity> answers = new ArrayList<AnswerEntity>();
	
	@Lob
	private String contents;
	private LocalDateTime createTime;
	
	public Question buildDomain() {  
		Question question = new Question();
		question.setId(id);
		question.setTitle(title);
		question.setWriter(writer.buildDomain());
		question.setContents(contents);
		question.setCreateTime(createTime);
		return question;
	}
	
	public void buildEntity(Question question) {
		id = question.getId();
		title = question.getTitle();
		
		UserEntity entity = new UserEntity();
		entity.buildEntity(question.getWriter());
		
		writer = entity;
		
		contents = question.getContents();
		createTime = question.getCreateTime();
	}
	
	public List<AnswerEntity> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerEntity> answers) {
		this.answers = answers;
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
	public UserEntity getWriter() {
		return writer;
	}
	public void setWriter(UserEntity writer) {
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