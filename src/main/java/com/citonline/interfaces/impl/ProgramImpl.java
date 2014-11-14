package com.citonline.interfaces.impl;

import java.util.ArrayList;

import com.citonline.domain.Program;
import com.citonline.domain.Semester;
import com.citonline.interfaces.ProgramInt;

public class ProgramImpl extends Program implements ProgramInt{

	public ProgramImpl(int programId, String programName, String programCode,
			ArrayList<Semester> semesterList) {
		super(programId, programName, programCode, semesterList);
		
	}
	
}
