package com.coderby.myapp.hr.controller;

import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coderby.myapp.hr.model.Employee;
import com.coderby.myapp.hr.service.IEmpService;


@Controller
@RequestMapping("/hr")
public class EmpController {

	@Autowired
	IEmpService empService;


	//에러 예외처리
	static final Logger logger = LoggerFactory.getLogger(EmpController.class);

	public ModelAndView databaseError(HttpServletRequest req, Exception ex) {
		logger.error("Request: " + req.getRequestURL() + " raised " + ex);

		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", ex);
		mav.addObject("url", req.getRequestURL());
		mav.setViewName("hr/error");
		return mav;
	}

	//jsp파일에서 ${empName} 사용을 위한 모델 생성.
	public Model callNameModel(String loginId, Model model) {
		Employee emp = empService.getEmpInfoByloginId(loginId);
		String firstName = emp.getFirstName();
		String lastName = emp.getLastName();
		model.addAttribute("firstName", firstName);
		model.addAttribute("lastName", lastName);
		return model;
	}
	//회원 조회 부분
	@RequestMapping(value = "view/{empId}", method = RequestMethod.GET)
	public String empInfo(@PathVariable int empId, Model model) {
		model.addAttribute("emps", empService.getEmpInfoByempId(empId));
		return "hr/view";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String empList(Model model) {
		List<Employee> empList = empService.getEmpList();
		model.addAttribute("empList", empList);
		return "hr/list";
	}


	// 회원 가입 부분

	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String signUp(Model model) {
		model.addAttribute("emp", new Employee());
		model.addAttribute("DeptId", empService.getAllDeptId());
		model.addAttribute("JobId", empService.getAllJobId());
		return "hr/insertform";
	}

	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String signUp(@ModelAttribute("emp") @Valid Employee emp,
			BindingResult result, Model model, RedirectAttributes redirectAttrs) {
		if(result.hasErrors()) {
			model.addAttribute("DeptId", empService.getAllDeptId());
			model.addAttribute("JobId", empService.getAllJobId());
			return "hr/insertform";
		}
		try {
			empService.insertEmp(emp);			
		} catch(RuntimeException e) {
			redirectAttrs.addFlashAttribute("message", e.getMessage());	
		}
		return "redirect:/";
	}

	// 회원 정보 수정
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String updateEmp(HttpSession session, Model model) {
		String loginId = (String)session.getAttribute("loginId");
		model.addAttribute("emps", empService.getEmpInfoByloginId(loginId));
		model.addAttribute("DeptId", empService.getAllDeptId());
		model.addAttribute("JobId", empService.getAllJobId());
		return "hr/updateform";
	}


	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String updateEmp(Employee emp, Model model) {
		try {
			empService.updateEmp(emp);
			return "redirect:/hr/list";
		} catch(Exception e) {
			model.addAttribute("exception", e);
			return "hr/error";
		}

	}

	//회원 탈퇴
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String deleteEmp(HttpSession session, Model model) {
		String loginId = (String)session.getAttribute("loginId");
		model.addAttribute("emps", empService.getEmpInfoByloginId(loginId));		
		return "/hr/deleteform";
	}

	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String deleteEmp(String loginId, String loginPassword, Model model) {
		callNameModel(loginId, model);		
		try {
			if(empService.checkPassword(loginId, loginPassword)) {				
				empService.deleteEmp(loginId, loginPassword);;
				return "hr/deleteok";
			}else {
				return "hr/deletefail";
			}

		} catch(Exception e) {
			model.addAttribute("exception", e);
			return "hr/error";
		}

	}


	//로그인 
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String signIn() {	
		return "/hr/welcome";
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String signIn(String loginId, String loginPassword, HttpSession session, Model model) {
		try{
			Employee emp = empService.getEmpInfoByloginId(loginId);
			callNameModel(loginId, model);
			model.addAttribute("emps", emp);

			if(empService.checkPassword(loginId, loginPassword)) {			
				session.setAttribute("loginId", loginId);			
				return "hr/welcome";
			}else{
				session.invalidate(); 
				return "hr/loginPwWrong";
			}
		}catch (Exception e){
			return "hr/loginIdWrong";
		}



	}



	//로그아웃
	@RequestMapping("/logout")
	public String logout(HttpSession session) {		
		session.invalidate();
		return "redirect:/";
	}


}
