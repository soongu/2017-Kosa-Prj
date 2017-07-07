package kr.co.lovelyzworld.users.model;

import java.sql.Date;

public class Users {
	
	private String userId;
	private String userPw;
	private String userName;
	private String userSex;
	private String userEmail;
	private String userPwHint;
	private Date signupDate;
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPwHint() {
		return userPwHint;
	}
	public void setUserPwHint(String userPwHint) {
		this.userPwHint = userPwHint;
	}
	public Date getSignupDate() {
		return signupDate;
	}
	public void setSignupDate(Date signupDate) {
		this.signupDate = signupDate;
	}
	
	@Override
	public String toString() {
		return "Users [userId=" + userId + ", userPw=" + userPw + ", userName=" + userName + ", userSex=" + userSex
				+ ", userEmail=" + userEmail + ", userPwHint=" + userPwHint + ", signupDate=" + signupDate + "]";
	}
	
	
}
