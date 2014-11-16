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


  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testCreateModule() {
		int nbRows = moduleJdbcDaoSupportObject.countRows();
		assertEquals(2,nbRows);
  		
		moduleJdbcDaoSupportObject.createModule("soft666", "34557", "MCQ Hacks", 2);
		nbRows = moduleJdbcDaoSupportObject.countRows();
		assertEquals(3,nbRows);
  	}
 
  
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testDeleteModuleInteger() {
  		moduleJdbcDaoSupportObject.deleteModule("34557");

		int nbRows = moduleJdbcDaoSupportObject.countRows();
		assertEquals(1,nbRows);
  	}

  	
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testGetModuleString() {
		Module mod = moduleJdbcDaoSupportObject.getModule("34557");
		
		assertEquals("soft345",mod.getCode());
		assertEquals("8779",mod.getCrn());
		assertEquals("Stress Meltdowns 101",mod.getName());
		assertEquals("1",mod.getSemester());
  	}

  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testListModules() {
		List<ModuleImpl> mod_list = moduleJdbcDaoSupportObject.listModules();
		
		assertEquals(2, mod_list.size());
		
		for(ModuleImpl l : mod_list){
			String fn = l.getCode();
			
			boolean is8020 = fn.equals("SOFT8020");
			boolean is666 = fn.equals("soft666");
			assertTrue( is8020 || is666 );
		}
  	}
  
  	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testUpdateModuleCodeString() {
		Module mod = moduleJdbcDaoSupportObject.getCode("soft666");
		assertEquals("soft666",mod.getCode());
		
		Module mod2 = moduleJdbcDaoSupportObject.getModule("soft666");
		moduleJdbcDaoSupportObject.updateModule("soft666", "34557", "MCQ Hacks", 2);
		
		mod = moduleJdbcDaoSupportObject.getCode("soft666");
		assertEquals("soft666",mod.getCode());
  	}
  
}
