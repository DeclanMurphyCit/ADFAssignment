package com.citonline.db.interfaces.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import com.citonline.domain.Program;
import com.citonline.domain.Semester;

/*
 * Author: Tim Wallace
 * Date: 04/11/14
 * 
 * Description: Program mapper
 * 
 * Inputs: Program String programName, String programCode
 * 
 * Expected Outputs: create, delete, update, return Program
 */

public class ProgramMapper implements RowMapper {

//String programName, String programCode
	
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		int programID = rs.getInt("id_program");
		String programName = rs.getString("program_name");
		String programCode = rs.getString("program_code");
	
		Program program = new Program(programID, programName, programCode);
		
		return program;
	}
}
