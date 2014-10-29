package com.citonline.domain;

import java.util.ArrayList;

public class Program {
	
	/**
	* This represents a course
	* 
	* @author  Declan Murphy
	* @version 1.0
	* @since   29-10-2014
	*/
	
	String programName,programCode;
	
	private ArrayList<Semester> semesterList = new ArrayList<Semester>();
	//private ProgramCoordinator;
		
	public Program(String programName, String programCode,
			ArrayList<Semester> semesterList) {
		super();
		this.programName = programName;
		this.programCode = programCode;
		this.semesterList = semesterList;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	public String getProgramCode() {
		return programCode;
	}

	public void setProgramCode(String programCode) {
		this.programCode = programCode;
	}

	public void setSemesterList(ArrayList<Semester> semesterList) {
		this.semesterList = semesterList;
	}
	
	public ArrayList<Semester> getSemesterList() {
		return semesterList;
	}
}
