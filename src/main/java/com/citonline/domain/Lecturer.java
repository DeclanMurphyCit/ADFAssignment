/**
 * @author Fabien
 * 
 * @since 25 oct. 2014
 */
package com.citonline.domain;

import java.util.ArrayList;

public class Lecturer extends Person {
	
	protected Program managedProgram;
	protected int idManagedProgram;

	protected ArrayList<Module> taughtModules;
	
	protected String roomNumber;
	
	/**
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param phoneNumber
	 * @param roomNumber
	 */
	public Lecturer(int id, String firstName, String lastName, String email,
			String phoneNumber, String roomNumber) {
		super(id, firstName, lastName, email, phoneNumber);
		this.roomNumber = roomNumber;

		// Avoid a null pointer exception in case of using addModule before setting it.
		taughtModules = new ArrayList<Module>();
	}

	/**
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param phoneNumber
	 * @param roomNumber
	 * @param idManagedProgram the id of the program the lecturer managed, if any
	 */
	public Lecturer(int id, String firstName, String lastName, String email,
			String phoneNumber, String roomNumber, int idManagedProgram) {
		super(id, firstName, lastName, email, phoneNumber);
		this.roomNumber = roomNumber;
		this.idManagedProgram = idManagedProgram;

		// Avoid a null pointer exception in case of using addModule before setting it.
		taughtModules = new ArrayList<Module>();
	}
	

	/**
	 * @return the managedProgram
	 */
	public Program getManagedProgram() {
		return managedProgram;
	}

	/**
	 * @param managedProgram the managedProgram to set
	 */
	public void setManagedProgram(Program managedProgram) {
		this.managedProgram = managedProgram;
	}
	
	/**
	 * 
	 * @return roomNumber
	 */
	public String getRoomNumber() {
		return roomNumber;
	}

	/**
	 * 
	 * @param roomNumber the roomNumber to set
	 */
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	/**
	 * 
	 * @return the taughtModules
	 */
	public ArrayList<Module> getModulesTaught() {
		return taughtModules;
	}

	/**
	 * 
	 * @param taughtModules the taughtModules to set
	 */
	public void setModulesTaught(ArrayList<Module> taughtModules) {
		this.taughtModules = taughtModules;
	}

	/**
	 * @return the idManagedProgram
	 */
	public int getIdManagedProgram() {
		return idManagedProgram;
	}

	/**
	 * @param idManagedProgram the idManagedProgram to set
	 */
	public void setIdManagedProgram(int idManagedProgram) {
		this.idManagedProgram = idManagedProgram;
	}

	
}
