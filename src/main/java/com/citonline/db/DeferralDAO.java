package com.citonline.db;

import java.util.Calendar;

import javax.sql.DataSource;

public interface DeferralDAO 
{
	public void setDataSource(DataSource dataSource);
	
	public void createDeferral(Calendar date, int id_student, int id_program);
	
	public void deleteDeferral(int id_defferal);
	
	public void updateDeferal(int id_defferal, int id_student, int id_program);
	
	

}
