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

import com.citonline.db.interfaces.impl.ModuleJdbcDaoSupport;
import com.citonline.domain.Module;
import com.citonline.interfaces.impl.ModuleImpl;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;

/**
 * @author Tim Wallace
 *
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DbUnitTestExecutionListener.class })
public class ModuleImplJdbcDAOSupportTest {
	
	@Autowired
	ApplicationContext autoWireContext;
	@Autowired
	ModuleJdbcDaoSupport moduleJdbcDaoSupportObject;

	final Logger logger = Logger.getLogger(ModuleImplJdbcDAOSupportTest.class);


	/**
	 * @author Tim Wallace
	 *
	 *Test to create a module
	 */
	
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testCreateModule() {
		int nbRowsBefore = moduleJdbcDaoSupportObject.countRows();
  		
		moduleJdbcDaoSupportObject.createModule("soft666", "34557", "MCQ Hacks", 7);
		int nbRowsAfter = moduleJdbcDaoSupportObject.countRows();
		assertEquals(nbRowsBefore,nbRowsAfter-1);
  	}
 
  	/**
  	 * @author Tim Wallace
  	 *
  	 *	Test to delete a module by CRN
  	 */
  	
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testDeleteModuleInteger() {	
  		
		moduleJdbcDaoSupportObject.createModule("soft666", "34557", "MCQ Hacks", 2);
		
		int nbRowsBefore = moduleJdbcDaoSupportObject.countRows();
		
  		moduleJdbcDaoSupportObject.deleteModule("34557");

  		int nbRowsAfter = moduleJdbcDaoSupportObject.countRows();
		assertEquals(nbRowsBefore,nbRowsAfter+1);
  	}

  	/**
  	 * @author Tim Wallace
  	 *
  	 *Test to get module by CRN 
  	 */
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testGetModuleString() {
  		moduleJdbcDaoSupportObject.createModule("soft666", "34557", "MCQ Hacks", 2);
		Module mod = moduleJdbcDaoSupportObject.getModule("34557");
		
		assertEquals("soft666",mod.getCode());
		assertEquals("34557",mod.getCrn());
		assertEquals("MCQ Hacks",mod.getName());
		assertEquals(2,mod.getSemester());
		
		moduleJdbcDaoSupportObject.deleteModule("34557");	
  	}

  	/**
  	 * @author Tim Wallace
  	 *
  	 *	Test for the modules list
  	 */
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testListModules() {
		List<ModuleImpl> mod_list = moduleJdbcDaoSupportObject.listModules();
		
		assertTrue(mod_list.size()> 0);
		
		//assertEquals(2, mod_list.size());
		
		for(ModuleImpl l : mod_list){
			String fn = l.getCode();
			
			boolean is8020 = fn.equals("SOFT8020");
			boolean is666 = fn.equals("soft666");
			assertTrue( is8020 || is666 );
		}
  	}
  
  	/**
  	 * @author Tim Wallace
  	 *
  	 *Test to update the module code 
  	 */
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testUpdateModuleCodeString() {
		Module mod = moduleJdbcDaoSupportObject.getModule("34557");
		assertEquals("soft666",mod.getCode());
		
		moduleJdbcDaoSupportObject.updateModule("soft666", "34557", "MCQ Hacks", 2);
		
		mod = moduleJdbcDaoSupportObject.getModule("soft666");
		assertEquals("soft666",mod.getCode());
  	}
  
}
