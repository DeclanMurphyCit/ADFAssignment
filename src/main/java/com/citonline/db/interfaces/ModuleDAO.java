package com.citonline.db.interfaces;

import java.util.List;
import javax.sql.DataSource;
import com.citonline.domain.Module;

/*
 * Author: Tim Wallace
 * Date: 03/11/14
 * 
 * Description:Module DAO
 * 
 * Inputs: Module semester, code, crn, name
 * 
 * Expected Outputs: create, delete,modify, return modules
 */

public interface ModuleDAO {

	public void setDataSource(DataSource dataSource);

	public void createModule(String code, String crn, String name, int semester);

	public void deleteModule(String crn);	
	
	public Module getModule(String crn);
	
	public List<Module> listModules();   
	
	public void updateModule(String code, String crn, String name, int semester);
	
}