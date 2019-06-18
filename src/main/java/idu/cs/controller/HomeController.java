package idu.cs.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import idu.cs.domain.User;
import idu.cs.entity.UserEntity;
import idu.cs.service.QuestionService;
import idu.cs.service.UserService;

@Controller
public class HomeController {
	@Autowired UserService userService;
	@Autowired QuestionService questionService;
	
	/*
	 * return "A" = A에 해당하는 view를 보여주는 것
	 * return "redirect:/A" = A주소로 URL 요청을 다시 하는 것
	 */
	
	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/users/login-form")
	public String getLoginForm(Model model) {
		return "/users/login";
	}
	
	@PostMapping("/login")
	public String loginUser(@Valid UserEntity user, HttpSession session) { // 로그인할때 여기를 지나감
		System.out.println(user.getUserId() + "님 login 시도");
		User sessionUser = userService.getUserByUserId(user.getUserId());
		if(sessionUser == null) {
			System.out.println("id error");
			return "redirect:/users/login-form";
		}
		if(!sessionUser.getUserPw().equals(user.getUserPw())) {
			System.out.println(("pw error"));
			return "redirct:/users/login-form";
		}
		session.setAttribute("user", sessionUser);
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logoutUser(HttpSession session) {
		//session.removeAttribute("user");
		session.invalidate();  // 세션을 소멸
		return "redirect:/";
	}
	
	@GetMapping("/users/register-form")
	public String getRegForm(Model model) {
		return "/users/register";
	}
	
	@GetMapping("/questions/form")
	public String getQuestionForm(HttpSession session, Model model) {
		User writer = (User) session.getAttribute("user");
		model.addAttribute("writer", writer);
		return "/questions/register";
	}
	
}
