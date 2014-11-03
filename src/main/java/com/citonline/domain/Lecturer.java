/**
 * @author Fabien
 * 
 * @since 25 oct. 2014
 */
package com.citonline.domain;

public class Lecturer extends Person {
	
	protected Program managedProgram;
	
	protected String roomNumber;
	
	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param phoneNumber
	 * @param roomNumber
	 */
	public Lecturer(String firstName, String lastName, String email,
			String phoneNumber, String roomNumber) {
		super(firstName, lastName, email, phoneNumber);
		this.roomNumber = roomNumber;
	}

	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param phoneNumber
	 * @param roomNumber
	 * @param managedProgram the program the lecturer managed, if any
	 */
	public Lecturer(String firstName, String lastName, String email,
			String phoneNumber, String roomNumber, Program managedProgram) {
		super(firstName, lastName, email, phoneNumber);
		this.roomNumber = roomNumber;
		this.managedProgram = managedProgram;
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

}
