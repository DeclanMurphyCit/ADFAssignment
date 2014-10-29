package com.citonline.domain;

import java.util.ArrayList;

public class Semester {
	
	/**
	* This represents a semester
	* It consists of Modules
	*
	* @author  Declan Murphy
	* @version 1.0
	* @since   29-10-2014
	*/
	
	private ArrayList<Module> moduleList = new ArrayList<Module>();
	
	public Semester(ArrayList<Module> moduleList) {
		super();
		this.moduleList = moduleList;
	}

	public ArrayList<Module> getModuleList() {
		return moduleList;
	}

	public void setModuleList(ArrayList<Module> moduleList) {
		this.moduleList = moduleList;
	}
	

}
