package com.citonline.domain;

import java.util.ArrayList;

import com.citonline.interfaces.LecturerInt;

/**
 * @author Fabien
 *
 */
public class Lecturer extends Person implements LecturerInt {
	
	private Program managedProgram;
	private ArrayList<Module> modulesTaught;
	
	private String roomNumber;
	
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
	 * @return the modulesTaught
	 */
	public ArrayList<Module> getModulesTaught() {
		return modulesTaught;
	}

	/**
	 * 
	 * @param modulesTaught the modulesTaught to set
	 */
	public void setModulesTaught(ArrayList<Module> modulesTaught) {
		this.modulesTaught = modulesTaught;
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

	/* (non-Javadoc)
	 * @see com.citonline.interfaces.LecturerInt#isProgramManager()
	 */
	@Override
	public boolean isProgramManager() {
		return this.managedProgram != null;
	}

	/* (non-Javadoc)
	 * @see com.citonline.interfaces.LecturerInt#uploadSignedDeferral(com.citonline.domain.Deferral)
	 */
	@Override
	public void uploadSignedDeferral(Deferral deferral) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.citonline.interfaces.LecturerInt#answerSupportCall()
	 */
	@Override
	public void answerSupportCall() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.citonline.interfaces.LecturerInt#teach(com.citonline.domain.Module)
	 */
	@Override
	public void teach(Module module) {
		modulesTaught.add(module);
		
	}

	/* (non-Javadoc)
	 * @see com.citonline.interfaces.LecturerInt#stopTeach(com.citonline.domain.Module)
	 */
	@Override
	public void stopTeach(Module module) {
		modulesTaught.remove(module);
		
	}

}
