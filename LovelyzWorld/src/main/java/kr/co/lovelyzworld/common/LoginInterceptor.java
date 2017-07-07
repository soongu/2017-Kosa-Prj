package kr.co.lovelyzworld.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		String contextName = req.getContextPath();
		HttpSession session = req.getSession();
		String userId = (String)session.getAttribute("userId");
		if(userId==null || userId.equals("")) {
			res.sendRedirect(contextName + "/");
			return false;
		}
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse res, Object handler, ModelAndView mav)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse res, Object handler, Exception e)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	
	
}
