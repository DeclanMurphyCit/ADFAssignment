/**
 * @author Fabien
 *
 * @since 3 nov. 2014
 */
package com.citonline;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.expression.spel.ast.Projection;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.cit.online.db.interfaces.impl.LecturerJdbcTemplate;
import com.cit.online.db.interfaces.impl.ProgramJdbcTemplate;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

/**
 * @author Fabien
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:configuration.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DbUnitTestExecutionListener.class })
public class LecturerImplJdbcTemplateTest {
	
	@Autowired
	ApplicationContext autoWireContext;
	@Autowired
	LecturerJdbcTemplate lecJT;

	final Logger logger = Logger.getLogger(LecturerImplJdbcTemplateTest.class);

	/**
	 * @author Fabien
	 * 
	 * Test method for {@link com.cit.online.db.interfaces.impl.LecturerJdbcTemplate#createLecturer(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)}.
	 * 
	 * This method tests the creation of a new Lecturer providing the  these params:
	 * firstName, lastName, email, phoneNumber, roomNumber.
	 * 
	 * It will check the number of rows added only. The details of the record would be
	 * check in another test.
	 * 
	 */
	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testCreateLecturerStringStringStringStringString() {
		lecJT.createLecturer("Donna", "OShea", "donna.oshea@cit.ie", "0123456789", "C123");
		
		int nbRow = lecJT.countRows();
		assertEquals(1,nbRow);
	}

	/**
	 * @author Fabien
	 * 
	 * Test method for {@link com.cit.online.db.interfaces.impl.LecturerJdbcTemplate#createLecturer(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Integer)}.
	 *
	 * This method tests the creation of a new Lecturer providing these params:
	 * firstName, lastName, email, phoneNumber, roomNumber, idManagedProgram.
	 * 
	 * It will check the number of rows added and the detail of the record.
	 * 
	 */
	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testCreateLecturerStringStringStringStringStringInteger() {
		ProgramJdbcTemplate progJT = (ProgramJdbcTemplate) autoWireContext.getBean("ProgJdbcTemplate");
		progJT.createProgram("DCOM4", "DCOM4");
		
		fail("Cannot go further because of ProgramJdbcTemplate does not allow me to get a program from name");
		int progID = 1; // progJT.getProgram("DCOM4")
		
		lecJT.createLecturer("Donna", "OShea", "donna.oshea@cit.ie", "0123456789", "C123", progID);
		
		int nbRow = lecJT.countRows();
		assertEquals(1,nbRow);
	}

	/**
	 * @author Fabien
	 * 
	 * Test method for {@link com.cit.online.db.interfaces.impl.LecturerJdbcTemplate#deleteLecturer(java.lang.Integer)}.
	 * 
	 * This method tests the deletion of a Lecturer providing its id.
	 * 
	 * After creating a random Lecturer, it will remove it from the table and checks the number of row.
	 */
	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testDeleteLecturerInteger() {
		lecJT.createLecturer("Donna", "OShea", "donna.oshea@cit.ie", "0123456789", "C123");
		
		int nbRow = lecJT.countRows();
		assertEquals(1,nbRow);
		
		lecJT.deleteLecturer("Donna","OShea");
		assertEquals(0,nbRow);
	}

	/**
	 * @author Fabien
	 * 
	 * Test method for {@link com.cit.online.db.interfaces.impl.LecturerJdbcTemplate#deleteLecturer(java.lang.String, java.lang.String)}.
	 * 
	 * This method tests the deletion of a Lecturer providing its firstName and lastName.
	 * 
	 * After adding a Lecturer in the db, it will remove it from the table and checks the number of row.
	 */
	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testDeleteLecturerStringString() {
		lecJT.createLecturer("Donna", "OShea", "donna.oshea@cit.ie", "0123456789", "C123");
		
		int nbRow = lecJT.countRows();
		assertEquals(1,nbRow);
		
		lecJT.deleteLecturer("Donna","OShea");
		assertEquals(0,nbRow);
	}

	/**
	 * @author Fabien
	 * 
	 * Test method for {@link com.cit.online.db.interfaces.impl.LecturerJdbcTemplate#getLecturer(java.lang.Integer)}.
	 * 
	 * This method tests if we are able to get a Lecturer from the database providing its id
	 * and create its corresponding object.
	 * 
	 * After creating a random Lecturer, it will add it in the db, then try to get the details
	 * of the record in order to generate a new Lecturer which should be exactly the same as the first one.
	 */
	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testGetLecturerInteger() {
		fail("Not yet implemented");
	}

	/**
	 * @author Fabien
	 * 
	 * Test method for {@link com.cit.online.db.interfaces.impl.LecturerJdbcTemplate#getLecturer(java.lang.String, java.lang.String)}.
	 * 
	 * This method tests if we are able to get a Lecturer from the database providing its firstName and lastName
	 * and create its corresponding object.
	 * 
	 * After creating a random Lecturer, it will add it in the db, then try to get the details
	 * of the record in order to generate a new Lecturer which should be exactly the same as the first one.
	 */
	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testGetLecturerStringString() {
		fail("Not yet implemented");
	}

	/**
	 * @author Fabien
	 * 
	 * Test method for {@link com.cit.online.db.interfaces.impl.LecturerJdbcTemplate#listLecturers()}.
	 * 
	 * This method tests if we are able to get the list of all Lecturers presents in the db.
	 * 
	 * After adding some Lecturers in the db, it will try to get all of them, and check only
	 * their firstName and lastName (other tests checks all the fields).
	 */
	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testListLecturers() {
		fail("Not yet implemented");
	}

	/**
	 * @author Fabien
	 * 
	 * Test method for {@link com.cit.online.db.interfaces.impl.LecturerJdbcTemplate#updateLecturerEmail(java.lang.String, java.lang.String, java.lang.String)}.
	 * 
	 * This methods checks the update of the email of a Lecturer, providing its firstName and lastName.
	 * 
	 * It will check the first email, then change it and tests the email again to verify the update.
	 */
	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testUpdateLecturerEmailStringStringString() {
		fail("Not yet implemented");
	}

	/**
	 * @author Fabien
	 * 
	 * Test method for {@link com.cit.online.db.interfaces.impl.LecturerJdbcTemplate#updateLecturerEmail(java.lang.Integer, java.lang.String)}.
	 * 
	 * This methods checks the update of the email of a Lecturer, providing its id.
	 * 
	 * It will check the first email, then change it and tests the email again to verify the update.
	 */
	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testUpdateLecturerEmailIntegerString() {
		fail("Not yet implemented");
	}

	/**
	 * @author Fabien
	 * 
	 * Test method for {@link com.cit.online.db.interfaces.impl.LecturerJdbcTemplate#updateLecturerRoomNumber(java.lang.String, java.lang.String, java.lang.String)}.
	 * 
	 * This methods checks the update of the roomNumber of a Lecturer, providing its firstName and lastName.
	 * 
	 * It will check the first roomNumber, then change it and tests the roomNumber again to verify the update.
	 */
	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testUpdateLecturerRoomNumberStringStringString() {
		fail("Not yet implemented");
	}

	/**
	 * @author Fabien
	 * 
	 * Test method for {@link com.cit.online.db.interfaces.impl.LecturerJdbcTemplate#updateLecturerRoomNumber(java.lang.Integer, java.lang.String)}.
	 * 
	 * This methods checks the update of the roomNumber of a Lecturer, providing its id.
	 * 
	 * It will check the first roomNumber, then change it and tests the roomNumber again to verify the update.
	 */
	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testUpdateLecturerRoomNumberIntegerString() {
		fail("Not yet implemented");
	}

	/**
	 * @author Fabien
	 * 
	 * Test method for {@link com.cit.online.db.interfaces.impl.LecturerJdbcTemplate#updateLecturerManagedProgram(java.lang.String, java.lang.String, java.lang.Integer)}.
	 * 
	 * This methods checks the update of the managedProgram of a Lecturer, providing its firstName and lastName.
	 * 
	 * It will check the first managedProgram, then change it and tests the managedProgram again to verify the update.
	 */
	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testUpdateLecturerManagedProgramStringStringInteger() {
		fail("Not yet implemented");
	}

	/**
	 * @author Fabien
	 * 
	 * Test method for {@link com.cit.online.db.interfaces.impl.LecturerJdbcTemplate#updateLecturerManagedProgram(java.lang.Integer, java.lang.Integer)}.
	 * 
	 * This methods checks the update of the managedProgram of a Lecturer, providing its id.
	 * 
	 * It will check the first managedProgram, then change it and tests the managedProgram again to verify the update.
	 */
	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testUpdateLecturerManagedProgramIntegerInteger() {
		fail("Not yet implemented");
	}

	/**
	 * @author Fabien
	 * 
	 * Test method for {@link com.cit.online.db.interfaces.impl.LecturerJdbcTemplate#addTaughtModule(java.lang.String, java.lang.String, java.lang.Integer)}.
	 * 
	 * This methods checks the addition of a module that a Lecturer teaches, providing its firstName and lastName.
	 * 
	 * It will check the absence of taught modules, then add one and tests again to verify the update.
	 * It will check the number of row only (checking the module is part of another test file).
	 */
	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testAddTaughtModuleStringStringInteger() {
		fail("Not yet implemented");
	}

	/**
	 * @author Fabien
	 * 
	 * Test method for {@link com.cit.online.db.interfaces.impl.LecturerJdbcTemplate#addTaughtModule(java.lang.Integer, java.lang.Integer)}.
	 * 
	 * This methods checks the addition of a module that a Lecturer teaches, providing its id.
	 * 
	 * It will check the absence of taught modules, then add one and tests again to verify the update.
	 * It will check the number of row only (checking the module is part of another test file).
	 */
	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testAddTaughtModuleIntegerInteger() {
		fail("Not yet implemented");
	}

	/**
	 * @author Fabien
	 * 
	 * Test method for {@link com.cit.online.db.interfaces.impl.LecturerJdbcTemplate#addTaughtModule(java.lang.String, java.lang.String, java.util.List)}.
	 * 
	 * This methods checks the addition of a list of modules that a Lecturer teaches, providing its firstName and lastName.
	 * 
	 * It will check the absence of taught modules, then add them and tests again to verify the update.
	 * It will check the number of row only (checking the module is part of another test file).
	 */
	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testAddTaughtModuleStringStringListOfInteger() {
		fail("Not yet implemented");
	}

	/**
	 * @author Fabien
	 * 
	 * Test method for {@link com.cit.online.db.interfaces.impl.LecturerJdbcTemplate#addTaughtModule(java.lang.Integer, java.util.List)}.
	 * 
	 * This methods checks the addition of a list of modules that a Lecturer teaches, providing its id.
	 * 
	 * It will check the absence of taught modules, then add them and tests again to verify the update.
	 * It will check the number of row only (checking the module is part of another test file).
	 */
	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testAddTaughtModuleIntegerListOfInteger() {
		fail("Not yet implemented");
	}

	/**
	 * @author Fabien
	 * 
	 * Test method for {@link com.cit.online.db.interfaces.impl.LecturerJdbcTemplate#removeTaughtModule(java.lang.String, java.lang.String, java.lang.Integer)}.
	 * 
	 * This methods checks the deletion of a module that a Lecturer stop teaching, providing its firstName and lastName.
	 * 
	 * It will add two modules, then delete one and tests again to verify the update.
	 * It will check the number of row only and the id to be sure it deletes the good one
	 * (checking the module is part of another test file).
	 */
	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testRemoveTaughtModuleStringStringInteger() {
		fail("Not yet implemented");
	}

	/**
	 * @author Fabien
	 * 
	 * Test method for {@link com.cit.online.db.interfaces.impl.LecturerJdbcTemplate#removeTaughtModule(java.lang.Integer, java.lang.Integer)}.
	 * 
	 * This methods checks the deletion of a module that a Lecturer stop teaching, providing its id.
	 * 
	 * It will add two modules, then delete one and tests again to verify the update.
	 * It will check the number of row only and the id to be sure it deletes the good one
	 * (checking the module is part of another test file).
	 */
	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testRemoveTaughtModuleIntegerInteger() {
		fail("Not yet implemented");
	}

	/**
	 * @author Fabien
	 * 
	 * Test method for {@link com.cit.online.db.interfaces.impl.LecturerJdbcTemplate#removeTaughtModule(java.lang.String, java.lang.String, java.util.List)}.
	 * 
	 * This methods checks the deletion of a list modules that a Lecturer stop teaching, providing its firstName and lastName.
	 * 
	 * It will add many modules, then delete the given lists and tests again to verify the update.
	 * It will check the ids of the number of rows and the ids to be sure it deletes the good ones
	 * (checking the module is part of another test file).
	 */
	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testRemoveTaughtModuleStringStringListOfInteger() {
		fail("Not yet implemented");
	}

	/**
	 * @author Fabien
	 * 
	 * Test method for {@link com.cit.online.db.interfaces.impl.LecturerJdbcTemplate#removeTaughtModule(java.lang.Integer, java.util.List)}.
	 * 
	 * This methods checks the deletion of a list of modules that a Lecturer stop teaching, providing its id.
	 * 
	 * It will add many modules, then delete the given lists and tests again to verify the update.
	 * It will check the number of row only and the id to be sure it deletes the good ones
	 * (checking the module is part of another test file).
	 */
	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testRemoveTaughtModuleIntegerListOfInteger() {
		fail("Not yet implemented");
	}

}
