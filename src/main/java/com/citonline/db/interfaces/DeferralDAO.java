package com.citonline.db.interfaces;

import java.util.ArrayList;
import java.util.Calendar;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import com.citonline.domain.Deferral;
import com.citonline.domain.Module;
/**
 * 
 * @author peter halligan
 * 
 * interface to handle the database actions for the deferal object
 *
 */
@Service
public interface DeferralDAO 
{
	public int countRows();
	
	public void createDeferral(Calendar date, int id_program, int id_student, boolean proframDeferred, int status);
	
	public void deleteDeferral(int id_defferal);
	
	void updateDeferralStatus(int status, String firstName, String lastName);
	
	void updateDeferralStatus(int status, String studentNumber);
	
	void addDeferredModules(int id_deferral, final ArrayList<Module> defered);
	
	public ArrayList<Module> getDeferredModules(int deferral);
	
	public ArrayList<Module> getDeferredModulesName(String firstName, String lastName);
	
	public ArrayList<Module> getDeferredModuleStudentNumber(String studentNumber);
	
	public ArrayList<Deferral> getDeferralsStudentNumber(String studentNumber);
	
	public ArrayList<Deferral> getDeferralsStudentName(String firstName, String lastName);
	
	public ArrayList<Deferral>getDefferalsStatus(int status);
	
}
