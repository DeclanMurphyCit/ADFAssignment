package com.citonline.db.interfaces.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.citonline.db.interfaces.DeferralDAO;
import com.citonline.domain.Deferral;
import com.citonline.domain.Lecturer;
import com.citonline.domain.Module;

@Repository
public class DeferralJdbDaoSupport extends JdbcDaoSupport implements DeferralDAO 
{
	@Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() 
    {

           setDataSource(dataSource);

    }
	public void createDeferral(Calendar date, int id_program, int id_student, boolean program_deferred, int status) 
	{
		String SQL = "INSERT INTO  defferal(deferral_date, id_program, id_student, program_deferred, id_deferral_status)"
				+ "VALUES(?, ?, ?, ?, ?)";
		
		getJdbcTemplate().update(SQL, new Object[] { date, id_program, id_student, program_deferred, status});
		
		System.out.println("Created DefferalObj date = " + date + "\nid: " + id_student +
				"\nprogid: " + id_program + program_deferred + "\ndefferalstatus: "+ status);

	}

	@Override
	public void deleteDeferral(int id_deferral) {
		String SQL = "delete from deferral where id_deferral = ?";
		getJdbcTemplate().update(SQL, new Object[] {id_deferral});
		System.out.println("Deleted Record with ID = " + id_deferral );
		return;

	}

	//@Override
	public void updateDeferal(int status, String studentNumber) {
		
		String SQL = "UPDATE deferral SET status=? where id_student in (SELECT id_student from Student where studentNumber =?)";
		getJdbcTemplate().update(SQL, new Object[]{status, studentNumber});
		
		System.out.println("update Defferal status " + status + " student :" + studentNumber);

	}
public void updateDeferal(int status, String firstName, String lastName) {
		
		String SQL = "UPDATE deferral SET status=? where id_student in (SELECT id_student from Student where firstName =? and secondName =?)";
		getJdbcTemplate().update(SQL, new Object[]{status, firstName, lastName});
		
		System.out.println("update Defferal status " + status + " student :" + firstName + " " + lastName) ;

	}
	@Override
	public void addDeferredModules(final int id_deferral, final ArrayList<Module> deferred) 
	{
			String SQL = "insert into deferredModules (id_defferal, id_module) values (?, ?)";
			getJdbcTemplate().batchUpdate(SQL, new BatchPreparedStatementSetter() 
			{

				public int getBatchSize() {
					return deferred.size();
				}

				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException
				{
					Module mod = deferred.get(i);
					ps.setInt(1, id_deferral );
					ps.setInt(2, mod.getId());
				}			
			});
			
		}

	@Override
	public ArrayList<Module> getDeferredModules(int deferral) {
		String SQL = "select * from deferredModules where deferralId";
		ArrayList<Module> modules = (ArrayList<Module>) getJdbcTemplate().query(SQL, 
						new ModuleMapper());
		return modules;

	}

	@Override
	public ArrayList<Deferral> getDeferralsStudentName(String firstName,
			String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Deferral> getDefferalsStatus(int status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateDeferalStatus(int id_deferral, String firstName,
			String lastName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateDeferalStatus(int status, String studentNumber) {
		// TODO Auto-generated method stub
		
	}

}
