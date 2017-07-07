package kr.co.lovelyzworld.users.service;

import kr.co.lovelyzworld.users.model.Users;

public interface IUserService {
	
	//로그인
	boolean checkPassword(String userId, String userPw);
	
	//회원가입
	void signUp(Users user);
	
	//ID 추출
	Users selectUserByUserId(String userId);
	
}
