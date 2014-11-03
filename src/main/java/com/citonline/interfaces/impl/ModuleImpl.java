package com.citonline.interfaces.impl;

import java.util.Calendar;

import com.citonline.domain.Module;
import com.citonline.interfaces.ModuleInt;

/*
 * Author: Tim Wallace
 * Date: 29/10/14
 * 
 * Description:Module implementation
 * 
 * Inputs: Module semester, code, crn, name
 * 
 * Expected Outputs: Module semester, code, crn, name, start date
 */

public class ModuleImpl  extends Module implements ModuleInt{
	
	public ModuleImpl(String code, String crn, String name, int semester) {
		super(code, crn, name, semester);
	}

	@Override
	public Calendar moduleStart() {
		Calendar rightNow = Calendar.getInstance();
		return rightNow;
		
	}

}
