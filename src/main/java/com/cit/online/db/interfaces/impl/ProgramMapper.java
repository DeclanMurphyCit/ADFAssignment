package com.cit.online.db.interfaces.impl;

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
		String programName = rs.getString("programName");
		String programCode = rs.getString("programCode");
	
		Program program = new Program(rowNum, programName, programCode);
		
		return program;
	}
}
