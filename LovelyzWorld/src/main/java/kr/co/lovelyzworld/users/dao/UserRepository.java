package kr.co.lovelyzworld.users.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.co.lovelyzworld.users.model.Users;

@Repository
public class UserRepository implements IUserRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public class UserMapper implements RowMapper<Users> {

		@Override
		public Users mapRow(ResultSet rs, int count) throws SQLException {
			Users user = new Users();
			user.setUserId(rs.getString("user_id"));
			user.setUserPw(rs.getString("user_pw"));
			user.setUserName(rs.getString("user_name"));
			user.setUserSex(rs.getString("user_sex"));
			user.setUserEmail(rs.getString("user_email"));
			user.setUserPwHint(rs.getString("user_pw_hint"));
			user.setSignupDate(rs.getDate("signup_date"));
			return user;
		}
				
	}
	
	@Override
	public String getPassword(String userId) {
		String sql = "select user_pw from love_users where user_id=?";
		return jdbcTemplate.queryForObject(sql, String.class, userId);
	}

	@Override
	public void signUp(Users user) {
		String sql = "insert into love_users values (?,?,?,?,?,?,sysdate())";
		jdbcTemplate.update(sql,
				user.getUserId(),
				user.getUserPw(),
				user.getUserName(),
				user.getUserSex(),
				user.getUserEmail(),
				user.getUserPwHint()
				);
	}

}
