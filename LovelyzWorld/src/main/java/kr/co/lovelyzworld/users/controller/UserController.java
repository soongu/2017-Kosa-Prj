package kr.co.lovelyzworld.users.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.lovelyzworld.users.model.Users;
import kr.co.lovelyzworld.users.service.IUserService;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	IUserService userService;
	
	
	//jsp 파일에서 ${} 사용을 위한 모델 생성.
	public Model callNameModel(String userId, Model model) {
		Users user = userService.selectUserByUserId(userId);
		String userName = user.getUserName();
		model.addAttribute("userName", userName);
		return model;
	}
	
	//에러 예외처리
		static final Logger logger = LoggerFactory.getLogger(UserController.class);

		public ModelAndView databaseError(HttpServletRequest req, Exception ex) {
			logger.error("Request: " + req.getRequestURL() + " raised " + ex);

			ModelAndView mav = new ModelAndView();
			mav.addObject("exception", ex);
			mav.addObject("url", req.getRequestURL());
			mav.setViewName("users/error");
			return mav;
		}
	
	//회원가입부분
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public String signUp(Model model) {
		model.addAttribute("users", new Users());
		return "/";
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signUp(@ModelAttribute("users") Users user, BindingResult result, Model model, RedirectAttributes redirectAttrs) {
		if(result.hasErrors()) {
			return "/";
		}
		try {
			userService.signUp(user);
		} catch(RuntimeException e) {
			redirectAttrs.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/";
	}
	
	//로그인
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String signIn() {
		return "/users/welcome";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String signIn(String userId, String userPw, HttpSession session, Model model) {
		try {
			Users user = userService.selectUserByUserId(userId);
			callNameModel(userId, model);
			model.addAttribute("users", user);
			
			if(userService.checkPassword(userId, userPw)) {
				session.setAttribute("userId", userId);
				return "users/welcome";
			}else {
				session.invalidate();
				return "users/PwWrong";
			}
		}catch (Exception e) {
			return "users/IdWrong";
		}
	}
	
	//로그아웃
	//로그아웃
		@RequestMapping("/logout")
		public String logout(HttpSession session) {		
			session.invalidate();
			return "redirect:/";
		}

}
