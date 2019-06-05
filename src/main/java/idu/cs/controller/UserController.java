package idu.cs.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import idu.cs.entity.UserEntity;
import idu.cs.exception.ResourceNotFoundException;
import idu.cs.repository.UserRepository;
import idu.cs.service.UserService;

@Controller  // Spring Framework에게 이 클래스로 부터 작성된 객체는 Controller 역할을 함을 알려줌
// Spring이 이 클래스로부터 Bean 객체를 생성해서 등록할 수 있음
public class UserController {
	@Autowired UserService userService;
	//@Autowired UserRepository userRepo; // Dependency Injection
	// UserRepository를 userRepo에 Autowired해줘
	
	// 전체 돌아가는 상황을 알자!
	// 뭘 누르면 뭐로 가고 그래서 뭐가 나오는지? 돌아가는 느낌 적인 느낌 느낌!
	
	@GetMapping("/")
	public String home(Model model) {
		return "index";
	}
	@GetMapping("/user-login-form")
	public String getLoginForm(Model model) {
		return "login";
	}
/*
	@PostMapping("/login")
	public String loginUser(@Valid UserEntity user, HttpSession session) { // 로그인할때 여기를 지나감
		System.out.println("님 login 성공");
		UserEntity sessionUser = userRepo.findByUserId(user.getUserId());
		if(sessionUser == null) {
			System.out.println("id error");
			return "redirect:/user-login-form";
		}
		if(!sessionUser.getUserPw().equals(user.getUserPw())) {
			System.out.println(("pw error"));
			return "redirct:/user-login-form";
		}
		session.setAttribute("user", sessionUser);
		return "redirect:/";
	}
	@GetMapping("/logout")
	public String logoutUser(HttpSession session) {
		session.removeAttribute("user");
		// session.invalidate();
		return "redirect:/";
	}
	@GetMapping("/user-register-form")
	public String getRegForm(Model model) {
		return "register";
	}
	@GetMapping("/user-update-form")
	public String updateForm(Model model, HttpSession session) {
		return "update";
	}*/

	// 이러한 방식이 더 좋아서 바꾸느라고 다 주석처리 해버림
	@GetMapping("/users")
	public String getAllUser(Model model, HttpSession session) {
		model.addAttribute("users", userService.getUsers());
		return "userlist";
	}
	/*
	@GetMapping("/users")
	public String getAllUser(Model model) {
		model.addAttribute("users", userService.getUsers());
		return "/users/list";
	}	*/
	/*
	@PostMapping("/users")
	public String createUser(@Valid UserEntity user, Model model) {  // User 앞 @RequestBody 제거
		if(userRepo.save(user)!=null) {
			System.out.println("Database 등록 성공");
		}
		else 
			System.out.println("Database 등록 실패");
		model.addAttribute("users", userRepo.findAll());
		return "redirect:/users";
	}
	@GetMapping("/users/{id}")  // 오류 해결방법 찾기 : ctrl + 1
	public String getUserById(@PathVariable(value = "id") Long userId, Model model) throws ResourceNotFoundException {
		UserEntity user = userRepo.findById(userId).get(); 
				//orElseThrow는 에러처리를 내가 지정 가능<-.orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
		//() -> new Resource..." + userId) = 람다식 (함수형 인터페이스)
		//model.addAttribute("id", user.getId());  // user.getID = userId  아이디를 넘김
		model.addAttribute("user", user);  // 하나의 객체로 넘기기
		return "user";
		//return ResponseEntity.ok().body(user);
	}  
	@GetMapping("/users/fn")
	public String getUserByName(@Param(value="name") String name, Model model) {
		List<UserEntity> users = userRepo.findByName(name);
		model.addAttribute("users",users);
		return "userlist";
	}
	
	/* 
	 * 메소드 방식에 따라 다르게 동작 시킬 것이다. - Restfull방식
	 * 자원 중심 : 자원의 현상태를 전송하자  ex) get방식, put방식에 따라 다르게 동작
	 * 중요한 이유 : 이게 이름?(url?)을 다르게 해서 쓰면 update를 썼나? upload를 썼나? 헷갈릴 수 있다.
	 *//*
	@PutMapping("/users/{id}")  // @PatchMapping : 수정된 것만 고치도록 하는 친구
	public String updateUser(@PathVariable(value = "id") Long userId, @Valid UserEntity userDetails, Model model, HttpSession session) { 
		UserEntity user = userRepo.findById(userId).get();  // user는 DB로 부터 읽어온 객체
		user.setUserId(userDetails.getUserId());
		user.setUserPw(userDetails.getUserPw());
		user.setName(userDetails.getName());  // userDetails는 전송한 객체
		user.setCompany(userDetails.getCompany());
		userRepo.save(user);
		session.setAttribute("user", user);  
		/* 
		 * 
		 * 
		 * 일단은 세션을 덮는다.!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		 *
		 *
		 *//*
		return "redirect:/users";
	}
	
	@DeleteMapping("/users/{id}")
	public String deleteUser(@PathVariable(value = "id") Long userId, Model model) {
	// @PathVariable(value = "id") Long userId = id를 가져오기
		UserEntity user = userRepo.findById(userId).get();
		userRepo.delete(user);
		model.addAttribute("name", user.getName());
		return "user-deleted";
	}*/
}
