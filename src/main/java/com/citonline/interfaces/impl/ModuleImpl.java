package com.citonline.interfaces.impl;

import java.util.Calendar;

import com.citonline.domain.Module;
import com.citonline.interfaces.ModuleInt;

public class ModuleImpl  extends Module implements ModuleInt{
	
	public ModuleImpl(int id, String code, String crn, String name, int semester) {
		super(id, code, crn, name, semester);
	}

	@Override
	public Calendar moduleStart() {
		Calendar rightNow = Calendar.getInstance();
		return rightNow;
		
	}

}
