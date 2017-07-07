package kr.co.lovelyzworld.users.controller;

import javax.servlet.http.HttpServletRequest;

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
		return "users/insertfrom";
	}
	
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signUp(@ModelAttribute("users") Users user, BindingResult result, Model model, RedirectAttributes redirectAttrs) {
		if(result.hasErrors()) {
			return "users/insertform";
		}
		try {
			userService.signUp(user);
		} catch(RuntimeException e) {
			redirectAttrs.addFlashAttribute("message", e.getMessage());
		}
		return "redirect:/";
	}
	

}
