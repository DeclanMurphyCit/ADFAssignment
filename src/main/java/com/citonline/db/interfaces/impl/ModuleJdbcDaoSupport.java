package com.citonline.db.interfaces.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.citonline.db.interfaces.ModuleDAO;
import com.citonline.domain.Module;
import com.citonline.domain.Program;
import com.citonline.interfaces.impl.LecturerImpl;
import com.citonline.interfaces.impl.ModuleImpl;
import com.citonline.interfaces.impl.StudentImpl;

/*
 * Author: Tim Wallace
 * Date: 03/11/14
 * 
 * Description: Module Jdbc template, all of the queries for the database
 * 
 * Inputs: Module semester, code, crn, name
 * 
 * Expected Outputs: create, delete, modify, return modules in the database
 */

 @Repository
 public class ModuleJdbcDaoSupport extends JdbcDaoSupport implements ModuleDAO {

	 @Autowired
     private DataSource dataSource;
     @PostConstruct
     private void initialize() {
            setDataSource(dataSource);
     }
	 

	@Override
	public void createModule(String code, String crn, String name, int semester) {
		
		String SQL = "INSERT INTO Module (code, crn, name, semester) "
				+ "VALUES(?, ?, ?, ?)";
		
		getJdbcTemplate().update(SQL, new Object[] { code, crn, name, semester});
		
		System.out.println("Created Module Name = " + name +
				"\nCRN = " + crn + ", code= " + code + ", Semester = " + semester);
	}

	@Override
	public void deleteModule(String crn) {
		String SQL = "delete from Module where crn = ?";
		getJdbcTemplate().update(SQL, new Object[] {crn});
		System.out.println("Deleted module where crn = " + crn );
		
	}

	@Override
	@Transactional
	public Module getModule(String crn) {
		String SQL = "select * from Module where crn = ?";
		Module module = (Module) getJdbcTemplate().queryForObject(SQL, 
						new Object[]{crn}, new ModuleMapper());
		return module;
	}

	@Override
	public void updateModule(String code, String crn, String name, int semester) {
		String SQL = "update module set code = ?, crn = ?,name = ?,semester = ? where crn = ?";
		getJdbcTemplate().update(SQL, new Object[] {code, crn, name, semester, crn});
		System.out.println("Updated code to " + code + "crn = " + crn + "name = " + name + 
				"semester = " + semester + " where crn = " + crn );
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public List<Module> listModules() {
		String SQL = "select * from module";
		List<Module> modules = getJdbcTemplate().query(SQL, 
						new ModuleMapper());
		return modules;
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public int countRows() {
		String SQL = "select count(id_module) from module";
		int rows=getJdbcTemplate().queryForObject(SQL, Integer.class);
		return rows;
	}

}
