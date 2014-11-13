package com.citonline.db.interfaces.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.citonline.interfaces.impl.StudentImpl;

public class StudentMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		int id = rs.getInt("id_Student");
		String firstName = rs.getString("firstName");
		String lastName = rs.getString("lastName");
		String studentNumber = rs.getString("studentNumber");
		String email = rs.getString("email");
		String phoneNumber = rs.getString("phoneNumber");	
		String addressLine1 = rs.getString("addressLine1");
		String addressLine2 = rs.getString("addressLine2");
		StudentImpl student = new StudentImpl(id, firstName, lastName, email, phoneNumber, 
				studentNumber,addressLine1,addressLine2);
		
		return student;
	}
}
