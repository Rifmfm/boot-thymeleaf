package idu.cs.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import idu.cs.domain.Question;
import idu.cs.service.QuestionService;


@Controller
public class QuestionController {
	@Autowired QuestionService questionService;
	
	@GetMapping("/questions")
	public String getAllQuestion(Model model, HttpSession session) {
		List<Question> questions = questionService.getQuestions();
		model.addAttribute("questions", questions);
		return "/question/list";
	}
	
}
