package idu.cs.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import idu.cs.domain.Answer;
import idu.cs.domain.Question;
import idu.cs.domain.User;
import idu.cs.service.QuestionService;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	@Autowired QuestionService questionService;
	
	@GetMapping("")  // list
	public String getAllQuestion(Model model, HttpSession session) {
		List<Question> questions = questionService.getQuestions();
		model.addAttribute("questions", questions);
		return "/questions/list";
	}
	
	@PostMapping("")  // create
	// public String createUser(Question question, Model model, HttpSession session) {
	public String createQuestion(String title, String contents, Model model, HttpSession session) {
		User sessionUser = (User) session.getAttribute("user");
		Question newQuestion = new Question(title, sessionUser, contents);
		// Question newQuestion = new Question(question.getTitle(), writer, question.getContents());	
		questionService.saveQuestion(newQuestion);
		return "redirect:/questions";
	}
	
	@GetMapping("/{id}")  // view, info Question
	public String getQuestion(@PathVariable(value = "id") Long id, Model model) {
		Question question = questionService.getQuestionById(id);  // getQuestionById에서 answer데이터도 같이 불러옴
		model.addAttribute("question", question);
		return "/questions/info";
	}
	
	@GetMapping("/{id}/form")  // update_form
	public String getupdateForm(@PathVariable(value="id") Long id, Model model) {
		Question question = questionService.getQuestionById(id);
		model.addAttribute("question", question);
		return "/questions/info";
	}
	
	@PutMapping("/{id}")  // update
	public String updateQuestion(@PathVariable(value = "id") Long id, String title, String contents, Model model) {
		Question question = questionService.getQuestionById(id);
		questionService.updateQuestion(question);		
		return "redirect:/questions/" + id;
	}
	
	@DeleteMapping("/{id}")  //delete
	public String deleteQuestionById(@PathVariable(value = "id") Long id, Model model) {
		Question question = questionService.getQuestionById(id);
		questionService.deleteQuestion(question);
		model.addAttribute("userId", question.getWriter().getUserId());
		return "/questions/delete";
	}
}
