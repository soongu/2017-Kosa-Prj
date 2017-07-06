package com.coderby.myapp.hr.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderby.myapp.hr.dao.IEmpRepository;
import com.coderby.myapp.hr.model.Employee;

@Service
public class EmpService implements IEmpService {

	@Autowired
	IEmpRepository empRepository;

	@Override
	public Employee getEmpInfoByloginId(String loginId) {
		return empRepository.getEmpInfoByloginId(loginId);
	}


	@Override
	public Employee getEmpInfoByempId(int empId) {

		return empRepository.getEmpInfoByempId(empId);
	}


	@Override
	public List<Employee> getEmpList() {
		return empRepository.getEmpList();
	}

	@Override
	public void insertEmp(Employee emp) {
		empRepository.insertEmp(emp);
	}

	@Override
	public void updateEmp(Employee emp) {
		empRepository.updateEmp(emp);
	}

	@Override
	public void deleteEmp(String loginId, String loginPassword) {
		empRepository.deleteEmp(loginId, loginPassword);
	}

	@Override
	public boolean checkPassword(String loginId, String loginPassword) {

		String pw = empRepository.getPassword(loginId);
		if(pw != null && pw.equals(loginPassword)) {
			return true;	
		}else{
			return false;
		}

	}
	
	

	@Override
	public boolean checkId(String loginId) {
		return empRepository.duplicateIdCheck(loginId);
	}


	@Override
	public List<Map<Integer, String>> getAllDeptId() {

		return empRepository.getAllDeptId();
	}

	@Override
	public List<Map<String, Object>> getAllJobId() {
		return empRepository.getAllJobId();
	}

	@Override
	public List<Map<String, Object>> getAllManagerId() {

		return empRepository.getAllManagerId();
	}

}



