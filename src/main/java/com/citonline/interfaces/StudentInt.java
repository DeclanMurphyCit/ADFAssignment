package com.citonline.interfaces;

import com.citonline.domain.Module;
import com.citonline.domain.Program;

/**
* @author  Declan Murphy
* @since   29-10-2014
*/

public interface StudentInt {
	
	public void enrolProgram (Program program);
	public void enrolModule (Module module);

}
