package com.citonline.interfaces;

import com.citonline.domain.Module;
import com.citonline.domain.Program;

/**
* @author  Declan Murphy
* @since   29-10-2014
*/

public interface StudentInt {
	
	public void enroll (Module module);
	public void deferModule (Module module);
	public void deferProgram (Program Program);

}
