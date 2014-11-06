package com.cit.online.db.interfaces.impl;

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

import com.citonline.db.interfaces.DeferralDAO;
import com.citonline.domain.Lecturer;
import com.citonline.domain.Module;

public class DeferralJdbcTemplate extends JdbcDaoSupport implements DeferralDAO 
{
	@Autowired
    private DataSource dataSource;

    @PostConstruct
    private void initialize() {

           setDataSource(dataSource);

    }

	@Override
	public void createDeferral(Calendar date, int id_student, int id_program) {
		String SQL = "INSERT INTO  defferal(date, id_student, id_program)"
				+ "VALUES(?, ?, ?, ?, ?)";
		
		getJdbcTemplate().update(SQL, new Object[] { date, id_student, id_program});
		
		System.out.println("Created DefferalObj Name = " + date + " id: " + id_student +
				"\nemail = " + id_program);

	}

	@Override
	public void deleteDeferral(int id_deferral) {
		String SQL = "delete from deferral where id_deferral = ?";
		getJdbcTemplate().update(SQL, new Object[] {id_deferral});
		System.out.println("Deleted Record with ID = " + id_deferral );
		return;

	}

	@Override
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
	public void addDeferredModules(int id_deferral, final ArrayList<Module> deferred) 
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

}
