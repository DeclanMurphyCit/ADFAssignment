package com.citonline.domain;

import java.util.List;

/*
 * Author: Tim Wallace
 * Date: 29/10/14
 * 
 * Description:Module pojo
 * 
 * Inputs: id, Module semester, code, crn, name
 * 
 * Expected Outputs: id, Module semester, code, crn, name
 */

public class Module {
	int semester, id;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Module(int id, String code, String crn, String name, int semester) {
		super();
		this.id = id;
		this.code = code;
		this.crn = crn;
		this.name = name;
		this.semester = semester;
	}
	
}
