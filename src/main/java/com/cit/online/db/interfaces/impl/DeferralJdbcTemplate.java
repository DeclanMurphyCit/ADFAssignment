package com.cit.online.db.interfaces.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.sql.DataSource;
//
import org.springframework.jdbc.core.JdbcTemplate;

import com.citonline.db.interfaces.DeferralDAO;
import com.citonline.domain.Module;

public class DeferralJdbcTemplate implements DeferralDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);

	}

	@Override
	public void createDeferral(Calendar date, int id_student, int id_program) {
		String SQL = "INSERT INTO  defferal(date, id_student, id_program)"
				+ "VALUES(?, ?, ?, ?, ?)";
		
		jdbcTemplateObject.update(SQL, new Object[] { date, id_student, id_program});
		
		System.out.println("Created DefferalObj Name = " + date + " id: " + id_student +
				"\nemail = " + id_program);

	}

	@Override
	public void deleteDeferral(int id_defferal) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateDeferal(int id_defferal, int id_student, int id_program) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addDeferredModules(ArrayList<Module> defered) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Module> getDeferredModules(int deferral) {
		return null;
		// TODO Auto-generated method stub

	}

}
