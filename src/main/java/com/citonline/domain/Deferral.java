package com.citonline.domain;

import java.util.ArrayList;
import java.util.Calendar;

public class Deferral 
{
	Calendar defferalDate;
	Student student;
	ArrayList<Module> defferedModules;
	
	public Deferral(Calendar defferalDate, Student student,
			ArrayList<Module> defferedModules) {
		super();
		this.defferalDate = defferalDate;
		this.student = student;
		this.defferedModules = defferedModules;
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
