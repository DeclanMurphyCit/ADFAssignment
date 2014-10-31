package com.citonline.domain;

import java.util.ArrayList;
import java.util.Calendar;

public class Deferral 
{
	Calendar defferalDate;
	Student student;
	ArrayList<Module> defferedModules;
	boolean programDeffered;
	Program program;
	
	
	
	public Deferral(Calendar defferalDate, Student student,
			ArrayList<Module> defferedModules, boolean programDeffered,
			Program program) {
		super();
		this.defferalDate = defferalDate;
		this.student = student;
		this.defferedModules = defferedModules;
		this.programDeffered = programDeffered;
		this.program = program;
	}

	public boolean isProgramDeffered() {
		return programDeffered;
	}

	public void setProgramDeffered(boolean programDeffered) {
		this.programDeffered = programDeffered;
	}

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	
	public Calendar getDefferalDate() {
		return defferalDate;
	}
	public void setDefferalDate(Calendar defferalDate) {
		this.defferalDate = defferalDate;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public ArrayList<Module> getDefferedModules() {
		return defferedModules;
	}
	public void setDefferedModules(ArrayList<Module> defferedModules) {
		this.defferedModules = defferedModules;
	}
	
	
	
}
