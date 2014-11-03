/**
 * @author Fabien
 *
 * @since 3 nov. 2014
 */
package com.citonline;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.citonline.domain.Module;
import com.citonline.domain.Program;
import com.citonline.interfaces.impl.LecturerImpl;

/**
 * @author Fabien
 *
 */
@RunWith(SpringJUnit4ClassRunner.class) @ContextConfiguration({"classpath:config.xml"}) 
public class LectureImplTest {
	
	@Autowired
	ApplicationContext context; 

	/**
	 * @author Fabien
	 * 
	 * @date 3 nov. 2014
	 * 
	 * Test method for {@link com.citonline.interfaces.impl.LecturerImpl#teach(com.citonline.domain.Module)}.
	 * 
	 * This method tests if a Module has been added in the module list of the Lecturer and also check that
	 * the module is effectively the one it has been put in by verifying all its properties.
	 */
	@Test
	public void testTeach() {
//		LecturerImpl donna = new LecturerImpl("Donna", "Oshea", "donna.oshea@cit.ie",
//        		"0123456789", "C123");
		
		LecturerImpl donna = (LecturerImpl) context.getBean("Lecturer_DonnaOshea");
		
		Module module = new Module(1, "SOFT8020", "CRN", "App Dev Frmwk", 7);
		donna.teach(module);
		
		assertTrue(donna.getModulesTaught().contains(module));
		
		Module mTest = donna.getModulesTaught().get(0);
		
		assertEquals(1, mTest.getId());
		assertEquals("SOFT8020", mTest.getCode());
		assertEquals("CRN", mTest.getCrn());
		assertEquals("App Dev Frmwk", mTest.getName());
		assertEquals(7, mTest.getSemester());
	}

	/**
	 * @author Fabien
	 * 
	 * @date 3 nov. 2014
	 * 
	 * Test method for {@link com.citonline.interfaces.impl.LecturerImpl#stopTeach(com.citonline.domain.Module)}.
	 * 
	 * This method tests if a Module has been removed from the module list of the Lecturer.
	 */
	@Test
	public void testStopTeach() {
		LecturerImpl donna = new LecturerImpl("Donna", "Oshea", "donna.oshea@cit.ie",
        		"0123456789", "C123");
		
		Module module = new Module(1, "SOFT8020", "CRN", "App Dev Frmwk", 7);
		donna.teach(module);
		
		assertTrue(donna.getModulesTaught().contains(module));
		
		donna.stopTeach(module);
		
		//assertFalse(donna.getModulesTaught().contains(module));
		assertTrue(donna.getModulesTaught().isEmpty());
	}

	/**
	 * @author Fabien
	 * 
	 * @date 3 nov. 2014
	 * 
	 * Test method for {@link com.citonline.interfaces.impl.LecturerImpl#isProgramManager()}.
	 * 
	 * This method check if a Lecturer is a Program Manager or not, before and after setting a Program,
	 * and also after removing (set to null) the Program that the Lecturer managed.
	 */
	@Test
	public void testIsProgramManager() {
		LecturerImpl donna = new LecturerImpl("Donna", "Oshea", "donna.oshea@cit.ie",
        		"0123456789", "C123");
		
		assertFalse(donna.isProgramManager());
		
		donna.setManagedProgram(new Program("DCOM4", "DCOM4", null));
		
		assertTrue(donna.isProgramManager());
		
		donna.setManagedProgram(null);
		
		assertFalse(donna.isProgramManager());
	}

}
