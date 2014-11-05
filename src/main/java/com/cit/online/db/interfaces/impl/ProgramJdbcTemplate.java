package com.cit.online.db.interfaces.impl;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.citonline.db.interfaces.ProgramDAO;
import com.citonline.domain.Program;
import com.citonline.domain.Semester;

/*
 * Author: Tim Wallace
 * Date: 04/11/14
 * 
 * Description: Program Jdbc Template
 * 
 * Inputs: Program String programName, String programCode, ArrayList<Semester> semesterList
 * 
 * Expected Outputs: create, delete, update, list semesters, return Program sql
 */

@Repository
public class ProgramJdbcTemplate implements ProgramDAO {

	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void createProgram(String programName, String programCode) {
		
		String SQL = "INSERT INTO Program (programName, programCode, semesterList) "
				+ "VALUES(?, ?, ?)";
		
		jdbcTemplateObject.update(SQL, new Object[] { programName, programCode});
		
		System.out.println("Created Program Name = " + programName + 
				"\nProgram Code = " + programCode );	
	}
	
	@Override
	public void deleteProgram(Integer id) {
		String SQL = "delete from Program where id_program= ?";
		jdbcTemplateObject.update(SQL, new Object[] {id});
		System.out.println("Deleted program where id_program = " + id );		
	}

	@Override
	public ArrayList<Semester> listSemesters() {
		String SQL = "select * from semester";
		ArrayList<Semester> semesterList = (ArrayList<Semester>) jdbcTemplateObject.query(SQL, 
						new ProgramMapper());
		return semesterList;
	}	
	
	@Override
	public Program getProgram(Integer id) {
		String SQL = "select * from Program where id_program = ?";
		Program program = (Program) jdbcTemplateObject.queryForObject(SQL, 
						new Object[]{id}, new ProgramMapper());
		program.setSemesterList(listSemesters());
		return program;
	}

	@Override
	public void updateProgramName(Integer id, String programName) {
		String SQL = "update program set program_name = ? where id_program = ?";
		jdbcTemplateObject.update(SQL, new Object[] {programName,id});
		System.out.println("Updated program_name to " + programName + " where id_program = " + id );
		
	}

	@Override
	public void updateProgramCode(Integer id, String programCode) {
		String SQL = "update program set program_code = ? where id_program = ?";
		jdbcTemplateObject.update(SQL, new Object[] {programCode,id});
		System.out.println("Updated program_code to " + programCode + " where id_program = " + id );
		
	}

}
