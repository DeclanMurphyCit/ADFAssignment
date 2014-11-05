package com.citonline.interfaces;

import org.springframework.stereotype.Service;

import com.citonline.domain.Module;
import com.citonline.domain.Program;

/**
* @author  Declan Murphy
* @since   29-10-2014
*/

@Service
public interface StudentInt {
	
	public void enrolProgram (Program program);
	public void enrolModule (Module module);

}
