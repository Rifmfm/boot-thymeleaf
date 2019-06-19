package idu.cs.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import idu.cs.domain.Comment;
import idu.cs.domain.Question;
import idu.cs.domain.User;
import idu.cs.service.CommentService;
import idu.cs.service.QuestionService;

@Controller
@RequestMapping("/questions/{questionId}/comment")
public class CommentController {
	@Autowired CommentService commentService;
	@Autowired QuestionService questionService;
	
	@PostMapping("")
	// public String createUser(Answer answer, Model model, HttpSession session) {
	public String createComment(@PathVariable Long questionId, String contents,HttpSession session) {
		User sessionUser = (User) session.getAttribute("user");
		Question question = questionService.getQuestionById(questionId);
		Comment newComment = new Comment(sessionUser, question, contents);
		commentService.saveComment(newComment);
		return String.format("redirect:/questions/%d", questionId);
	}
	
	@DeleteMapping("/{id}")  //delete
	public String deleteCommentById(@PathVariable Long questionId, @PathVariable(value = "id") Long id, Model model) {
		// Question question = questionService.getQuestionById(questionId);
		System.out.println("여기까지");
		Comment comment = commentService.getCommentById(id);
		commentService.deleteComment(comment);
		return String.format("redirect:/questions/%d", questionId);
	}
}
