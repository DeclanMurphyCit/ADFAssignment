package com.citonline.interfaces.impl;

import java.util.ArrayList;

import com.citonline.domain.Module;
import com.citonline.domain.Program;
import com.citonline.domain.Student;
import com.citonline.interfaces.StudentInt;

public class StudentImpl extends Student implements StudentInt {
	
	private ArrayList<Module> moduleList = new ArrayList<Module>();
	private ArrayList<Object> deferralList = new ArrayList<Object>();
	//TODO Is this right and if not should it be of type deferral?

	public StudentImpl(String firstName, String lastName, String email,
			String phoneNumber, String studentNumber, String addressLine1,String addressLine2,
			ArrayList<Module> moduleList,ArrayList<Object> deferralList) {
		super(firstName, lastName, email, phoneNumber, studentNumber, addressLine1, addressLine2);	
		this.moduleList = moduleList;
		this.deferralList = deferralList;
	}

	@Override
	public void enroll(Module module) {
		moduleList.add(module);
	}

	@Override
	public void deferModule(Module module) {
		deferralList.add(module);
	}

	@Override
	public void deferProgram(Program Program) {
		deferralList.add(Program);
	}

}
