package com.cit.online.db.interfaces.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.citonline.db.interfaces.ModuleDAO;
import com.citonline.domain.Module;

/*
 * Author: Tim Wallace
 * Date: 03/11/14
 * 
 * Description: Module Jdbc template, all of the queries for the database
 * 
 * Inputs: Module semester, code, crn, name
 * 
 * Expected Outputs: create, delete,modify, return modules in the database
 */

public class ModuleJdbcTemplate implements ModuleDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public void createModule(String code, String crn, String name, int semester) {
		
		String SQL = "INSERT INTO Module (code, crn, name, semester) "
				+ "VALUES(?, ?, ?, ?, ?)";
		
		jdbcTemplateObject.update(SQL, new Object[] { code, crn, name, semester});
		
		System.out.println("Created Module Name = " + name +
				"\nCRN = " + crn + ", code= " + code + ", semester = " + semester);
	}

	@Override
	public void deleteModule(String crn) {
		String SQL = "delete from Module where crn = ?";
		jdbcTemplateObject.update(SQL, new Object[] {crn});
		System.out.println("Deleted module where crn = " + crn );
		
	}



	@Override
	public Module getModule(String crn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Module> listModules() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateModule(String code, String crn, String name, int semester) {
		// TODO Auto-generated method stub
		
	}

}
