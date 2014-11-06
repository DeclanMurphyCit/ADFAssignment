package com.citonline.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Deferral 
{

	Date defferalDate;
	int student;
	ArrayList<Module> defferedModules;
	boolean programDeffered;
	int program_id;
	int status;
	
	
	public Deferral(Date deferralDate, int student,int program, boolean programDeffered, int status) {
		super();
		this.defferalDate = deferralDate;
		this.student = student;
		this.programDeffered = programDeffered;
		this.program_id = program;
		
		ArrayList<Module> defferedModules = new ArrayList<Module>();
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
		return program_id;
	}

	public void setProgram(int program) {
		this.program_id = program;
	}

	
	public Date getDefferalDate() {
		return defferalDate;
	}
	public void setDefferalDate(Date defferalDate) {
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
