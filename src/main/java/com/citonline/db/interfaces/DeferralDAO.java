package com.citonline.db.interfaces;

import java.util.ArrayList;
import java.util.Calendar;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import com.citonline.domain.Deferral;
import com.citonline.domain.Module;

@Service
public interface DeferralDAO 
{
	public void setDataSource(DataSource dataSource);
	
	public void createDeferral(Calendar date, int id_student, int id_program, boolean proframDeferred, int status);
	
	public void deleteDeferral(int id_defferal);
	
	public ArrayList<Deferral> getDeferralsStudentName(String firstName, String lastName);
	
	public ArrayList<Deferral>getDefferalsStatus(int status);
	
	
	public ArrayList<Module> getDeferredModules(int deferral);

	void updateDeferalStatus(int id_deferral, String firstName, String lastName);

	void updateDeferalStatus(int status, String studentNumber);

	void addDeferredModules(int id_deferral, final ArrayList<Module> defered);

}
