package com.citonline;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.citonline.db.interfaces.impl.DeferralJdbDaoSupport;
import com.citonline.db.interfaces.impl.StudentJdbcDaoSupport;
import com.citonline.domain.Deferral;
import com.citonline.domain.Module;
import com.citonline.interfaces.impl.StudentImpl;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DbUnitTestExecutionListener.class })

public class DeferralJdbcDaoSupportTest {

	@Autowired
	ApplicationContext autoWireContext;
	@Autowired
	DeferralJdbDaoSupport DefferalDaoObj;

	final Logger logger = Logger.getLogger(DeferralJdbcDaoSupportTest.class);
	
	@Test
  	@DatabaseSetup(value="classpath:databaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testCreateDeferral() {
		int nbRowsBefore = DefferalDaoObj.countRows();
		
		Deferral def1 = (Deferral) autoWireContext.getBean("def1");
  		
		DefferalDaoObj.createDeferral(def1.get, declan.getLastName(),declan.getStudentNumber(), 
				 declan.getEmail(), declan.getPhoneNumber(), declan.getAddressLine1(), declan.getAddressLine2());
		
		int nbRowsAfter = studentJT.countRows();

		assertEquals(nbRowsBefore,nbRowsAfter+1);
  	}
	@Test
	public void testCreateDeferral(Calendar date, int id_program, int id_student, boolean proframDeferred, int status)
	{}
	@Test
	public void testDeleteDeferral(int id_defferal)
	{}
	@Test
	void testUpdateDeferralStatus(int status, String firstName, String lastName)
	{}
	@Test
	void testUpdateDeferralStatus(int status, String studentNumber)
	{}
	@Test
	void testAddDeferredModules(int id_deferral, final ArrayList<Module> defered) 
	{}
	@Test
	public void testGetDeferredModules(int deferral)
	{}
	@Test
	public void testGetDeferredModulesName(String firstName, String lastName)
	{}
	@Test
	public void testGetDeferredModuleStudentNumber(String studentNumber)
	{}
	@Test
	public void testGetDeferralsStudentNumber(String studentNumber)
	{}
	@Test
	public void testGetDeferralsStudentName(String firstName, String lastName)
	{}
	@Test
	public void testGetDefferalsStatus(int status)
	{
		return null;}

}
