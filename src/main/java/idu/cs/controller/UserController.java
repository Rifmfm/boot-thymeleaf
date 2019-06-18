package idu.cs.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import idu.cs.domain.User;
import idu.cs.exception.ResourceNotFoundException;
import idu.cs.service.UserService;

@Controller
@RequestMapping("/users")
//@Component, @Controller, @Repository, @Service 표시된 클래스형 빈 객체를 스프링이 스캔하여 등록하고, @Autowired 등 요청시 주입 	
public class UserController {
	@Autowired UserService userService;	// 의존성 주입
	
	@GetMapping("")  // list
	public String getUsers(Model model, HttpSession session) {
		model.addAttribute("users", userService.getUsers());
		return "/users/list";
	}

	@PostMapping("")  // register, create
	public String createUser(@Valid User formUser, Model model) {  // @valid : 도메인 클래스에서 지정한 규칙에 어긋날 경우 돌려보냄
		userService.saveUser(formUser);
		model.addAttribute("user", formUser);
		return "redirect:/users";  // get 방식으로 redirect = 즉, list로 간다는 이야기
	}

	@GetMapping("/{id}")  // update_form
	public String getupdateForm(@PathVariable(value = "id") Long id, Model model) {
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "/users/info";
	}
	
	@PutMapping("/{id}")  // update
	public String updateUser(@PathVariable(value = "id") Long id, @Valid User formUser, Model model, HttpSession session) { 
		User user = userService.getUserById(id);
		user.setUserPw(formUser.getUserPw());
		user.setName(formUser.getName());
		user.setCompany(formUser.getCompany());
		userService.updateUser(user);
		session.setAttribute("user", user);
		return "redirect:/users";
	}
	
	@DeleteMapping("/{id}")  // delete
	public String deleteUser(@PathVariable(value = "id") Long userId, @Valid User formUser, Model model) {
		userService.deleteUser(formUser);
		model.addAttribute("name", formUser.getName());
		return "/users/delete";
	}
}
