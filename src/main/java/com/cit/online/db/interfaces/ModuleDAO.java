package com.cit.online.db.interfaces;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import com.citonline.domain.Module;
import com.citonline.interfaces.impl.ModuleImpl;

/*
 * Author: Tim Wallace
 * Date: 03/11/14
 * 
 * Description:Module DAO
 * 
 * Inputs: Module id, semester, code, crn, name
 * 
 * Expected Outputs: create, delete,modify, return modules
 */

@Service
public interface ModuleDAO {

	public void setDataSource(DataSource dataSource);

	//public void createModule(int id, String code, String crn, String name, int semester);

	public void deleteModule(String crn);	
	
	public Module getModule(String crn);  
	
	public void updateModule(String code, String crn, String name, int semester);

	int countRows();

	void createModule(String code, String crn, String name, int semester);

	public List<ModuleImpl> listModules();

	
}