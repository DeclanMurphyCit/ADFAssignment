package com.citonline.db;

import java.util.List;

import javax.sql.DataSource;

import com.citonline.domain.Lecturer;

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
	 * a record in the Lecturer table.
	 * 
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param roomNumber
	 */
	public void createLecturer(String firstName, String lastName, String email, String roomNumber);
	
	/**
	 *  This is the method to be used to create
	 * a record in the Lecturer table.
	 * 
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param roomNumber
	 * @param idProgram
	 */
	public void createLecturer(String firstName, String lastName, String email, String roomNumber, Integer idProgram);
	
	/**
	 *  This is the method to be used to list down
	 * a record from the Lecturer table corresponding
	 * to a passed Lecturer's id.
	 * 
	 * @param id
	 * @return the corresponding Lecturer
	 */
	public Lecturer getLecturer(Integer id);
	/** 
	 *  This is the method to be used to list down
	 * all the records from the Lecturer table.
	 * 
	 *  @return the list of all Lecturers present in the Lecturer table
	 */
	public List<Lecturer> listLecturers();   
	
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
	 * @param id
	 * @param email
	 */
	public void updateLecturerEmail(Integer id, String email);
	
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
	 * @param id
	 * @param roomNumber
	 */
	public void updateLecturerRoomNumber(Integer id, String roomNumber);
	
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
	 * @param id
	 * @param idManagedProgram
	 */
	public void updateLecturerManagedProgram(Integer id, Integer managedProgram);
	
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
	 * @param id
	 * @param idModule
	 */
	public void addTaughtModule(Integer id, Integer idModule);
	/**
	 *  Set the Lecturer to teach those Modules
	 *  
	 * @param firstName
	 * @param lastName
	 * @param idModuleList
	 */
	public void addTaughtModule(String firstName, String lastName, List<Integer> idModuleList);
	/**
	 *  Set the Lecturer to teach those Modules
	 *  
	 * @param id
	 * @param idModuleList
	 */
	public void addTaughtModule(Integer id, List<Integer> idModuleList);
	
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
	 * @param id
	 * @param idModule
	 */
	public void removeTaughtModule(Integer id, Integer idModule);
	/**
	 *  Stop the Lecturer from teaching those Modules
	 *  
	 * @param firstName
	 * @param lastName
	 * @param idModuleList
	 */
	public void removeTaughtModule(String firstName, String lastName, List<Integer> idModuleList);
	/**
	 *  Stop the Lecturer from teaching those Modules
	 *  
	 * @param id
	 * @param idModuleList
	 */
	public void removeTaughtModule(Integer id, List<Integer> idModuleList);
	
}
