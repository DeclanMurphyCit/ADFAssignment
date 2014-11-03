package com.citonline.domain;

/*
 * Author: Tim Wallace
 * Date: 29/10/14
 * 
 * Description:Module pojo
 * 
 * Inputs: Module semester, code, crn, name
 * 
 * Expected Outputs: Module semester, code, crn, name
 */

public class Module {
	int semester;
	String code, crn, name;
	
	public int getSemester() {
		return semester;
	}
	public void setSemester(int semester) {
		this.semester = semester;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCrn() {
		return crn;
	}
	public void setCrn(String crn) {
		this.crn = crn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Module(String code, String crn, String name, int semester) {
		super();
		this.code = code;
		this.crn = crn;
		this.name = name;
		this.semester = semester;
	}
	
	
}
