package com.citonline.db.interfaces;

import java.sql.Date;
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
	/**
	 *  count rows in deferral table
	 * @return number of rows in Deferral table
	 */
	public int countRows();
	/**
	 * add deferal object to the database
	 * @param date
	 * @param id_program
	 * @param id_student
	 * @param proframDeferred
	 * @param status
	 */
	public void createDeferral(String date, int id_program, int id_student, boolean proframDeferred, int status);
	/**
	 * sets the deferal object status to deleted
	 * @param id_defferal
	 */
	public void deleteDeferral(int id_defferal);
	/**
	 * change status of the deferal 
	 * @param status
	 * @param firstName
	 * @param lastName
	 */
	void updateDeferralStatus(int status, String firstName, String lastName);
	/**
	 * change status of deferral based on student numberr
	 * @param status
	 * @param studentNumber
	 */
	void updateDeferralStatus(int status, String studentNumber);
	/**
	 * Add deferred module based on id of the deferal 
	 * @param id_deferral
	 * @param defered
	 */
	void addDeferredModules(int id_deferral, final ArrayList<Module> defered);
	/**
	 * gets deferred modules based on deferral number
	 * @param deferral
	 * @return arraylist Deferred modules
	 */
	public ArrayList<Module> getDeferredModules(int deferral);
	/**
	 * gets deferred modules based on student name
	 * @param firstName
	 * @param lastName
	 * @return arraylist<modules> defered modules
	 */
	public ArrayList<Module> getDeferredModulesName(String firstName, String lastName);
	/**
	 * get deferred modules based on student number
	 * @param studentNumber
	 * @return <modules> defered modules
	 */
	public ArrayList<Module> getDeferredModuleStudentNumber(String studentNumber);
	/**
	 * get deferrals based on student number
	 * @param studentNumber
	 * @return array list of defferal
	 */
	public ArrayList<Deferral> getDeferralsStudentNumber(String studentNumber);
	/**
	 * single deferal based on student name
	 * @param firstName
	 * @param lastName
	 * @return array list of deferal
	 */
	public Deferral getDeferralStudentName(String firstName, String lastName);
	/**
	 * returns single defferal based on student number
	 * @param studentNumber
	 * @return Deferal object
	 */
	public Deferral getDeferralStudentNumber(String studentNumber);
	/**
	 * get list of deferrals based on student name
	 * @param firstName
	 * @param lastName
	 * @return array list defferals
	 */
	public ArrayList<Deferral> getDeferralsStudentName(String firstName, String lastName);
	/**
	 * geet Defferals based on status id
	 * @param status
	 * @return arraylist of defferals
	 */
	public ArrayList<Deferral>getDefferalsStatus(int status);
	/**
	 * returns Defferals based on the name of the status as found in deferralStatus enum
	 * @param status
	 * @return arraylist defferals
	 */
	public ArrayList<Deferral>getDefferalsStatusName(String status);
	
}
