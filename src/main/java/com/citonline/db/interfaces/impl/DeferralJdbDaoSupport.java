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
import org.springframework.transaction.support.TransactionTemplate;

import com.citonline.db.interfaces.DeferralDAO;
import com.citonline.domain.Deferral;
import com.citonline.domain.Lecturer;
import com.citonline.domain.Module;
/**
 * 
 * @author peter halligan
 * date revised 013/11/14
 * 
 *  implemenation of the deferral dao interface. 
 *
 */
@Repository
public class DeferralJdbDaoSupport extends JdbcDaoSupport implements DeferralDAO 
{
	@Autowired
    private DataSource dataSource;

	@Autowired
	private TransactionTemplate transactionTemplate;
	
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
		String SQL = "UPDATE deferral SET id_deferral_status=3";
		getJdbcTemplate().update(SQL, new Object[] {id_deferral});
		System.out.println("Deleted Record with ID = " + id_deferral );
		return;

	}

	@Override
	public void updateDeferralStatus(int status, String studentNumber) {
		
		String SQL = "UPDATE deferral SET status=? where id_student in (SELECT id_student from Student where studentNumber =?)";
		getJdbcTemplate().update(SQL, new Object[]{status, studentNumber});
		
		System.out.println("update Defferal status " + status + " student :" + studentNumber);

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
	public ArrayList<Module> getDeferredModules(int deferral) 
	{
		String SQL = "select * from Modules where id_module in(Select id_module from deferred_modules where id_deferral= ?";
		ArrayList<Module> modules = (ArrayList<Module>) getJdbcTemplate().query(SQL, 
				new Object[]{deferral},new ModuleMapper());
		return modules;

	}
	
	public ArrayList<Module> getDeferredModulesName(String firstName, String lastName) 
	{
		String SQL ="select module.id_module, code, crn, name, semester from module"
				+ " left join deferred_modules "
				+ " on  module.id_module=deferred_modules.id_module " 
				+ " join deferral "
				+ " on deferred_modules.id_deferral=deferral.id_deferral "
				+ " join student "
				+ " on deferral.id_student=student.id_student"
				+ " and student.firstName = ?"
				+ " and student.lastName=?";
		ArrayList<Module> modules = (ArrayList<Module>) getJdbcTemplate().query(SQL, 
				new Object[]{firstName, lastName},new ModuleMapper());
		return modules;
	}
	
	@Override
	public ArrayList<Deferral> getDefferalsStatus(int status) {
		String SQL = "select * from Deferral where id_status in(select id_status from deferred_status where status = ?";
		ArrayList<Deferral> deferrals = (ArrayList<Deferral>) getJdbcTemplate().query(SQL, 
				new Object[]{status},new DeferralMapper());
		return deferrals;
	}
	@Override
	public void updateDeferralStatus(int status, String firstName,
			String lastName) {
		String SQL = "UPDATE deferral SET status=? where id_student in (SELECT id_student from Student where firstName =? and secondName =?)";
		getJdbcTemplate().update(SQL, new Object[]{status, firstName, lastName});
		
		System.out.println("update Defferal status " + status + " student :" + firstName + " " + lastName) ;
		
	}
	@Override
	public ArrayList<Deferral> getDeferralsStudentNumber(
			String studentNumber) {
		String SQL = "select * from Deferral where id_student in(select id_student from student where studentNumber = ?";
		ArrayList<Deferral> deferrals = (ArrayList<Deferral>) getJdbcTemplate().query(SQL, 
				new Object[]{studentNumber},new DeferralMapper());
		return deferrals;
	}
	@Override
	public ArrayList<Deferral> getDeferralsStudentName(String firstName,
			String lastName) {
		String SQL = "select * from Deferral where id_student in(select id_student from student where firstName = ? and lastName =?";
		ArrayList<Deferral> deferrals = (ArrayList<Deferral>) getJdbcTemplate().query(SQL, 
				new Object[]{firstName, lastName},new DeferralMapper());
		return deferrals;
	}
	@Override
	public ArrayList<Module> getDeferredModuleStudentNumber(String studentNumber) {
		String SQL ="select module.id_module, code, crn, name, semester from module"
				+ " left join deferred_modules "
				+ " on  module.id_module=deferred_modules.id_module " 
				+ " join deferral "
				+ " on deferred_modules.id_deferral=deferral.id_deferral "
				+ " join student "
				+ " on deferral.id_student=student.id_student"
				+ " and student.studentNumber=?";
		ArrayList<Module> modules = (ArrayList<Module>) getJdbcTemplate().query(SQL, 
				new Object[]{studentNumber},new ModuleMapper());
		return modules;
	}
	@Override
	public int countRows() {
		String SQL = "select count(id_deferral) from deferral";
		int rows=getJdbcTemplate().queryForObject(SQL, Integer.class);
		return rows; 
	}

}
