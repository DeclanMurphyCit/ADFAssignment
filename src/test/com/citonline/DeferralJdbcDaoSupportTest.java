package com.citonline;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

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
import com.citonline.domain.Deferral;
import com.citonline.domain.DeferralStatus;
import com.citonline.domain.Module;
import com.citonline.interfaces.impl.StudentImpl;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config2.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DbUnitTestExecutionListener.class })
/**
 * 
 * @author peter halligan
 * test class for the deferral dbunit test
 *
 */
public class DeferralJdbcDaoSupportTest {

	@Autowired
	ApplicationContext autoWireContext;
	@Autowired
	DeferralJdbDaoSupport DefferalDaoSupportObj;

	final Logger logger = Logger.getLogger(DeferralJdbcDaoSupportTest.class);
	/**
	 * Testing adding a deferral object to the database
	 * input deferral_bean properties except id
	 * output 1 deferral added to the systerm so the number of rows minus 1 is equal
	 */
	@Test
  	@DatabaseSetup(value="classpath:peterDataBaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
  	public void testCreateDeferral() {
		int rowsBefore = DefferalDaoSupportObj.countRows();
		
		Deferral deferral_bean = (Deferral) autoWireContext.getBean("deferral_bean");
  		
		DefferalDaoSupportObj.createDeferral(deferral_bean.getDefferalDate(),deferral_bean.getProgram(),
				 deferral_bean.getStudent(),deferral_bean.getProgramDeferred(), 1);
		
		int rowsAfter = DefferalDaoSupportObj.countRows();
		
		assertEquals(rowsBefore, rowsAfter-1);
  	}
	/**
	 * test Delete Deferral by id
	 * @param id_deferral
	 * input id of the deferral to be deleted 100
	 * output the status of the deferral == 3 which is the status id of deleted
	 */
	@Test
	@DatabaseSetup(value="classpath:peterDataBaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testDeleteDeferralIntId()
	{
		Deferral deferral_bean = (Deferral) autoWireContext.getBean("deferral_bean");
 
		DefferalDaoSupportObj.deleteDeferral(100);
		deferral_bean = DefferalDaoSupportObj.getDeferralStudentNumber("r1");
		
		assertEquals(3, deferral_bean.getStatus());
	}
	/**
	 * update deferral status by name 
	 * input:
	 * @param int status 
	 * @param string firstName
	 * @param string lastName
	 * output test is the status of the bean retrieved from the db is equals to what was just changed
	 */
	@Test
	@DatabaseSetup(value="classpath:peterDataBaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testUpdateDeferralStatusByName()
	{
		Deferral deferral_bean = (Deferral) autoWireContext.getBean("deferral_bean");
		StudentImpl student = (StudentImpl) autoWireContext.getBean("student_peter");
		DeferralStatus status =DeferralStatus.APPROVED;
		DefferalDaoSupportObj.updateDeferralStatus(status.getStatus(), student.getFirstName(), student.getLastName());
		deferral_bean = DefferalDaoSupportObj.getDeferralStudentNumber(student.getStudentNumber());
		
		assertEquals(status.getStatus(), deferral_bean.getStatus());
	}
	/**
	 * update Deferral status by student number
	 * @param status
	 * @param studentNumber
	 * output the status of the object which is retrieved from the db should be the same as updated
	 */
	@Test
	@DatabaseSetup(value="classpath:peterDataBaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testUpdateDeferralStatusByStudentNumber()
	{
		Deferral deferral_bean = (Deferral) autoWireContext.getBean("deferral_bean");
		StudentImpl student = (StudentImpl) autoWireContext.getBean("student_peter");
		DeferralStatus status =DeferralStatus.DOWNLOADED_BY_LECTURER;
		DefferalDaoSupportObj.updateDeferralStatus(status.getStatus(), student.getStudentNumber());
		deferral_bean = DefferalDaoSupportObj.getDeferralStudentNumber(student.getStudentNumber());
		
		assertEquals(status.getStatus(), deferral_bean.getStatus());
	}
	/**
	 * test add deferred modules  inserting arraylist of 2 modules 
	 * @param id_deferral
	 * @param defered
	 * output should be 3 modules as there was 1 attached to deferalid 102 previously
	 */
	@Test
	@DatabaseSetup(value="classpath:peterDataBaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testAddDeferredModules() 
	{
		ArrayList<Module> deferred_modules_after = new ArrayList<Module>();
		ArrayList<Module> deferred_modules = new ArrayList<Module>();
		
		Module module1 = (Module) autoWireContext.getBean("module1");
		Module module2 = (Module) autoWireContext.getBean("module2");
		
		deferred_modules.add(module1);
		deferred_modules.add(module2);

		DefferalDaoSupportObj.addDeferredModules(102, deferred_modules);
		deferred_modules_after=DefferalDaoSupportObj.getDeferredModules(102);

		assertEquals(3,deferred_modules_after.size());
		
	}
	/**
	 * get defered modules based on id of deferral 102
	 * @param deferral
	 * output should be arraylist size 1 
	 */
	@Test
	@DatabaseSetup(value="classpath:peterDataBaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testGetDeferredModules()
	{
			ArrayList<Module> deferred_modules_after = new ArrayList<Module>();

			deferred_modules_after=DefferalDaoSupportObj.getDeferredModules(102);

			assertEquals(1,deferred_modules_after.size());
	}
	/**
	 * get deferred modules based on name  of student
	 * @param firstName
	 * @param lastName
	 * output 2 as there are 2 modules deffered in relation to this student name
	 */
	@Test
	@DatabaseSetup(value="classpath:peterDataBaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testGetDeferredModulesName()
	{
		StudentImpl student = (StudentImpl) autoWireContext.getBean("student_peter");
		ArrayList<Module> deferred_modules_after = new ArrayList<Module>();

		deferred_modules_after=DefferalDaoSupportObj.getDeferredModulesName(student.getFirstName(), student.getLastName());

		assertEquals(2,deferred_modules_after.size());
		
	}
	/**
	 * get defered modules based on student number
	 * @param studentNumber
	 * output 2 as the same student is used
	 */
	@Test
	@DatabaseSetup(value="classpath:peterDataBaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testGetDeferredModuleStudentNumber()
	{
		StudentImpl student = (StudentImpl) autoWireContext.getBean("student_peter");
		ArrayList<Module> deferred_modules_after = new ArrayList<Module>();

		deferred_modules_after=DefferalDaoSupportObj.getDeferredModuleStudentNumber(student.getStudentNumber());

		assertEquals(2,deferred_modules_after.size());
	}
	/**
	 * get deferal based on student number 
	 * @param studentNumber
	 * output 1 as there is only 1 deferal inrelation to theis student
	 */
	@Test
	@DatabaseSetup(value="classpath:peterDataBaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testGetDeferralsStudentNumber()
	{
		ArrayList<Deferral> deferrals = new ArrayList<Deferral>();

		StudentImpl student = (StudentImpl) autoWireContext.getBean("student_peter");

		deferrals =DefferalDaoSupportObj.getDeferralsStudentNumber(student.getStudentNumber());

		assertEquals(1, deferrals.size());
	}
	/**
	 * get a list of deferrals by student name
	 * @param firstName
	 * @param lastName
	 * output1
	 */
	@Test
	@DatabaseSetup(value="classpath:peterDataBaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testGetDeferralsStudentName()
	{
		ArrayList<Deferral> deferrals = new ArrayList<Deferral>();

		StudentImpl student = (StudentImpl) autoWireContext.getBean("student_peter");

		deferrals =DefferalDaoSupportObj.getDeferralsStudentName(student.getFirstName(),student.getLastName());
		assertEquals(1, deferrals.size());
	}
	/**
	 * Returns a list of all deferrals based on status id
	 * @param status
	 * returns list deferrals with downloaded as leturer as status
	 * currently 4
	 */
	@Test
	@DatabaseSetup(value="classpath:peterDataBaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testGetDefferalsStatus()
	{
		ArrayList<Deferral> deferrals = new ArrayList<Deferral>();

		DeferralStatus status =DeferralStatus.DOWNLOADED_BY_LECTURER;
		deferrals =DefferalDaoSupportObj.getDefferalsStatus(status.getStatus());
		
		assertEquals(4, deferrals.size());
	}
	/**
	 * returns list of deferrals based on name of status
	 * 
	 */
	@Test
	@DatabaseSetup(value="classpath:peterDataBaseEntries.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void testGetDefferalsStatusName()
	{
		ArrayList<Deferral> deferrals = new ArrayList<Deferral>();
		deferrals =DefferalDaoSupportObj.getDefferalsStatusName("DOWNLOADED_BY_LECTURER");
		
		assertEquals(4, deferrals.size());
	}
}
