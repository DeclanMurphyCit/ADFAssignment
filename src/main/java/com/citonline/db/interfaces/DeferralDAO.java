package com.citonline.db.interfaces;

import java.util.ArrayList;
import java.util.Calendar;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import com.citonline.domain.Module;

@Service
public interface DeferralDAO 
{
	public void setDataSource(DataSource dataSource);
	
	public void createDeferral(Calendar date, int id_student, int id_program);
	
	public void deleteDeferral(int id_defferal);
	
	public void updateDeferal(int id_defferal, int id_student, int id_program);
	
	public void addDeferredModules(ArrayList<Module> defered);
	
	public ArrayList<Module> getDeferredModules(int deferral);

}
