package com.citonline.interfaces.impl;

import java.util.ArrayList;

import com.citonline.domain.Module;
import com.citonline.domain.Program;
import com.citonline.domain.Student;
import com.citonline.interfaces.StudentInt;

/**
* @author  Declan Murphy
* @since   29-10-2014
*/

public class StudentImpl extends Student implements StudentInt {
	
	private ArrayList<Module> moduleList = new ArrayList<Module>();
	private ArrayList<Object> deferralList = new ArrayList<Object>();
	private Program program;

	public StudentImpl(String firstName, String lastName, String email,
			String phoneNumber, String studentNumber, String addressLine1,String addressLine2,
			ArrayList<Module> moduleList,ArrayList<Object> deferralList) {
		super(firstName, lastName, email, phoneNumber, studentNumber, addressLine1, addressLine2);	
		this.moduleList = moduleList;
		this.deferralList = deferralList;
	}

	@Override
	public void enrollModule(Module module) {
		moduleList.add(module);
	}
	
	@Override
	public void enrollProgram(Program Program) {
		setProgram(program);
	}

	@Override
	public void deferModule(Module module) {
		deferralList.add(module);
	}

	@Override
	public void deferProgram(Program Program) {
		deferralList.add(Program);
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public ArrayList<Module> getModuleList() {
		return moduleList;
	}

	public void setModuleList(ArrayList<Module> moduleList) {
		this.moduleList = moduleList;
	}

	public ArrayList<Object> getDeferralList() {
		return deferralList;
	}

	public void setDeferralList(ArrayList<Object> deferralList) {
		this.deferralList = deferralList;
	}
}
