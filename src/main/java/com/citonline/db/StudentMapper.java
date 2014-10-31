package com.citonline.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.citonline.domain.Student;

public class StudentMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		String firstName = rs.getString("firstName");
		String lastName = rs.getString("lastName");
		String studentNumber = rs.getString("studentNumber");
		String email = rs.getString("email");
		String phoneNumber = rs.getString("phoneNumber");	
		String addressLine1 = rs.getString("addressLine1");
		String addressLine2 = rs.getString("addressLine2");
		Student student = new Student(firstName, lastName, email, phoneNumber, 
				studentNumber,addressLine1,addressLine2);
		
		return student;
	}
}
