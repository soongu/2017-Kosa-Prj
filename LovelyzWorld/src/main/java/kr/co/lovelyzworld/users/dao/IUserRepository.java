package kr.co.lovelyzworld.users.dao;

import kr.co.lovelyzworld.users.model.Users;

public interface IUserRepository {
	
	//로그인
	String getPassword(String userId);
	
	//회원가입
	void signUp(Users user);
	
	//ID 추출
	Users selectUserByUserId(String userId);
}
