package com.citonline;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.cit.online.db.interfaces.impl.StudentJdbcDaoSupport;
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
	 * INPUT: The database is populated with two first Lecturers.
	 * EXPECTED OUTPUT: The database is populated with 3 Lecturers.
  	 */
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testCreateLecturerStringStringStringStringString() {
		int nbRowsBefore = studentJT.countRows();
		
		StudentImpl declan = (StudentImpl) autoWireContext.getBean("Student_Declan");
  		
		studentJT.createStudent(declan.getFirstName(), declan.getLastName(),declan.getStudentNumber(), 
				 declan.getEmail(), declan.getPhoneNumber(), declan.getAddressLine1(), declan.getAddressLine2());
		
		int nbRowsAfter = studentJT.countRows();

		assertEquals(nbRowsBefore,nbRowsAfter+1);
  	}
	
	

}