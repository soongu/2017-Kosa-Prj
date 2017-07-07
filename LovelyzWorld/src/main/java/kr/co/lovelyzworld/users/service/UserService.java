package kr.co.lovelyzworld.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.lovelyzworld.users.dao.IUserRepository;
import kr.co.lovelyzworld.users.model.Users;

@Service
public class UserService implements IUserService {
	
	@Autowired
	IUserRepository userRepository;
	
	@Override
	public boolean checkPassword(String userId, String userPw) {
		String pw = userRepository.getPassword(userId);
		if(pw!=null && pw.equals(userPw)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void signUp(Users user) {
		userRepository.signUp(user);
	}

	@Override
	public Users selectUserByUserId(String userId) {
		return userRepository.selectUserByUserId(userId);
	}
	
	

}
