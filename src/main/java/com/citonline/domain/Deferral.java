package com.citonline.domain;

import java.util.ArrayList;
/**
 * 
 * @author peter halligan
 * deferral object
 * holds information relating to the deferral including student id, and Arraylist of deferred modules
 *
 */
public class Deferral 
{
	int id;
	String deferral_date;
	int id_student;
	ArrayList<Module> deferredModules;
	boolean programDeferred;
	int id_program;
	int id_deferral_status;
	
	/**
	 * costructor with id for row mapper
	 * @param id
	 * @param deferral_date
	 * @param id_student
	 * @param id_program
	 * @param program_deferred
	 * @param id_deferral_status
	 */
	public Deferral(int id, String deferral_date, int id_student,int id_program, boolean program_deferred, int id_deferral_status) {
		super();
		this.id=id;
		this.deferral_date = deferral_date;
		this.id_student = id_student;
		this.programDeferred = program_deferred;
		this.id_program = id_program;
		this.id_deferral_status =id_deferral_status;
		deferredModules = new ArrayList<Module>();
	}
	/**
	 * constructor with no id 
	 * @param deferral_date
	 * @param id_student
	 * @param id_program
	 * @param program_deferred
	 * @param id_deferral_status
	 */
	public Deferral(String deferral_date, int id_student,int id_program, boolean program_deferred, int id_deferral_status) {
		super();
		this.deferral_date = deferral_date;
		this.id_student = id_student;
		this.programDeferred = program_deferred;
		this.id_program = id_program;
		this.id_deferral_status =id_deferral_status;
		deferredModules = new ArrayList<Module>();
	}
	public int getStatus() {
		return id_deferral_status;
	}

	public void setStatus(DeferralStatus status) {
		this.id_deferral_status = status.getStatus();
	}
	public boolean getProgramDeferred() {
		return programDeferred;
	}

	public void setProgramDeffered(boolean programDeffered) {
		this.programDeferred = programDeffered;
	}

	public int getProgram() {
		return id_program;
	}

	public void setProgram(int program) {
		this.id_program = program;
	}

	
	public String getDefferalDate() {
		return deferral_date;
	}
	public void setDefferalDate(String defferal_date) {
		this.deferral_date = defferal_date;
	}
	public int getStudent() {
		return id_student;
	}
	public void setStudent(int id_student) {
		this.id_student = id_student;
	}
	public ArrayList<Module> getDefferedModules() {
		return deferredModules;
	}
	public void setDefferedModules(ArrayList<Module> defferedModules) {
		this.deferredModules = defferedModules;
	}

	public int getId() {
		return id;
	}

	public void setId(int id_deferral) {
		this.id= id_deferral;
	}
	
}
