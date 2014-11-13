package com.citonline;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.citonline.db.interfaces.impl.StudentJdbcDaoSupport;
import com.citonline.domain.Module;
import com.citonline.interfaces.impl.StudentImpl;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DbUnitTestExecutionListener.class })

public class StudentImplJdbcTemplateTest {
	
	@Autowired
	ApplicationContext autoWireContext;
	@Autowired
	StudentJdbcDaoSupport studentJT;

	final Logger logger = Logger.getLogger(StudentImplJdbcTemplateTest.class);
	
	/**
  	 * @author Declan Murphy
  	 * 
  	 * Test method creating a student record in the database
  	 * 
	 * It will check the number of rows added and not the detail of the record.
  	 * 
	 * INPUT: A student is inserted
	 * EXPECTED OUTPUT: The number of students has been incremented by one
  	 */
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testCreateStudent() {
		int nbRowsBefore = studentJT.countRows();
		
		StudentImpl declan = (StudentImpl) autoWireContext.getBean("Student_Declan");
  		
		studentJT.createStudent(declan.getFirstName(), declan.getLastName(),declan.getStudentNumber(), 
				 declan.getEmail(), declan.getPhoneNumber(), declan.getAddressLine1(), declan.getAddressLine2());
		
		int nbRowsAfter = studentJT.countRows();

		assertEquals(nbRowsBefore,nbRowsAfter-1);
  	} 
  	 /**
  	 * @author Declan Murphy
  	 * 
  	 * Test method creating a student record in the database and verifying its content
  	 * 
	 * INPUT: A student is inserted
	 * EXPECTED OUTPUT: The newly inserted student matches testStudent
  	 */
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testCreateStudentAndCheckDetails() {
  		studentJT.createStudent("Mark", "Murry", "R000123546", "mark.murry@mycit.ie", "087543234", "Cork", "Ireland");
  		  		
		StudentImpl testStudent = studentJT.getStudent("R000123546");		
		assertEquals("Mark", testStudent.getFirstName());
		assertEquals("Murry", testStudent.getLastName());
		assertEquals("R000123546", testStudent.getStudentNumber());
		assertEquals("mark.murry@mycit.ie",testStudent.getEmail());
		assertEquals("087543234", testStudent.getPhoneNumber());
		assertEquals("Cork", testStudent.getAddressLine1());
		assertEquals("Ireland", testStudent.getAddressLine2());
  	}
  	  	
	 /**
  	 * @author Declan Murphy
  	 * 
  	 * Test method for deleting a student by id
	 *
	 * INPUT: A student is deleted from the database
	 * EXPECTED OUTPUT: The number of students has been decremented by one
  	 */
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testDeleteStudentByID() {
		
  		int nbRowsBefore = studentJT.countRows();	
  		
		studentJT.deleteStudent(1);		
		int nbRowsAfter = studentJT.countRows();

		assertEquals(nbRowsBefore,nbRowsAfter+1);
  	}
  	
	/**
  	 * @author Declan Murphy
  	 * 
  	 * Test method for deleting a student by student number
	 *
	 * INPUT: A student is deleted from the database
	 * EXPECTED OUTPUT: The number of students has been decremented by one
  	 */
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testDeleteStudentByStudentNumber() {
		
  		int nbRowsBefore = studentJT.countRows();	
  		
		studentJT.deleteStudent("R00058441");		
		int nbRowsAfter = studentJT.countRows();

		assertEquals(nbRowsBefore,nbRowsAfter+1);
  	}
  	
  	/**
  	 * @author Declan Murphy
  	 * 
  	 * Test to check if a student can be retrieved from the database 
	 * 
	 * INPUT: student id
	 * EXPECTED OUTPUT: The returned student
  	 */
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testGetLecturerStudentID() {		
		StudentImpl testStudent = studentJT.getStudent(1);	
		
		assertEquals("Simon", testStudent.getFirstName());
		assertEquals("Casey", testStudent.getLastName());
		assertEquals("R00058441", testStudent.getStudentNumber());
		assertEquals("simon.casey@mycit.ie",testStudent.getEmail());
		assertEquals("098765433", testStudent.getPhoneNumber());
		assertEquals("Tramore", testStudent.getAddressLine1());
		assertEquals("Waterford", testStudent.getAddressLine2());
  	}
  	
  	/**
  	 * @author Declan Murphy
  	 * 
  	 * Test to check if a student can be retrieved from the database 
	 * 
	 * INPUT: student number
	 * EXPECTED OUTPUT: The returned student is verified
  	 */
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testGetLecturerStudentNumber() {		
		StudentImpl testStudent = studentJT.getStudent("R00058441");	
		
		assertEquals("Simon", testStudent.getFirstName());
		assertEquals("Casey", testStudent.getLastName());
		assertEquals("R00058441", testStudent.getStudentNumber());
		assertEquals("simon.casey@mycit.ie",testStudent.getEmail());
		assertEquals("098765433", testStudent.getPhoneNumber());
		assertEquals("Tramore", testStudent.getAddressLine1());
		assertEquals("Waterford", testStudent.getAddressLine2());
  	}
  	
  	/**
  	 * @author Declan Murphy
  	 * 
  	 * This method tests a list of students can be returned from the db
	 * 
	 * INPUT: 2 students: Simon Casey & Mary Casey
	 * EXPECTED OUTPUT: Both students returned in a list
  	 */
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testListLecturers() {
		List<StudentImpl> studentList = studentJT.listStudents();
		
		assertEquals(2, studentList.size());
		
		for(StudentImpl l : studentList){
			String fn = l.getFirstName();
			String ln = l.getLastName();
			
			boolean isSimon = fn.equals("Simon") && ln.equals("Casey");
			boolean isMary = fn.equals("Mary") && ln.equals("Casey");
			assertTrue( isSimon || isMary );
		}
  	}
  	
  	/**
  	 * @author Declan Murphy
  	 * 
  	 * This test verifies that a student email can be updated by passing an id
	 * 
	 * INPUT: Student: Simon Casey with email simon.casey@mycit.ie
	 * EXPECTED OUTPUT: Student: Simon Casey with email simon.casey2@mycit.ie
  	 */
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testUpdateEmailByStudentId() {
		StudentImpl simon = studentJT.getStudent(1);
		assertEquals("simon.casey@mycit.ie",simon.getEmail());
		
		studentJT.updateStudentEmail(1, "simon.casey2@mycit.ie");
		simon = studentJT.getStudent(1);
		assertEquals("simon.casey2@mycit.ie",simon.getEmail());
  	}
  	
  	/**
  	 * @author Declan Murphy
  	 * 
  	 * This test verifys that a student email can be updated by passing in a student number
	 * 
	 * INPUT: Student: Simon Casey with email simon.casey@mycit.ie
	 * EXPECTED OUTPUT: Student: Simon Casey with email simon.casey2@mycit.ie
  	 */
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testUpdateEmailByStudentNumber() {
		StudentImpl simon = studentJT.getStudent("R00058441");
		assertEquals("simon.casey@mycit.ie",simon.getEmail());
		
		studentJT.updateStudentEmail(1, "simon.casey2@mycit.ie");
		simon = studentJT.getStudent(1);
		assertEquals("simon.casey2@mycit.ie",simon.getEmail());
  	}
  	
  	/**
  	 * @author Declan Murphy
  	 * 
  	 * This test verifies that a student address can be updated by passing an id
	 * 
	 * INPUT: Student: Simon Casey with address "Tramore" & "Waterford"
	 * EXPECTED OUTPUT: Student: Simon Casey has new address "Wilton" & "Cork"
  	 */
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testUpdateAddressByStudentId() {
		StudentImpl simon = studentJT.getStudent(1);
		assertEquals("Tramore",simon.getAddressLine1());
		assertEquals("Waterford",simon.getAddressLine2());
		
		studentJT.updateStudentAddress(1, "Wilton", "Cork");
		simon = studentJT.getStudent(1);
		assertEquals("Wilton",simon.getAddressLine1());
		assertEquals("Cork",simon.getAddressLine2());
  	}
  	
  	/**
  	 * @author Declan Murphy
  	 * 
  	 * This test verifies that a student address can be updated by passing a student number
	 * 
	 * INPUT: Student: Simon Casey with address "Tramore" & "Waterford"
	 * EXPECTED OUTPUT: Student: Simon Casey has new address "Wilton" & "Cork"
  	 */
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testUpdateAddressByStudentNumber() {
		StudentImpl simon = studentJT.getStudent(1);
		assertEquals("Tramore",simon.getAddressLine1());
		assertEquals("Waterford",simon.getAddressLine2());
		
		studentJT.updateStudentAddress("R00058441", "Wilton", "Cork");
		simon = studentJT.getStudent(1);
		assertEquals("Wilton",simon.getAddressLine1());
		assertEquals("Cork",simon.getAddressLine2());
  	}
  	
  	/**
  	 * @author Declan Murphy
  	 * 
  	 * This methods checks that a student can enrol a list of modules
	 * 
	 * INPUT: Simon Casey and a list of modules
	 * EXPECTED OUTPUT: Simon Casey and the module are linked by the student_enrolls_for table
  	 */
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testStudentEnrolModules() {
		StudentImpl simon = studentJT.getStudent(1);
		simon.enrolModules(studentJT.getEnrolledModules(1));
		int listSizeBefore = simon.getModuleList().size();
		
		List<Integer> modules = new ArrayList<Integer>();
		modules.add(1);
		modules.add(2);		
		
		studentJT.enrollModules(1, modules);		
		simon.enrolModules(studentJT.getEnrolledModules(1));
		ArrayList<Module> simonsModules = studentJT.getEnrolledModules(1);
		int listSizeAfter = simon.getModuleList().size();	
		assertEquals(listSizeBefore,listSizeAfter-2);
  	} 
  	
  	/**
  	 * @author Declan Murphy
  	 * 
  	 * This methods checks that a student can enrol in a module
	 * 
	 * INPUT: Simon Casey and a module
	 * EXPECTED OUTPUT: Simon Casey and the module are linked by the student_enrolls_for table
  	 */
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testStudentEnrolModule() {
		StudentImpl simon = studentJT.getStudent(1);
		simon.enrolModules(studentJT.getEnrolledModules(1));
		assertFalse(simon.getModuleList().size() > 0);
		
		studentJT.enrollModule(3, simon.getId());
		simon.enrolModules(studentJT.getEnrolledModules(1));
		ArrayList<Module> simonsModules = studentJT.getEnrolledModules(1);
		assertTrue(simonsModules.size() > 0);	
  	} 	
  	

}