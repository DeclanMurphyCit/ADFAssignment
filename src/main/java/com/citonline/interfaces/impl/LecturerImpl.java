/**
 * @author Fabien
 * 
 * @since 3 nov. 2014
 */
package com.citonline.interfaces.impl;

import java.util.ArrayList;

import com.citonline.domain.Deferral;
import com.citonline.domain.Lecturer;
import com.citonline.domain.Module;
import com.citonline.domain.Program;
import com.citonline.interfaces.LecturerInt;

public class LecturerImpl extends Lecturer implements LecturerInt {

	private ArrayList<Module> modulesTaught;
	
	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param phoneNumber
	 * @param roomNumber
	 */
	public LecturerImpl(String firstName, String lastName, String email,
			String phoneNumber, String roomNumber) {
		super(firstName, lastName, email, phoneNumber, roomNumber);
		
		// Avoid a null pointer exception in case of using addModule before setting it.
		modulesTaught = new ArrayList<Module>();
	}
	
	/**
	 * Constructor of the class
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param phoneNumber
	 * @param roomNumber
	 * @param managedProgram
	 */
	public LecturerImpl(String firstName, String lastName, String email,
			String phoneNumber, String roomNumber, Program managedProgram) {
		super(firstName, lastName, email, phoneNumber, roomNumber, managedProgram);
		
		// Avoid a null pointer exception in case of using addModule before setting it.
		modulesTaught = new ArrayList<Module>();
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

	/* (non-Javadoc)
	 * @see com.citonline.interfaces.LecturerInt#isProgramManager()
	 */
	@Override
	public boolean isProgramManager() {
		return managedProgram != null;
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

}
