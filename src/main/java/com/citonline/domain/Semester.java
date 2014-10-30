package com.citonline.domain;

import java.util.ArrayList;

/**
* @author  Declan Murphy
* @since   29-10-2014
*/

public class Semester {
		
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
