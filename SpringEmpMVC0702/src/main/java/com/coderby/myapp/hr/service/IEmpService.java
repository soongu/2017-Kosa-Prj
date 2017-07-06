package com.coderby.myapp.hr.service;

import java.util.List;
import java.util.Map;

import com.coderby.myapp.hr.model.Employee;

public interface IEmpService {
	//사원 정보 조회
	Employee getEmpInfoByloginId(String loginId);
	Employee getEmpInfoByempId(int empId);
	//사원 목록 조회
	List<Employee> getEmpList();

	//사원 정보 입력
	void insertEmp(Employee emp);
	//사원 정보 수정
	void updateEmp(Employee emp);
	//사원 정보 삭제
	void deleteEmp(String loginId, String loginPassword);
	//login
	boolean checkPassword(String loginId, String loginPassword);
	//id중복체크
	boolean checkId(String loginId);
	
	
	//모든 부서 조회
	List<Map<Integer, String>> getAllDeptId();
	//모든 직무아이디 조회
	List<Map<String, Object>> getAllJobId();
	//모든 매니저 아이디 조회
	List<Map<String, Object>> getAllManagerId();

}
