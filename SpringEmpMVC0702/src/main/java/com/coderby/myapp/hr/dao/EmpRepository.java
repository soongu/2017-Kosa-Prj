package com.coderby.myapp.hr.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.coderby.myapp.hr.model.Employee;

@Repository
public class EmpRepository implements IEmpRepository {

	@Autowired
	JdbcTemplate jdbcTemplate;

	private class EmpMapper implements RowMapper<Employee> {

		public Employee mapRow(ResultSet rs, int count) throws SQLException {
			Employee emp = new Employee();
			emp.setLoginId(rs.getString("login_id"));
			emp.setLoginPassword(rs.getString("login_password"));
			emp.setEmployeeId(rs.getInt("employee_id"));
			emp.setFirstName(rs.getString("first_name"));
			emp.setLastName(rs.getString("last_name"));
			emp.setEmail(rs.getString("email"));
			emp.setPhoneNumber(rs.getString("phone_number"));
			emp.setHireDate(rs.getDate("hire_date"));
			emp.setJobId(rs.getString("job_id"));
			emp.setSalary(rs.getDouble("salary"));
			emp.setCommissionPct(rs.getDouble("commission_pct"));
			emp.setManagerId(rs.getInt("manager_id"));
			emp.setDepartmentId(rs.getInt("department_id"));

			return emp;
		}
	}

	@Override
	public int getEmpCount() {
		String sql = "select count(*) from emp";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}

	@Override
	public int getEmpCount(int deptid) {
		String sql = "select count(*) from emp where department_id=?";
		return jdbcTemplate.queryForObject(sql, Integer.class, deptid);
	}

	@Override
	public Employee getEmpInfoByloginId(String loginId) {
		String sql = "select * from emp where login_id=?";
		return jdbcTemplate.queryForObject(sql, new EmpMapper(), loginId);
	}



	@Override
	public Employee getEmpInfoByempId(int empId) {
		String sql = "select * from emp where employee_id=?";
		return jdbcTemplate.queryForObject(sql, new EmpMapper(), empId);
	}

	@Override
	public List<Employee> getEmpList() {
		String sql = "select * from emp";
		return jdbcTemplate.query(sql, new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int count) throws SQLException {
				Employee emp = new Employee();
				emp.setLoginId(rs.getString("login_id"));
				emp.setLoginPassword(rs.getString("login_password"));
				emp.setEmployeeId(rs.getInt("employee_id"));
				emp.setFirstName(rs.getString("first_name"));
				emp.setLastName(rs.getString("last_name"));
				emp.setEmail(rs.getString("email"));
				emp.setPhoneNumber(rs.getString("phone_number"));
				emp.setHireDate(rs.getDate("hire_date"));
				emp.setJobId(rs.getString("job_id"));
				emp.setSalary(rs.getDouble("salary"));
				emp.setCommissionPct(rs.getDouble("commission_pct"));
				emp.setManagerId(rs.getInt("manager_id"));
				emp.setDepartmentId(rs.getInt("department_id"));
				return emp;
			}

		});
	}

	@Override
	public void insertEmp(Employee emp) {
		String sql = "insert into emp (login_id, login_password, employee_id, first_name,"
				+ "last_name, email, phone_number, hire_date,"
				+ "job_id, salary, commission_pct, manager_id,"
				+ "department_id) values (?,?,?,?,?,?,?,sysdate(),?,?,?,?,?)";
		jdbcTemplate.update(sql,
				emp.getLoginId(),
				emp.getLoginPassword(),
				emp.getEmployeeId(),
				emp.getFirstName(),
				emp.getLastName(),
				emp.getEmail(),
				emp.getPhoneNumber(),
				emp.getJobId(),
				emp.getSalary(),
				emp.getCommissionPct(),
				emp.getManagerId(),
				emp.getDepartmentId()
				);
	}


	@Override
	public void updateEmp(Employee emp) {
		String sql = "update emp set login_password=?, first_name=?,"
				+ "last_name=?, email=?, phone_number=?, "
				+ "job_id=?, salary=?, "
				+ "commission_pct=?, manager_id=?,"
				+ "department_id=? where login_id=?";
		jdbcTemplate.update(sql,
				emp.getLoginPassword(),
				emp.getFirstName(),
				emp.getLastName(),
				emp.getEmail(),
				emp.getPhoneNumber(),				
				emp.getJobId(),
				emp.getSalary(),
				emp.getCommissionPct(),
				emp.getManagerId(),
				emp.getDepartmentId(),
				emp.getLoginId());
	}

	@Override
	public void deleteEmp(String loginId, String loginPassword) {
		String sql = "delete from emp where login_id=? and login_password=?";
		jdbcTemplate.update(sql, loginId, loginPassword);

	}



	@Override
	public String getPassword(String loginId) {
		String sql = "select login_password from emp where login_id=?";
		return jdbcTemplate.queryForObject(sql, String.class, loginId);
	}
	
	

	@Override
	public boolean duplicateIdCheck(String loginId) {	
		String sql = "SELECT login_ID FROM emp WHERE login_ID=?";		
		return jdbcTemplate.queryForObject(sql, boolean.class, loginId);
	}

	@Override
	public List<Map<Integer, String>> getAllDeptId() {
		String sql = "select department_id, department_name from departments";
		return jdbcTemplate.query(sql, new RowMapper<Map<Integer, String>>(){

			@Override
			public Map<Integer, String> mapRow(ResultSet rs, int count) throws SQLException {
				Map<Integer, String> deptMap = new HashMap<Integer, String>();
				deptMap.put(rs.getInt("department_id"), rs.getString("department_name"));
				return deptMap;
			}

		});
	}

	@Override
	public List<Map<String,Object>> getAllJobId() {
		String sql = "select job_id as jobId, job_title as title from jobs";
		return jdbcTemplate.queryForList(sql);
	}

	@Override
	public List<Map<String,Object>> getAllManagerId() {
		String sql = "select d.manager_id as managerId, e.first_name as firstName"
				+ "from departments d join employees e on d.manager_id = e.employee_id"
				+ "order by d.manager_id";
		return jdbcTemplate.queryForList(sql);
	}

}
