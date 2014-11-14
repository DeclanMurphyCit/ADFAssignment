/**
 * @author Fabien
 * 
 * @since 25 oct. 2014
 */
package com.citonline.db.interfaces;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import com.citonline.domain.Module;
import com.citonline.domain.Program;
import com.citonline.interfaces.impl.LecturerImpl;

@Service
public interface LecturerDAO {

	/**
	 *  This is the method to be used to initialize
	 * database resources ie. connection.
	 * 
	 * @param dataSource
	 */
	public void setDataSource(DataSource dataSource);
	
	/**
	 *  This is the method to be used to create
	 * a record in the lecturer table.
	 * 
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param phoneNumber
	 * @param roomNumber
	 */
	public void createLecturer(String firstName, String lastName, String email,
			String phoneNumber, String roomNumber);
	
	/**
	 *  This is the method to be used to create
	 * a record in the lecturer table.
	 * 
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param roomNumber
	 * @param phoneNumber
	 * @param idManagedProgram
	 */
	public void createLecturer(String firstName, String lastName, String email,
			String phoneNumber, String roomNumber, Integer idManagedProgram);
	
	/**
	 *  This is the method to be used to delete
	 * a record from the lecturer table corresponding
	 * to a passed Lecturer's id_lecturer.
	 * 
	 * @param id_lecturer
	 */
	public void deleteLecturer(Integer id_lecturer);
	/**
	 *  This is the method to be used to delete
	 * a record from the lecturer table corresponding
	 * to a passed Lecturer's firstName and lastName.
	 * 
	 * @param firstName
	 * @param lastName
	 */
	public void deleteLecturer(String firstName, String lastName);
	
	/**
	 *  This is the method to be used to list down
	 * a record from the lecturer table corresponding
	 * to a passed Lecturer's id.
	 * 
	 * @param id_lecturer
	 * @return the corresponding Lecturer
	 */
	public LecturerImpl getLecturer(Integer id_lecturer);
	/**
	 *  This is the method to be used to list down
	 * a record from the lecturer table corresponding
	 * to a passed Lecturer's firstName and lastName.
	 * 
	 * @param firstName
	 * @param lastName
	 * @return the corresponding Lecturer
	 */
	public LecturerImpl getLecturer(String firstName, String lastName);
	
	/** 
	 *  This is the method to be used to list down
	 * all the records from the Lecturer table.
	 * 
	 *  @return the list of all Lecturers present in the lecturer table
	 */
	public List<LecturerImpl> listLecturers();   
	
	/**
	 *  Update the Lecturer's email.
	 *  
	 * @param firstName
	 * @param lastName
	 * @param email
	 */
	public void updateLecturerEmail(String firstName, String lastName, String email);
	/**
	 *  Update the Lecturer's email.
	 *  
	 * @param id_lecturer
	 * @param email
	 */
	public void updateLecturerEmail(Integer id_lecturer, String email);
	
	/**
	 *  Update the Lecturer's roomNumber.
	 *  
	 * @param firstName
	 * @param lastName
	 * @param roomNumber
	 */
	public void updateLecturerRoomNumber(String firstName, String lastName, String roomNumber);
	/**
	 *  Update the Lecturer's roomNumber.
	 *  
	 * @param id_lecturer
	 * @param roomNumber
	 */
	public void updateLecturerRoomNumber(Integer id_lecturer, String roomNumber);
	
	/**
	 *  Update the Lecturer's ManagedProgram.
	 *  
	 * @param firstName
	 * @param lastName
	 * @param idManagedProgram
	 */
	public void updateLecturerManagedProgram(String firstName, String lastName, Integer idManagedProgram);
	/**
	 *  Update the Lecturer's ManagedProgram.
	 *  
	 * @param id_lecturer
	 * @param idManagedProgram
	 */
	public void updateLecturerManagedProgram(Integer id_lecturer, Integer idManagedProgram);
	
	/**
	 *  Set the Lecturer to teach this Module
	 *  
	 * @param firstName
	 * @param lastName
	 * @param idModule
	 */
	public void addTaughtModule(String firstName, String lastName, Integer idModule);
	/**
	 *  Set the Lecturer to teach this Module
	 *  
	 * @param id_lecturer
	 * @param idModule
	 */
	public void addTaughtModule(Integer id_lecturer, Integer idModule);
	/**
	 *  Set the Lecturer to teach those Modules
	 *  
	 * @param firstName
	 * @param lastName
	 * @param idModuleList
	 */
	public void addTaughtModule(final String firstName, final String lastName, final List<Integer> idModuleList);
	/**
	 *  Set the Lecturer to teach those Modules
	 *  
	 * @param id_lecturer
	 * @param idModuleList
	 */
	public void addTaughtModule(final Integer id_lecturer, final List<Integer> idModuleList);
	
	/**
	 *  Stop the Lecturer from teaching this Module
	 *  
	 * @param firstName
	 * @param lastName
	 * @param idModule
	 */
	public void removeTaughtModule(String firstName, String lastName, Integer idModule);
	/**
	 *  Stop the Lecturer from teaching this Module
	 *  
	 * @param id_lecturer
	 * @param idModule
	 */
	public void removeTaughtModule(Integer id_lecturer, Integer idModule);
	/**
	 *  Stop the Lecturer from teaching those Modules
	 *  
	 * @param firstName
	 * @param lastName
	 * @param idModuleList
	 */
	public void removeTaughtModule(final String firstName, final String lastName, final List<Integer> idModuleList);
	/**
	 *  Stop the Lecturer from teaching those Modules
	 *  
	 * @param id_lecturer
	 * @param idModuleList
	 */
	public void removeTaughtModule(final Integer id_lecturer, final List<Integer> idModuleList);
	/**
	 * This is the method to be used to list down
	 * all the modules that the Lecturer teach.
	 * 
	 * @param id_lecturer
	 * 
	 * @return the list of all the Modules the Lecturer teaches
	 */
	public ArrayList<Module> getTaughtModules(final Integer id_lecturer);
	/**
	 * This is the method to be used to list down
	 * all the modules that the Lecturer teach.
	 * 
	 * @param firstName
	 * @param lastName
	 * 
	 * @return the list of all the Modules the lecturer teaches
	 */
	public ArrayList<Module> getTaughtModules(final String firstName, final String lastName);
	/**
	 * Count the number of rows in the lecturer table
	 * 
	 * @return the number of rows in the lecturer table
	 */
	public int countRows();
	/**
	 * Get the managedProgram of the Lecturer
	 * 
	 * @param firstName
	 * @param lastName
	 * @return the managedProgram of the Lecturer
	 */
	public Program getManagedProgram(final String firstName, final String lastName);
	/**
	 * Get the managedProgram of the Lecturer
	 * 
	 * @param id_lecturer
	 * @return the managedProgram of the Lecturer
	 */
	public Program getManagedProgram(final Integer id_lecturer);
	
}
