package com.citonline.db.interfaces;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.citonline.domain.Program;
import com.citonline.domain.Semester;

/*
 * Author: Tim Wallace
 * Date: 04/11/14
 * 
 * Description: Program DAO
 * 
 * Inputs: Program String programName, String programCode, ArrayList<Semester> semesterList
 * 
 * Expected Outputs: create, delete, update, list semesters, return Program
 */

public interface ProgramDAO {

	public void setDataSource(DataSource dataSource);
	
	public void createProgram(String programName, String programCode);
	
	public void deleteProgram(Integer id);	
		
	public void updateProgramName(Integer id, String programName);
	
	public void updateProgramCode(Integer id, String programCode);
	
	public List<Semester> listSemesters();

	Program getProgram(Integer id);
  
	
}