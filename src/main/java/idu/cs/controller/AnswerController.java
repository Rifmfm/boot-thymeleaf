package idu.cs.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import idu.cs.domain.Answer;
import idu.cs.domain.Question;
import idu.cs.domain.User;
import idu.cs.service.AnswerService;
import idu.cs.service.QuestionService;

@Controller
@RequestMapping("/questions/{questionId}/answers")
public class AnswerController {
	@Autowired AnswerService answerService;
	@Autowired QuestionService questionService;
	
	@PostMapping("")
	// public String createUser(Answer answer, Model model, HttpSession session) {
	public String createAnswer(@PathVariable Long questionId, String contents,HttpSession session) {
		User sessionUser = (User) session.getAttribute("user");
		Question question = questionService.getQuestionById(questionId);
		Answer newAnswer = new Answer(sessionUser, question, contents);
		answerService.saveAnswer(newAnswer);
		return String.format("redirect:/questions/%d", questionId);
	}
}
