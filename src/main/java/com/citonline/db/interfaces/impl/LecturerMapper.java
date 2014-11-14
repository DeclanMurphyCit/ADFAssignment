/**
 * @author Fabien
 * 
 * @since 25 oct. 2014
 */
package com.citonline.db.interfaces.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.citonline.interfaces.impl.LecturerImpl;

public class LecturerMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		int id = rs.getInt("id_lecturer");
		String firstName = rs.getString("firstName");
		String lastName = rs.getString("lastName");
		String email = rs.getString("email");
		String phoneNumber = rs.getString("phoneNumber");
		String roomNumber = rs.getString("roomNumber");
		int idManagedProgram = rs.getInt("idManagedProgram");
		LecturerImpl lecturer = new LecturerImpl(id, firstName, lastName,
				email, phoneNumber, roomNumber, idManagedProgram);
		
		return lecturer;
	}


}
