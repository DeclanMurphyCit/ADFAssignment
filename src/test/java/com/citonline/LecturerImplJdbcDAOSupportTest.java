/**
 * @author Fabien
 *
 * @since 3 nov. 2014
 */
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

import com.citonline.db.interfaces.impl.LecturerJdbcDaoSupport;
import com.citonline.domain.Module;
import com.citonline.interfaces.impl.LecturerImpl;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

/**
 * @author Fabien
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DbUnitTestExecutionListener.class })
public class LecturerImplJdbcDAOSupportTest {
	
	@Autowired
	ApplicationContext autoWireContext;
	@Autowired
	LecturerJdbcDaoSupport lecturerJdbcDaoSupportObject;

	final Logger logger = Logger.getLogger(LecturerImplJdbcDAOSupportTest.class);

	/**
  	 * @author Fabien
  	 * 
  	 * This method tests the creation of a new Lecturer providing the  these params:
  	 * firstName, lastName, email, phoneNumber, roomNumber.
  	 * 
	 * It will check the number of rows added and not the detail of the record.
  	 * 
	 * INPUT: The database is populated with two first Lecturers.
	 * EXPECTED OUTPUT: The database is populated with 3 Lecturers.
  	 */
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testCreateLecturerStringStringStringStringString() {
		int nbRows = lecturerJdbcDaoSupportObject.countRows();
		assertEquals(2,nbRows);
  		
		lecturerJdbcDaoSupportObject.createLecturer("Vincent", "Ryan", "vincent.ryan@cit.ie", "9876543210", "C123");
		nbRows = lecturerJdbcDaoSupportObject.countRows();
		assertEquals(3,nbRows);
  	}
  
  	/**
  	 * @author Fabien
  	 * 
  	 * It will check the number of rows added and the detail of the record.
  	 * 
	 * INPUT: The database is populated with two first Lecturers.
	 * EXPECTED OUTPUT: The database is populated with 3 Lecturers.
	 * EXPECTED OUTPUT: The 3rd Lecturer reflect the data we put in (same email ...).
  	 */
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testCreateLecturerStringStringStringStringStringInteger() {
		lecturerJdbcDaoSupportObject.createLecturer("Vincent", "Ryan", "vincent.ryan@cit.ie", "9876543210", "C123", 1);
  		
		int nbRows = lecturerJdbcDaoSupportObject.countRows();
		assertEquals(3,nbRows);
  		
		// Not sure if this part is needed: it is relevant to check the whole record
		// But it then tests also the getLecturer(String firstName, String LastName) method.
		LecturerImpl vincent = lecturerJdbcDaoSupportObject.getLecturer("Vincent", "Ryan");
		assertEquals("vincent.ryan@cit.ie",vincent.getEmail());
		assertEquals("9876543210", vincent.getPhoneNumber());
		assertEquals("C123", vincent.getRoomNumber());
		assertEquals(1, vincent.getManagedProgram());
  	}
  
  	/**
  	 * @author Fabien
  	 * 
  	 * This method tests the deletion of a Lecturer providing its id.
  	 * 
	 * It will check the number of rows and the details of the remaining record.
	 * 
	 * INPUT: The database is populated with two first Lecturers.
	 * EXPECTED OUTPUT: The database is populated with 1 Lecturer.
	 * EXPECTED OUTPUT: The remaining Lecturer is the good one.
  	 */
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testDeleteLecturerInteger() {
		lecturerJdbcDaoSupportObject.deleteLecturer("Donna","OShea");

		int nbRows = lecturerJdbcDaoSupportObject.countRows();
		assertEquals(1,nbRows);
  		
		// Not sure if this part is needed: it is relevant to check the remaining record
		// But it then tests also the getLecturer(Integer id) method.
		LecturerImpl ted = lecturerJdbcDaoSupportObject.getLecturer(2);
		assertEquals("Ted", ted.getFirstName());
  	}
  
  	/**
  	 * @author Fabien
  	 * 
  	 * This method tests the deletion of a Lecturer providing its firstName and lastName.
  	 * 
	 * It will check the number of rows only.
	 * 
	 * INPUT: The database is populated with two first Lecturers.
	 * EXPECTED OUTPUT: The database is populated with 1 Lecturer.
  	 */
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testDeleteLecturerStringString() {
		lecturerJdbcDaoSupportObject.deleteLecturer(1);

		int nbRows = lecturerJdbcDaoSupportObject.countRows();
		assertEquals(1,nbRows);
  	}
  
  	/**
  	 * @author Fabien
  	 * 
  	 * This method tests if we are able to get a Lecturer from the database providing its id
  	 * and create its corresponding object.
  	 * 
	 * It will check the whole details of the record.
	 * 
	 * INPUT: at least the lecturer Ted Scully.
	 * EXPECTED OUTPUT: the returned lecturer is Ted Scully.
  	 */
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testGetLecturerInteger() {
		LecturerImpl ted = lecturerJdbcDaoSupportObject.getLecturer(2);
		
		assertEquals("Ted",ted.getFirstName());
		assertEquals("Scully",ted.getLastName());
		assertEquals("ted.scully@cit.ie",ted.getEmail());
		assertEquals("0123456789",ted.getPhoneNumber());
		assertEquals("B180A",ted.getRoomNumber());
  	}
  
  	/**
  	 * @author Fabien
  	 * 
  	 * This method tests if we are able to get a Lecturer from the database providing its firstName and lastName
  	 * and create its corresponding object.
  	 * 
	 * It will check the whole details of the record.
	 * 
	 * INPUT: at least the lecturer Ted Scully.
	 * EXPECTED OUTPUT: the returned lecturer is Ted Scully.
  	 */
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testGetLecturerStringString() {
		LecturerImpl ted = lecturerJdbcDaoSupportObject.getLecturer("Ted","Scully");
		
		//assertEquals(2,ted.getId());
		assertEquals("ted.scully@cit.ie",ted.getEmail());
		assertEquals("0123456789",ted.getPhoneNumber());
		assertEquals("B180A",ted.getRoomNumber());
  	}
  
  	/**
  	 * @author Fabien
  	 * 
  	 * This method tests if we are able to get the list of all Lecturers presents in the db.
  	 * 
	 * It will check only the number of rows and a few details of the records.
	 * 
	 * INPUT: 2 lecturers: Donna OShea and Ted Scully.
	 * EXPECTED OUTPUT: those two lecturer are there.
  	 */
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testListLecturers() {
		List<LecturerImpl> lect_list = lecturerJdbcDaoSupportObject.listLecturers();
		
		assertEquals(2, lect_list.size());
		
		for(LecturerImpl l : lect_list){
			String fn = l.getFirstName();
			String ln = l.getLastName();
			
			boolean isDonna = fn.equals("Donna") && ln.equals("OShea");
			boolean isTed = fn.equals("Ted") && ln.equals("Scully");
			assertTrue( isDonna || isTed );
		}
  	}
  
  	/**
  	 * @author Fabien
  	 * 
  	 * This methods checks the update of the email of a Lecturer, providing its firstName and lastName.
  	 * 
  	 * It will check the first email, then change it and tests the email again to verify the update.
	 * 
	 * INPUT: lecturer Donna OShea with mail= "donna.oshea@cit.ie".
	 * EXPECTED OUTPUT: new mail = "bestTeacher@cit.ie".
  	 */
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testUpdateLecturerEmailStringStringString() {
		LecturerImpl donna = lecturerJdbcDaoSupportObject.getLecturer(1);
		assertEquals("donna.oshea@cit.ie",donna.getEmail());
		
		lecturerJdbcDaoSupportObject.updateLecturerEmail("Donna","OShea", "bestLecturer@cit.ie");
		donna = lecturerJdbcDaoSupportObject.getLecturer(1);
		assertEquals("bestLecturer@cit.ie",donna.getEmail());
  	}
  
  	/**
  	 * @author Fabien
  	 * 
  	 * This methods checks the update of the email of a Lecturer, providing its id.
  	 * 
  	 * It will check the first email, then change it and tests the email again to verify the update.
	 * 
	 * INPUT: lecturer Donna OShea with mail= "donna.oshea@cit.ie".
	 * EXPECTED OUTPUT: new mail = "bestTeacher@cit.ie".
  	 */
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testUpdateLecturerEmailIntegerString() {
		LecturerImpl donna = lecturerJdbcDaoSupportObject.getLecturer(1);
		assertEquals("donna.oshea@cit.ie",donna.getEmail());
		
		lecturerJdbcDaoSupportObject.updateLecturerEmail(1, "bestLecturer@cit.ie");
		donna = lecturerJdbcDaoSupportObject.getLecturer(1);
		assertEquals("bestLecturer@cit.ie",donna.getEmail());
  	}
  
  	/**
  	 * @author Fabien
  	 * 
  	 * This methods checks the update of the roomNumber of a Lecturer, providing its firstName and lastName.
  	 * 
  	 * It will check the first roomNumber, then change it and tests the roomNumber again to verify the update.
	 * 
	 * INPUT: lecturer Donna OShea with roomNumber= "B180A".
	 * EXPECTED OUTPUT: new roomNumber = "HeadRoom".
  	 */
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testUpdateLecturerRoomNumberStringStringString() {
		LecturerImpl donna = lecturerJdbcDaoSupportObject.getLecturer(1);
		assertEquals("B180A",donna.getRoomNumber());
		
		lecturerJdbcDaoSupportObject.updateLecturerRoomNumber("Donna","OShea", "HeadRoom");
		donna = lecturerJdbcDaoSupportObject.getLecturer(1);
		assertEquals("HeadRoom",donna.getRoomNumber());
  	}
  
  	/**
  	 * @author Fabien
  	 * 
  	 * This methods checks the update of the roomNumber of a Lecturer, providing its id.
  	 * 
  	 * It will check the first roomNumber, then change it and tests the roomNumber again to verify the update.
	 * 
	 * INPUT: lecturer Donna OShea with roomNumber= "B180A".
	 * EXPECTED OUTPUT: new roomNumber = "HeadRoom".
  	 */
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testUpdateLecturerRoomNumberIntegerString() {
		LecturerImpl donna = lecturerJdbcDaoSupportObject.getLecturer(1);
		assertEquals("B180A",donna.getRoomNumber());
		
		lecturerJdbcDaoSupportObject.updateLecturerRoomNumber(1, "HeadRoom");
		donna = lecturerJdbcDaoSupportObject.getLecturer(1);
		assertEquals("HeadRoom",donna.getRoomNumber());
  	}
  
  	/**
  	 * @author Fabien
  	 * 
  	 * This methods checks the update of the managedProgram of a Lecturer, providing its firstName and lastName.
  	 * 
  	 * It will check the first managedProgram, then change it and tests the managedProgram again to verify the update.
	 * 
	 * INPUT: Ted Scully with no managedProgram.
	 * EXPECTED OUTPUT: Ted Scully managed the program of id=1
  	 */
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testUpdateLecturerManagedProgramStringStringInteger() {
		LecturerImpl ted = lecturerJdbcDaoSupportObject.getLecturer(2);
		assertFalse(ted.isProgramManager());
		
		lecturerJdbcDaoSupportObject.updateLecturerManagedProgram("Ted","Scully", 1);
		ted = lecturerJdbcDaoSupportObject.getLecturer(2);
		assertEquals(1,ted.getManagedProgram().getProgramId());
  	}
  

	/**
	 * @author Fabien
	 * 
	 * Test method for {@link com.cit.online.db.interfaces.impl.LecturerJdbcTemplate#updateLecturerManagedProgram(java.lang.Integer, java.lang.Integer)}.
	 * 
	 * This methods checks the update of the managedProgram of a Lecturer, providing its id.
	 * 
	 * It will check the first managedProgram, then change it and tests the managedProgram again to verify the update.
	 * 
	 * INPUT: Ted Scully with no managedProgram.
	 * EXPECTED OUTPUT: Ted Scully managed the program of id=1
	 */
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testUpdateLecturerManagedProgramIntegerInteger() {
		LecturerImpl ted = lecturerJdbcDaoSupportObject.getLecturer(2);
		assertFalse(ted.isProgramManager());
		
		lecturerJdbcDaoSupportObject.updateLecturerManagedProgram(2, 1);
		ted = lecturerJdbcDaoSupportObject.getLecturer(2);
		assertEquals(1,ted.getManagedProgram().getProgramId());
  	}

	/**
	 * @author Fabien
	 * 
	 * Test method for {@link com.cit.online.db.interfaces.impl.LecturerJdbcTemplate#addTaughtModule(java.lang.String, java.lang.String, java.lang.Integer)}.
	 * 
	 * This methods checks the addition of a module that a Lecturer teaches, providing its firstName and lastName.
	 * 
	 * It will check the absence of taught modules, then add one and tests again to verify the update.
	 * It will check the id of the module only.
	 * 
	 * INPUT: Donna OShea with no taught module.
	 * EXPECTED OUTPUT: Donna OShea teaching the module of id=1
	 */
	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testAddTaughtModuleStringStringInteger() {
		LecturerImpl donna = lecturerJdbcDaoSupportObject.getLecturer(1);
		
		assertTrue(donna.getModulesTaught().isEmpty());
		lecturerJdbcDaoSupportObject.addTaughtModule("Donna", "OShea", 1);
		
		donna = lecturerJdbcDaoSupportObject.getLecturer(1);
		assertEquals(1,donna.getModulesTaught().get(0).getId());
	}

	/**
	 * @author Fabien
	 * 
	 * Test method for {@link com.cit.online.db.interfaces.impl.LecturerJdbcTemplate#addTaughtModule(java.lang.Integer, java.lang.Integer)}.
	 * 
	 * This methods checks the addition of a module that a Lecturer teaches, providing its id.
	 * 
	 * It will check the absence of taught modules, then add one and tests again to verify the update.
	 * It will check the id of the module only.
	 * 
	 * INPUT: Donna OShea with no taught module.
	 * EXPECTED OUTPUT: Donna OShea teaching the module of id=1
	 */
	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testAddTaughtModuleIntegerInteger() {
		LecturerImpl donna = lecturerJdbcDaoSupportObject.getLecturer(1);
		
		assertTrue(donna.getModulesTaught().isEmpty());
		lecturerJdbcDaoSupportObject.addTaughtModule(1, 1);
		
		donna = lecturerJdbcDaoSupportObject.getLecturer(1);
		assertEquals(1,donna.getModulesTaught().get(0).getId());
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
	 * 
	 * INPUT: Donna OShea with no taught module.
	 * EXPECTED OUTPUT: Donna OShea teaching 3 modules
	 */
	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testAddTaughtModuleStringStringListOfInteger() {
		LecturerImpl donna = lecturerJdbcDaoSupportObject.getLecturer(1);
		List<Integer> modules = new ArrayList<Integer>();
		modules.add(2);
		modules.add(3);
		modules.add(4);
		
		assertTrue(donna.getModulesTaught().isEmpty());
		lecturerJdbcDaoSupportObject.addTaughtModule("Donna", "OShea", modules);
		
		donna = lecturerJdbcDaoSupportObject.getLecturer(1);
		List<Module> donnasModules = donna.getModulesTaught();
		assertEquals(3, donnasModules.size());
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
	 * 
	 * INPUT: Donna OShea with no taught module.
	 * EXPECTED OUTPUT: Donna OShea teaching 3 modules
	 */
	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testAddTaughtModuleIntegerListOfInteger() {
		LecturerImpl donna = lecturerJdbcDaoSupportObject.getLecturer(1);
		List<Integer> modules = new ArrayList<Integer>();
		modules.add(2);
		modules.add(3);
		modules.add(4);
		
		assertTrue(donna.getModulesTaught().isEmpty());
		lecturerJdbcDaoSupportObject.addTaughtModule(1, modules);
		
		donna = lecturerJdbcDaoSupportObject.getLecturer(1);
		List<Module> donnasModules = donna.getModulesTaught();
		assertEquals(3, donnasModules.size());
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
	 * 
	 * INPUT: Ted Scully teaching 2 modules
	 * EXPECTED OUTPUT: Ted Scully teaching only 1 module
	 */
	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testRemoveTaughtModuleStringStringInteger() {
		LecturerImpl ted = lecturerJdbcDaoSupportObject.getLecturer(2);
		assertEquals(2, ted.getModulesTaught().size());
		
		lecturerJdbcDaoSupportObject.removeTaughtModule("Ted", "Scully", 1);
		ted = lecturerJdbcDaoSupportObject.getLecturer(2);
		assertEquals(1, ted.getModulesTaught().size());
		assertEquals(2,ted.getModulesTaught().get(0).getId());
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
	 * 
	 * INPUT: Ted Scully teaching 2 modules
	 * EXPECTED OUTPUT: Ted Scully teaching only 1 module
	 */
	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testRemoveTaughtModuleIntegerInteger() {
		LecturerImpl ted = lecturerJdbcDaoSupportObject.getLecturer(2);
		assertEquals(2, ted.getModulesTaught().size());
		
		lecturerJdbcDaoSupportObject.removeTaughtModule(2, 1);
		ted = lecturerJdbcDaoSupportObject.getLecturer(2);
		assertEquals(1, ted.getModulesTaught().size());
		assertEquals(2,ted.getModulesTaught().get(0).getId());
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
	 * 
	 * INPUT: Ted Scully teaching 2 modules
	 * EXPECTED OUTPUT: Ted Scully teaching no module
	 */
	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testRemoveTaughtModuleStringStringListOfInteger() {
		LecturerImpl ted = lecturerJdbcDaoSupportObject.getLecturer(2);
		assertEquals(2, ted.getModulesTaught().size());
		
		List<Integer> modules = new ArrayList<Integer>();
		modules.add(1);
		modules.add(2);
		
		lecturerJdbcDaoSupportObject.removeTaughtModule("Ted", "Scully", modules);
		ted = lecturerJdbcDaoSupportObject.getLecturer(2);
		assertTrue(ted.getModulesTaught().isEmpty());
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
	 * 
	 * INPUT: Ted Scully teaching 2 modules
	 * EXPECTED OUTPUT: Ted Scully teaching no module
	 */
	@Test
	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testRemoveTaughtModuleIntegerListOfInteger() {
		LecturerImpl ted = lecturerJdbcDaoSupportObject.getLecturer(2);
		assertEquals(2, ted.getModulesTaught().size());
		
		List<Integer> modules = new ArrayList<Integer>();
		modules.add(1);
		modules.add(2);
		
		lecturerJdbcDaoSupportObject.removeTaughtModule(2, modules);
		ted = lecturerJdbcDaoSupportObject.getLecturer(2);
		assertTrue(ted.getModulesTaught().isEmpty());
	}

}
