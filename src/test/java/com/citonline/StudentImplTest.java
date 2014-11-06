package com.citonline;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.citonline.domain.Deferral;
import com.citonline.domain.Module;
import com.citonline.domain.Program;
import com.citonline.interfaces.impl.StudentImpl;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration({"classpath:config.xml"})
public class StudentImplTest {
	
	@Autowired
	ApplicationContext context; 

	/**
	 * @author Declan Murphy 
	 * @date 3 nov. 2014
	 * 
	 * Test method for {@link com.citonline.interfaces.impl.StudentImpl#enrolModule(com.citonline.domain.Module)}.
	 * 
	 * This method tests if a Module has been added in the module list of a Student
	 * It also tests if it matches the module bean Student_Declan 
	 */
	@Test
	public void testEnrolModule() {
		
		StudentImpl declan = (StudentImpl) context.getBean("Student_Declan");
		
		Module module = (Module) context.getBean("Module_ADF");
		//Module module = new Module("SOFT8020", "CRN", "App Dev Frmwk", 7);
		declan.enrolModule(module);
		
		assertTrue(declan.getModuleList().contains(module));
		
		Module mTest = declan.getModuleList().get(0);
		
		assertEquals("SOFT8020", mTest.getCode());
		assertEquals("CRN", mTest.getCrn());
		assertEquals("App Dev Frmwk", mTest.getName());
		assertEquals(7, mTest.getSemester());
	}
	
	/**
	 * @author Declan Murphy 
	 * @date 4 nov. 2014
	 * 
	 * Test method for {@link com.citonline.interfaces.impl.StudentImpl#enrolProgram(com.citonline.domain.Module)}.
	 * 
	 * This method tests if a Program has been added to a Student
	 * It also tests if it matches the module bean Program_SoftwareDev
	 */
	@Test
	public void testEnrolProgram() {
		
		StudentImpl declan = (StudentImpl) context.getBean("Student_Declan");		
		Program softwareDev = (Program) context.getBean("Program_SoftwareDev");
		
		declan.enrolProgram(softwareDev);
		
		assertTrue(declan.getProgram().equals(softwareDev));
		
		Program pTest = declan.getProgram();
		
		assertEquals("CR106", pTest.getProgramCode());
		assertEquals("Software Development", pTest.getProgramName());
	}
	
	/**
	 * @author Declan Murphy 
	 * @date 4 nov. 2014
	 * 
	 * Test method for {@link com.citonline.interfaces.impl.StudentImpl#deferModule(com.citonline.domain.Module)}.
	 * 
	 * This method tests if a Deferral has been added to a Student's deferral list
	 * It also tests if it matches the module bean Program_SoftwareDev
	 */
/*	@Test
	public void deferModule() {
		
		StudentImpl declan = (StudentImpl) context.getBean("Student_Declan");		
		//Program program = (Program) context.getBean("Program_SoftwareDev");
		Deferral deferral = (Deferral) context.getBean("Deferral_deferADF");
		
		
		//assertTrue(declan.getProgram().equals(program));
		
		Program pTest = declan.getProgram();
		
		assertEquals("CR106", pTest.getProgramCode());
		assertEquals("Software Development", pTest.getProgramName());
	}*/
	



}
