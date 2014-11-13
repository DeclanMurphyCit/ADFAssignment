package com.citonline.interfaces.impl;

import java.util.ArrayList;

import com.citonline.domain.Deferral;
import com.citonline.domain.Module;
import com.citonline.domain.Program;
import com.citonline.domain.Student;
import com.citonline.interfaces.StudentInt;

/**
* @author  Declan Murphy
* @since   29-10-2014
*/

public class StudentImpl extends Student implements StudentInt {
	
	private ArrayList<Module> moduleList;
	private Deferral deferrals;
	private Program program;

	public StudentImpl(int id, String firstName, String lastName, String email,
			String phoneNumber, String studentNumber, String addressLine1,String addressLine2) {
		super(id, firstName, lastName, email, phoneNumber, studentNumber, addressLine1, addressLine2);	
		moduleList = new ArrayList<Module>();
	}

	@Override
	public void enrolModule(Module module) {
		moduleList.add(module);
	}
	
	@Override
	public void enrolModules(ArrayList<Module> moduleList) {
		moduleList.addAll(moduleList);
	}
	
	@Override
	public void enrolProgram(Program Program) {
		setProgram(program);
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
}