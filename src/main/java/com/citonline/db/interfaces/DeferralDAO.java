package com.citonline.db.interfaces;

import java.util.ArrayList;
import java.util.Calendar;

import javax.sql.DataSource;

import com.citonline.domain.Module;

public interface DeferralDAO 
{
	public void setDataSource(DataSource dataSource);
	
	public void createDeferral(Calendar date, int id_student, int id_program);
	
	public void deleteDeferral(int id_defferal);
	
	public ArrayList<Module> getDeferredModules(int deferral);

	void updateDeferal(int id_deferral, String firstName, String lastName);

	void updateDeferal(int status, String studentNumber);

	void addDeferredModules(int id_deferral, final ArrayList<Module> defered);

}
