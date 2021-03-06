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

import com.citonline.db.interfaces.impl.ProgramJdbcDaoSupport;
import com.citonline.domain.Program;
import com.citonline.interfaces.impl.ProgramImpl;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

/**
 * @author Tim Wallace
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DbUnitTestExecutionListener.class })
public class ProgramImplJdbcDAOSupportTest {
	
	@Autowired
	ApplicationContext autoWireContext;
	@Autowired
	ProgramJdbcDaoSupport programJdbcDaoSupportObject;

	final Logger logger = Logger.getLogger(ProgramImplJdbcDAOSupportTest.class);

	/**
	 * @author Tim Wallace
	 * Test to create a program
	 *
	 */
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testCreateProgam() {
		int nbRowsBefore = programJdbcDaoSupportObject.countRows();
		
  		
		programJdbcDaoSupportObject.createProgram("DNET4", "DNET4");
		int nbRowsAfter = programJdbcDaoSupportObject.countRows();
		assertEquals(nbRowsBefore,nbRowsAfter-1);
  	}
 
  	/**
  	 * @author Tim Wallace
  	 * 
  	 * Test to delete a program by id
  	 */
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testDeleteProgramInteger() {
  		programJdbcDaoSupportObject.deleteProgram(3);

		int nbRows = programJdbcDaoSupportObject.countRows();
		assertEquals(1,nbRows);
  	}

  	/**
  	 * @author Tim Wallace
  	 *
  	 * Test to get program by id
  	 */
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testGetProgramInt() {
		Program prog = programJdbcDaoSupportObject.getProgram(1);
		
		assertEquals(1,prog.getProgramId());
		assertEquals("DCOM4",prog.getProgramName());
		assertEquals("DCOM4",prog.getProgramCode());
  	}
  	
  
  	/**
  	 * @author Tim Wallace
  	 * 
  	 * Test the list of programs
  	 */
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testListPrograms() {
		List<Program> prog_list = programJdbcDaoSupportObject.listPrograms();
		
		assertTrue(prog_list.size() > 0);
		
		for(Program l : prog_list){
			String fn = l.getProgramCode();
			
			boolean isC = fn.equals("DCOM4");
			boolean isN = fn.equals("DNET4");
			assertTrue( isC || isN );
		}
  	}
  
  	/**
  	 * @author Tim Wallace
  	 *
  	 * Test to update the program code
  	 */
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testUpdateProgramCodeString() {
  		Program prog = programJdbcDaoSupportObject.getProgram(1);
		assertEquals("DCOM4",prog.getProgramCode());
		
		Program prog2 = programJdbcDaoSupportObject.getProgram(1);
		programJdbcDaoSupportObject.updateProgramCode(1, "DCOM4");
		
		prog2 = programJdbcDaoSupportObject.getProgram(1);
		assertEquals("DCOM4",prog.getProgramCode());
  	}
  
}
