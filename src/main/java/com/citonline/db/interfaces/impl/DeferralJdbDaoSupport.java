package com.citonline.db.interfaces.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import com.citonline.db.interfaces.DeferralDAO;
import com.citonline.domain.Deferral;
import com.citonline.domain.Module;

/**
 * 
 * @author peter halligan date revised 013/11/14
 * 
 * implemenation of the deferral dao interface.
 *
 */
@Repository
public class DeferralJdbDaoSupport extends JdbcDaoSupport implements
		DeferralDAO {
	@Autowired
	private DataSource dataSource;

	@Autowired
	private TransactionTemplate transactionTemplate;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}

	@Autowired
	public void setTxTemplate(TransactionTemplate txTemplate) {
		this.transactionTemplate = txTemplate;
	}

	@Override
	@Transactional
	public void createDeferral(String date, int id_program, int id_student,
			boolean program_deferred, int status) {
		String SQL = "INSERT INTO  deferral(deferral_date, id_program, id_student, program_deferred, id_deferral_status)"
				+ "VALUES(?, ?, ?, ?, ?)";

		getJdbcTemplate().update(
				SQL,
				new Object[] { date, id_program, id_student, program_deferred,
						status });

		System.out.println("Created DefferalObj date = " + date + "\nid: "
				+ id_student + "\nprogid: " + id_program + program_deferred
				+ "\ndefferalstatus: " + status);

	}

	@Override
	@Transactional
	public void deleteDeferral(int id_deferral) {
		int delete = 3;
		String SQL = "UPDATE deferral SET id_deferral_status= ? where id_deferral=?";
		getJdbcTemplate().update(SQL, new Object[] { delete, id_deferral });
		System.out.println("Deleted Record with ID = " + id_deferral);
		return;

	}

	@Override
	@Transactional
	public void updateDeferralStatus(int status, String studentNumber) {

		String SQL = "UPDATE deferral SET id_deferral_status=? where id_student in (SELECT id_student from student where studentNumber =?)";
		getJdbcTemplate().update(SQL, new Object[] { status, studentNumber });

		System.out.println("update Defferal status " + status + " student :"
				+ studentNumber);

	}

	@Override
	@Transactional
	public void addDeferredModules(int id_deferral,
			final ArrayList<Module> deferred) {
		String SQL = "insert into deferred_modules (id_deferral, id_module) values (?, ?)";
		getJdbcTemplate().batchUpdate(SQL, new BatchPreparedStatementSetter() {

			public int getBatchSize() {
				return deferred.size();
			}

			@Override
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {
				Module mod = deferred.get(i);
				ps.setInt(1, id_deferral);
				ps.setInt(2, mod.getId());
			}
		});

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public ArrayList<Module> getDeferredModules(int deferral) {
		String SQL = "select * from module where id_module in(Select id_module from deferred_modules where id_deferral= ?)";
		@SuppressWarnings("unchecked")
		ArrayList<Module> modules = (ArrayList<Module>) getJdbcTemplate()
				.query(SQL, new Object[] { deferral }, new ModuleMapper());
		return modules;

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public ArrayList<Module> getDeferredModulesName(String firstName,
			String lastName) {
		String SQL = "select module.id_module, code, crn, name, semester from module"
				+ " left join deferred_modules "
				+ " on  module.id_module=deferred_modules.id_module "
				+ " join deferral "
				+ " on deferred_modules.id_deferral=deferral.id_deferral "
				+ " join student "
				+ " on deferral.id_student=student.id_student"
				+ " and student.firstName = ?" + " and student.lastName=?";
		@SuppressWarnings("unchecked")
		ArrayList<Module> modules = (ArrayList<Module>) getJdbcTemplate()
				.query(SQL, new Object[] { firstName, lastName },
						new ModuleMapper());
		return modules;
	}

	@Override
	@Transactional
	public void updateDeferralStatus(int status, String firstName,
			String lastName) {
		String SQL = "UPDATE deferral SET id_deferral_status=? where id_student in (SELECT id_student from student where firstName =? and lastName =?)";
		getJdbcTemplate().update(SQL,
				new Object[] { status, firstName, lastName });

		System.out.println("update Defferal status " + status + " student :"
				+ firstName + " " + lastName);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public ArrayList<Deferral> getDeferralsStudentNumber(String studentNumber) {
		String SQL = "select * from deferral where id_student in(select id_student from student where studentNumber = ?)";
		@SuppressWarnings("unchecked")
		ArrayList<Deferral> deferrals = (ArrayList<Deferral>) getJdbcTemplate()
				.query(SQL, new Object[] { studentNumber },
						new DeferralMapper());
		return deferrals;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public ArrayList<Deferral> getDeferralsStudentName(String firstName,
			String lastName) {
		String SQL = "select * from deferral where id_student in(select id_student from student where firstName = ? and lastName =?)";
		@SuppressWarnings("unchecked")
		ArrayList<Deferral> deferrals = (ArrayList<Deferral>) getJdbcTemplate()
				.query(SQL, new Object[] { firstName, lastName },
						new DeferralMapper());
		return deferrals;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public ArrayList<Module> getDeferredModuleStudentNumber(String studentNumber) {
		String SQL = "select module.id_module, code, crn, name, semester from module"
				+ " left join deferred_modules "
				+ " on  module.id_module=deferred_modules.id_module "
				+ " join deferral "
				+ " on deferred_modules.id_deferral=deferral.id_deferral "
				+ " join student "
				+ " on deferral.id_student=student.id_student"
				+ " and student.studentNumber=?";
		@SuppressWarnings("unchecked")
		ArrayList<Module> modules = (ArrayList<Module>) getJdbcTemplate()
				.query(SQL, new Object[] { studentNumber }, new ModuleMapper());
		return modules;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public int countRows() {
		String SQL = "select count(id_deferral) from deferral";
		int rows = getJdbcTemplate().queryForObject(SQL, Integer.class);
		return rows;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Deferral getDeferralStudentName(String firstName, String lastName) {
		String SQL = "select * from deferral where id_student in(select id_student from student where firstName = ? and lastName =?)";
		@SuppressWarnings("unchecked")
		Deferral deferral = (Deferral) getJdbcTemplate().queryForObject(SQL,
				new Object[] { firstName, lastName }, new DeferralMapper());
		return deferral;

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public Deferral getDeferralStudentNumber(String studentNumber) {
		String SQL = "select * from deferral where id_student in(select id_student from student where studentNumber = ?)";
		@SuppressWarnings("unchecked")
		Deferral deferral = (Deferral) getJdbcTemplate().queryForObject(SQL,
				new Object[] { studentNumber }, new DeferralMapper());
		return deferral;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public ArrayList<Deferral> getDefferalsStatus(int status) {
		String SQL = "select * from deferral where id_deferral_status =?";
		@SuppressWarnings("unchecked")
		ArrayList<Deferral> deferrals = (ArrayList<Deferral>) getJdbcTemplate()
				.query(SQL, new Object[] { status }, new DeferralMapper());
		return deferrals;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public ArrayList<Deferral> getDefferalsStatusName(String status) {
		String SQL = "select * from deferral where id_deferral_status in(select id_deferral_status from deferral_status_types where deferral_status_name = ?)";
		@SuppressWarnings("unchecked")
		ArrayList<Deferral> deferrals = (ArrayList<Deferral>) getJdbcTemplate()
				.query(SQL, new Object[] { status }, new DeferralMapper());
		return deferrals;
	}

}
