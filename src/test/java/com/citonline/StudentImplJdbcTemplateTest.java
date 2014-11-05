package com.citonline;

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
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:configuration.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DbUnitTestExecutionListener.class })

public class StudentImplJdbcTemplateTest {
	
	@Autowired
	ApplicationContext autoWireContext;
	@Autowired
	StudentJdbcDaoSupport studentJT;

	final Logger logger = Logger.getLogger(StudentImplJdbcTemplateTest.class);
	
	@Test
	@DatabaseSetup(value="classpath:songwriterData.xml", type=DatabaseOperation.CLEAN_INSERT)
	public void test() {
		fail("Not yet implemented");
	}
	
	

}