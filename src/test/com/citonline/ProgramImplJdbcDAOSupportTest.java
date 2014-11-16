/**
 * @author Tim Wallace
 *
 * Date Created: 13.11.14
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

	final Logger logger = Logger.getLogger(ModuleImplJdbcDAOSupportTest.class);


  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testCreateProgam() {
		int nbRows = programJdbcDaoSupportObject.countRows();
		assertEquals(2,nbRows);
  		
		programJdbcDaoSupportObject.createProgram("DNET4", "DNET4");
		nbRows = programJdbcDaoSupportObject.countRows();
		assertEquals(3,nbRows);
  	}
 
  
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testDeleteProgramInteger() {
  		programJdbcDaoSupportObject.deleteProgram(3);

		int nbRows = programJdbcDaoSupportObject.countRows();
		assertEquals(1,nbRows);
  	}

  	
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testGetProgramInt() {
		Program prog = programJdbcDaoSupportObject.getProgram(1);
		
		assertEquals("1",prog.getProgramId());
		assertEquals("DCOM4",prog.getProgramName());
		assertEquals("DCOM4",prog.getProgramCode());
  	}
  	
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testListPrograms() {
		List<ProgramImpl> prog_list = programJdbcDaoSupportObject.listPrograms();
		
		assertEquals(2, prog_list.size());
		
		for(ProgramImpl l : prog_list){
			String fn = l.getProgramCode();
			
			boolean isC = fn.equals("DCOM4");
			boolean isN = fn.equals("DNET4");
			assertTrue( isC || isN );
		}
  	}
  
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
