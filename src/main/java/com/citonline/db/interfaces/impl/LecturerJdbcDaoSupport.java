/**
 * @author Fabien
 *
 * @since 5 nov. 2014
 */
package com.citonline.db.interfaces.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import com.citonline.db.interfaces.LecturerDAO;
import com.citonline.domain.Module;
import com.citonline.domain.Program;
import com.citonline.interfaces.impl.LecturerImpl;

/**
 * @author Fabien
 *
 */
@Repository
public class LecturerJdbcDaoSupport extends JdbcDaoSupport implements LecturerDAO {

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
	public void createLecturer(final String firstName, final  String lastName, final String email,
			final String phoneNumber, final  String roomNumber) {
		
		String SQL = "INSERT INTO lecturer (firstName, lastName, email, phoneNumber, roomNumber)"
				+ "VALUES(?, ?, ?, ?, ?)";
		
		int added = getJdbcTemplate().update(SQL, new Object[] { firstName, lastName, email,
				phoneNumber, roomNumber});
		if(added == 1){
			System.out.println("Created lecturer Name = " + firstName + " " + lastName +
				"\nemail = " + email + ", phoneNumber =" + phoneNumber + ", roomNumber = " + roomNumber);
		}
		else{
			System.err.println("Error creating lecturer Name = " + firstName + " " + lastName +
				"\nemail = " + email + ", phoneNumber =" + phoneNumber + ", roomNumber = " + roomNumber);
		}
	}

	@Override
	@Transactional
	public void createLecturer(String firstName, String lastName, String email,
			String phoneNumber, String roomNumber, Integer idManagedProgram) {

		String SQL = "INSERT INTO lecturer (firstName, lastName, email, phoneNumber, roomNumber, idManagedProgram)"
				+ "VALUES(?, ?, ?, ?, ?, ?)";
		
		int added = getJdbcTemplate().update(SQL, new Object[] { firstName, lastName, email,
				phoneNumber, roomNumber, idManagedProgram});
		
		if(added == 1){
			System.out.println("Created lecturer Name = " + firstName + " " + lastName +
				"\nemail = " + email + ", phoneNumber =" + phoneNumber +
				", roomNumber = " + roomNumber + ", idManagedProgram = " + idManagedProgram);
		}
		else{
			System.err.println("Error creating lecturer Name = " + firstName + " " + lastName +
				"\nemail = " + email + ", phoneNumber =" + phoneNumber +
				", roomNumber = " + roomNumber + ", idManagedProgram = " + idManagedProgram);
		}

	}	

	@Override
	@Transactional
	public void deleteLecturer(Integer id_lecturer) {
		String SQL = "delete from lecturer where id_lecturer = ?";
		
		int deleted = getJdbcTemplate().update(SQL, new Object[] {id_lecturer});
		
		if(deleted == 1)
			System.out.println("Deleted lecturer with ID = " + id_lecturer );
		else
			System.err.println("Error deleting lecturer with ID = " + id_lecturer );
	}

	@Override
	@Transactional
	public void deleteLecturer(String firstName, String lastName) {
		String SQL = "delete from lecturer where firstName = ? and lastName = ?";
		
		int deleted = getJdbcTemplate().update(SQL, new Object[] {firstName, lastName});
		
		if(deleted == 1)
			System.out.println("Deleted lecturer with Name = " + firstName + " " + lastName );
		else
			System.err.println("Error deleting lecturer with Name = " + firstName + " " + lastName );
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public LecturerImpl getLecturer(Integer id_lecturer) {
		String SQL = "select * from lecturer where id_lecturer = ?";
		LecturerImpl lecturer = (LecturerImpl) getJdbcTemplate().queryForObject(SQL, 
						new Object[]{id_lecturer}, new LecturerMapper());
		
		ArrayList<Module> taughtModules = getTaughtModules(id_lecturer);
		lecturer.setModulesTaught(taughtModules);
		
		Program prog = getManagedProgram(id_lecturer);
		lecturer.setManagedProgram(prog);
		
		return lecturer;
	}
	

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public LecturerImpl getLecturer(String firstName, String lastName) {
		String SQL = "select * from lecturer where firstName =? and lastName =?";
		LecturerImpl lecturer = (LecturerImpl) getJdbcTemplate().queryForObject(SQL, 
						new Object[]{firstName, lastName}, new LecturerMapper());
		
		ArrayList<Module> taughtModules = getTaughtModules(firstName, lastName);
		lecturer.setModulesTaught(taughtModules);
		
		Program prog = getManagedProgram(firstName, lastName);
		lecturer.setManagedProgram(prog);
		
		return lecturer;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public List<LecturerImpl> listLecturers() {
		String SQL = "select * from lecturer";
		List<LecturerImpl> lecturers = getJdbcTemplate().query(SQL, 
						new LecturerMapper());
		return lecturers;
	}

	@Override
	@Transactional
	public void updateLecturerEmail(String firstName, String lastName,
			String email) {
		String SQL = "UPDATE lecturer SET email=? where firstName = ? and lastName = ?";
		getJdbcTemplate().update(SQL, new Object[]{email, firstName, lastName});
		
		System.out.println("update lecturer " + firstName + " " + lastName +
				"'s email. Set to: " + email);
	}

	@Override
	@Transactional
	public void updateLecturerEmail(Integer id_lecturer, String email) {
		String SQL = "UPDATE lecturer SET email=? where id_lecturer = ?";
		getJdbcTemplate().update(SQL, new Object[]{email, id_lecturer});
		
		System.out.println("update lecturer " + id_lecturer + "'s email. Set to: " + email);
	}

	@Override
	@Transactional
	public void updateLecturerRoomNumber(String firstName, String lastName,
			String roomNumber) {
		String SQL = "UPDATE lecturer SET roomNumber=? where firstName = ? and lastName = ?";
		getJdbcTemplate().update(SQL, new Object[]{roomNumber, firstName, lastName});
		
		System.out.println("update lecturer " + firstName + " " + lastName +
				"'s roomNumber. Set to: " + roomNumber);
	}

	@Override
	@Transactional
	public void updateLecturerRoomNumber(Integer id_lecturer, String roomNumber) {
		String SQL = "UPDATE lecturer SET roomNumber=? where id_lecturer = ?";
		getJdbcTemplate().update(SQL, new Object[]{roomNumber, id_lecturer});
		
		System.out.println("update lecturer " + id_lecturer + "'s roomNumber. Set to: " + roomNumber);
	}

	@Override
	@Transactional
	public void updateLecturerManagedProgram(String firstName, String lastName,
		Integer idManagedProgram) {
	String SQL = "UPDATE lecturer SET idManagedProgram=? where firstName = ? and lastName = ?";
	getJdbcTemplate().update(SQL, new Object[]{idManagedProgram, firstName, lastName});
	
	System.out.println("update lecturer " + firstName + " " + lastName +
			"'s idManagedProgram. Set to: " + idManagedProgram);
	}

	@Override
	@Transactional
	public void updateLecturerManagedProgram(Integer id_lecturer, Integer idManagedProgram) {
		String SQL = "UPDATE lecturer SET idManagedProgram=? where id_lecturer = ?";
	getJdbcTemplate().update(SQL, new Object[]{idManagedProgram, id_lecturer});
	
	System.out.println("update lecturer " + id_lecturer + "'s idManagedProgram. Set to: " + idManagedProgram);
	}

	@Override
	@Transactional
	public void addTaughtModule(String firstName, String lastName,
				Integer idModule) {
		
		String SQL = "select id_lecturer from lecturer where firstName =? and lastName =?";
		final int id_lecturer=getJdbcTemplate().queryForObject(SQL,
				new Object[]{firstName, lastName}, Integer.class);

		String SQL2 = "INSERT INTO lecturer_teaches_module(idLecturer, idModule) " +
			"VALUES (?, ?)";
		getJdbcTemplate().update(SQL2, new Object[]{id_lecturer, idModule});
		
		System.out.println("update lecturer " + id_lecturer + "'s taught modules: add " + idModule);
		
	}

	@Override
	@Transactional
	public void addTaughtModule(Integer id_lecturer, Integer idModule) {
		String SQL = "INSERT INTO lecturer_teaches_module(idLecturer, idModule)" +
				"VALUES (?, ?)";
		getJdbcTemplate().update(SQL, new Object[]{id_lecturer, idModule});
		
		System.out.println("update lecturer " + id_lecturer + "'s taught modules: add " + idModule);
	}

	@Override
	@Transactional
	public void addTaughtModule(String firstName, String lastName,
			final List<Integer> idModuleList) {
		String SQL = "select id_lecturer from lecturer where firstName =? and lastName =?";
		final int id_lecturer=getJdbcTemplate().queryForObject(SQL,
				new Object[]{firstName, lastName}, Integer.class);
		
		String SQL2 = "INSERT INTO lecturer_teaches_module(idLecturer, idModule)" +
				"VALUES (?, ?)";
		System.out.println("update lecturer " + id_lecturer + "'s taught modules: add ");
		getJdbcTemplate().batchUpdate(SQL2, new BatchPreparedStatementSetter() {

			public int getBatchSize() {
				return idModuleList.size();
			}
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Integer idModule = idModuleList.get(i);
				ps.setInt(1, id_lecturer);
				ps.setInt(2, idModule);
				System.out.print(idModule + ", ");
			}		
		});
	}

	@Override
	@Transactional
	public void addTaughtModule(final Integer id_lecturer, final List<Integer> idModuleList) {
		String SQL = "INSERT INTO lecturer_teaches_module(idLecturer, idModule)" +
				"VALUES (?, ?)";
		
		System.out.println("update lecturer " + id_lecturer + "'s taught modules: add ");
		getJdbcTemplate().batchUpdate(SQL, new BatchPreparedStatementSetter() {

			public int getBatchSize() {
				return idModuleList.size();
			}
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Integer idModule = idModuleList.get(i);
				ps.setInt(1, id_lecturer);
				ps.setInt(2, idModule);
				System.out.print(idModule + ", ");
			}		
		});
	}

	@Override
	@Transactional
	public void removeTaughtModule(String firstName, String lastName,
			Integer idModule) {
		String SQL= "select id_lecturer from lecturer where firstName =? and lastName =?";
		final int id_lecturer=getJdbcTemplate().queryForObject(SQL,
				new Object[]{firstName, lastName}, Integer.class);
		
		String SQL2 = "DELETE from lecturer_teaches_module WHERE idLecturer= ? AND idModule= ?";
		getJdbcTemplate().update(SQL2, new Object[]{id_lecturer, idModule});
		
		System.out.println("update lecturer " + id_lecturer + "'s taught modules: remove " + idModule);
		
	}

	@Override
	@Transactional
	public void removeTaughtModule(Integer id_lecturer, Integer idModule) {
		String SQL = "DELETE from lecturer_teaches_module WHERE idLecturer= ? AND idModule= ?";
		getJdbcTemplate().update(SQL, new Object[]{id_lecturer, idModule});
		
		System.out.println("update lecturer " + id_lecturer + "'s taught modules: remove " + idModule);
	}

	@Override
	@Transactional
	public void removeTaughtModule(final String firstName, final String lastName,
			final List<Integer> idModuleList) {

		String SQL= "select id_lecturer from lecturer where firstName =? and lastName =?";
		final int id_lecturer=getJdbcTemplate().queryForObject(SQL,
				new Object[]{firstName, lastName}, Integer.class);
		
		String SQL2 = "DELETE FROM lecturer_teaches_module WHERE idLecturer = ? " +
			"AND idModule = ?";
		
		System.out.println("update lecturer " + id_lecturer + "'s taught modules: removing ");
		getJdbcTemplate().batchUpdate(SQL2, new BatchPreparedStatementSetter() {

			public int getBatchSize() {
				return idModuleList.size();
			}
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Integer idModule = idModuleList.get(i);
				ps.setInt(1, id_lecturer);
				ps.setInt(2, idModule);
				System.out.print(idModule + ", ");
			}		
		});
	}

	@Override
	@Transactional
	public void removeTaughtModule(final Integer id_lecturer, final List<Integer> idModuleList) {
		String SQL = "DELETE FROM lecturer_teaches_module WHERE idLecturer = ? " +
				"AND idModule = ?";
			
		System.out.println("update lecturer " + id_lecturer + "'s taught modules: remove ");
		getJdbcTemplate().batchUpdate(SQL, new BatchPreparedStatementSetter() {

			public int getBatchSize() {
				return idModuleList.size();
			}
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Integer idModule = idModuleList.get(i);
				ps.setInt(1, id_lecturer);
				ps.setInt(2, idModule);
				System.out.print(idModule + ", ");
			}		
		});
	}

	/* (non-Javadoc)
	 * @see com.citonline.db.interfaces.LecturerDAO#getTaughtModules(java.lang.Integer)
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public ArrayList<Module> getTaughtModules(Integer id_lecturer) {
		ArrayList<Module> taughtModules;
		
		String SQL="SELECT * from module"
				+ " JOIN lecturer_teaches_module on "
				+ " lecturer_teaches_module.idModule = module.id_module"
				+ " AND lecturer_teaches_module.idLecturer= ?";
		
		taughtModules = (ArrayList<Module>) getJdbcTemplate().query(SQL,
				new Object[] {id_lecturer}, new ModuleMapper());
		
		return taughtModules;
	}

	/* (non-Javadoc)
	 * @see com.citonline.db.interfaces.LecturerDAO#getTaughtModules(java.lang.String, java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public ArrayList<Module> getTaughtModules(String firstName, String lastName) {
		ArrayList<Module> taughtModules;
		
		String SQL="SELECT * from module"
				+ " JOIN lecturer_teaches_module on "
				+ " lecturer_teaches_module.idModule = module.id_module"
				+ " JOIN lecturer on "
				+ " lecturer_teaches_module.idLecturer = lecturer.id_lecturer"
				+ " AND lecturer.firstName= ? AND lecturer.lastName = ?";
		
		taughtModules = (ArrayList<Module>) getJdbcTemplate().query(SQL,
				new Object[] {firstName, lastName}, new ModuleMapper());
		
		return taughtModules;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public int countRows() {
		String SQL = "select count(id_lecturer) from lecturer";
		int rows=getJdbcTemplate().queryForObject(SQL, Integer.class);
		return rows;
	}

	/* (non-Javadoc)
	 * @see com.citonline.db.interfaces.LecturerDAO#getManagedProgram(java.lang.String, java.lang.String)
	 */
	@Override
	public Program getManagedProgram(String firstName, String lastName) {
		Program prog =null;
		
		String SQL = "SELECT * FROM program "
				+ "JOIN lecturer ON "
				+ "lecturer.idManagedProgram = program.id_program "
				+ "AND lecturer.firstName = ? AND lecturer.lastName = ?";
		try{
			prog = (Program) getJdbcTemplate().queryForObject(SQL,
					new Object[] {firstName, lastName}, new ProgramMapper());
		}
		catch(Exception e){}
		
		return prog;
	}

	/* (non-Javadoc)
	 * @see com.citonline.db.interfaces.LecturerDAO#getManagedProgram(java.lang.Integer)
	 */
	@Override
	public Program getManagedProgram(Integer id_lecturer) {
		Program prog = null;
		
		String SQL = "SELECT * FROM program "
				+ "JOIN lecturer ON "
				+ "lecturer.idManagedProgram = program.id_program "
				+ "AND lecturer.id_lecturer = ?";
		
		try{
			prog = (Program) getJdbcTemplate().queryForObject(SQL,
					new Object[] {id_lecturer}, new ProgramMapper());
		}
		catch(Exception e){}
		
		return prog;
	}

}
