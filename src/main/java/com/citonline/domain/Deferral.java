package com.citonline.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Deferral 
{

	Calendar defferalDate;
	int student;
	ArrayList<Module> defferedModules;
	boolean programDeffered;
	int program;
	int status;
	
	
	public Deferral(Calendar defferalDate, int student, boolean programDeffered,
			int program) {
		super();
		this.defferalDate = defferalDate;
		this.student = student;
		this.programDeffered = programDeffered;
		this.program = program;
		
		ArrayList<Module> defferedModules = new ArrayList<Module>();
	}

	public Deferral(Date deferralDate, int id_student, int id_program,
			int id_deferral) {
		super();
		this.defferalDate = defferalDate;
		this.student = student;
		this.defferedModules = defferedModules;
		this.programDeffered = programDeffered;
		this.program = program;
		
	}
	public int getStatus() {
		return status;
	}

	public void setStatus(Enum<DeferralStatus> e) {
		this.status = e.ordinal();
	}
	public boolean isProgramDeffered() {
		return programDeffered;
	}

	public void setProgramDeffered(boolean programDeffered) {
		this.programDeffered = programDeffered;
	}

	public int getProgram() {
		return program;
	}

	public void setProgram(int program) {
		this.program = program;
	}

	
	public Calendar getDefferalDate() {
		return defferalDate;
	}
	public void setDefferalDate(Calendar defferalDate) {
		this.defferalDate = defferalDate;
	}
	public int getStudent() {
		return student;
	}
	public void setStudent(int student) {
		this.student = student;
	}
	public ArrayList<Module> getDefferedModules() {
		return defferedModules;
	}
	public void setDefferedModules(ArrayList<Module> defferedModules) {
		this.defferedModules = defferedModules;
	}
	
	
	
}
