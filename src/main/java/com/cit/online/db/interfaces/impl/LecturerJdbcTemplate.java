/**
 * @author Fabien
 * 
 * @since 25 oct. 2014
 */
package com.cit.online.db.interfaces.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import com.citonline.db.interfaces.LecturerDAO;
import com.citonline.domain.Lecturer;
import com.citonline.domain.Module;
import com.citonline.interfaces.impl.LecturerImpl;

public class LecturerJdbcTemplate implements LecturerDAO {
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public void createLecturer(String firstName, String lastName, String email,
			String phoneNumber, String roomNumber) {
		
		String SQL = "INSERT INTO lecturer (firstName, lastName, email, phoneNumber, roomNumber)"
				+ "VALUES(?, ?, ?, ?, ?)";
		
		jdbcTemplateObject.update(SQL, new Object[] { firstName, lastName, email,
				phoneNumber, roomNumber});
		
		System.out.println("Created lecturer Name = " + firstName + " " + lastName +
				"\nemail = " + email + ", phoneNumber =" + phoneNumber + ", roomNumber = " + roomNumber);

	}

	@Override
	public void createLecturer(String firstName, String lastName, String email,
			String phoneNumber, String roomNumber, Integer idManagedProgram) {

		String SQL = "INSERT INTO lecturer (firstName, lastName, email, phoneNumber, roomNumber, idManagedProgram)"
				+ "VALUES(?, ?, ?, ?, ?, ?)";
		
		jdbcTemplateObject.update(SQL, new Object[] { firstName, lastName, email,
				phoneNumber, roomNumber, idManagedProgram});
		
		System.out.println("Created lecturer Name = " + firstName + " " + lastName +
				"\nemail = " + email+ ", phoneNumber =" + phoneNumber +
				", roomNumber = " + roomNumber + ", idManagedProgram = " + idManagedProgram);

	}	

	@Override
	public void deleteLecturer(Integer id_lecturer) {
		String SQL = "delete from lecturer where id_lecturer = ?";
		jdbcTemplateObject.update(SQL, new Object[] {id_lecturer});
		System.out.println("Deleted lecturer with ID = " + id_lecturer );
	}

	@Override
	public void deleteLecturer(String firstName, String lastName) {
		String SQL = "delete from lecturer where firstName = ? and lastName = ?";
		jdbcTemplateObject.update(SQL, new Object[] {firstName, lastName});
		System.out.println("Deleted lecturer with Name = " + firstName + " " + lastName );
	}

	@Override
	public LecturerImpl getLecturer(Integer id_lecturer) {
		String SQL = "select * from lecturer where id_lecturer = ?";
		LecturerImpl lecturer = (LecturerImpl) jdbcTemplateObject.queryForObject(SQL, 
						new Object[]{id_lecturer}, new LecturerMapper());
		
		ArrayList<Module> taughtModules = getTaughtModules(id_lecturer);
		lecturer.setModulesTaught(taughtModules);
		
		return lecturer;
	}
	

	@Override
	public LecturerImpl getLecturer(String firstName, String lastName) {
		String SQL = "select * from lecturer where firstName = ? and lastName = ? ";
		LecturerImpl lecturer = (LecturerImpl) jdbcTemplateObject.queryForObject(SQL, 
						new Object[]{firstName, lastName}, new LecturerMapper());
		
		ArrayList<Module> taughtModules = getTaughtModules(firstName, lastName);
		lecturer.setModulesTaught(taughtModules);
		
		return lecturer;
	}

	@Override
	public List<LecturerImpl> listLecturers() {
		String SQL = "select * from lecturer";
		List<LecturerImpl> lecturers = jdbcTemplateObject.query(SQL, 
						new LecturerMapper());
		return lecturers;
	}

	@Override
	public void updateLecturerEmail(String firstName, String lastName,
			String email) {
		String SQL = "UPDATE lecturer SET email=? where firstName = ? and lastName = ?";
		jdbcTemplateObject.update(SQL, new Object[]{email, firstName, lastName});
		
		System.out.println("update lecturer " + firstName + " " + lastName +
				"'s email. Set to: " + email);
	}

	@Override
	public void updateLecturerEmail(Integer id_lecturer, String email) {
		String SQL = "UPDATE lecturer SET email=? where id_lecturer = ?";
		jdbcTemplateObject.update(SQL, new Object[]{email, id_lecturer});
		
		System.out.println("update lecturer " + id_lecturer + "'s email. Set to: " + email);
	}

	@Override
	public void updateLecturerRoomNumber(String firstName, String lastName,
			String roomNumber) {
		String SQL = "UPDATE lecturer SET roomNumber=? where firstName = ? and lastName = ?";
		jdbcTemplateObject.update(SQL, new Object[]{roomNumber, firstName, lastName});
		
		System.out.println("update lecturer " + firstName + " " + lastName +
				"'s roomNumber. Set to: " + roomNumber);
	}

	@Override
	public void updateLecturerRoomNumber(Integer id_lecturer, String roomNumber) {
		String SQL = "UPDATE lecturer SET roomNumber=? where id_lecturer = ?";
		jdbcTemplateObject.update(SQL, new Object[]{roomNumber, id_lecturer});
		
		System.out.println("update lecturer " + id_lecturer + "'s roomNumber. Set to: " + roomNumber);
	}

	@Override
	public void updateLecturerManagedProgram(String firstName, String lastName,
		Integer idManagedProgram) {
	String SQL = "UPDATE lecturer SET managedProgram=? where firstName = ? and lastName = ?";
	jdbcTemplateObject.update(SQL, new Object[]{idManagedProgram, firstName, lastName});
	
	System.out.println("update lecturer " + firstName + " " + lastName +
			"'s managedProgram. Set to: " + idManagedProgram);
	}

	@Override
	public void updateLecturerManagedProgram(Integer id_lecturer, Integer idManagedProgram) {
		String SQL = "UPDATE lecturer SET managedProgram=? where id_lecturer = ?";
	jdbcTemplateObject.update(SQL, new Object[]{idManagedProgram, id_lecturer});
	
	System.out.println("update lecturer " + id_lecturer + "'s managedProgram. Set to: " + idManagedProgram);
	}

	@Override
	public void addTaughtModule(String firstName, String lastName,
				Integer idModule) {
		
		String SQL = "SELECT id_lecturer from lecturer WHERE firstName = ? AND nastName = ?";
		final int id_lecturer=jdbcTemplateObject.queryForObject(SQL, Integer.class);

		String SQL2 = "INSERT INTO  lecturer_teaches_module(id_lecturer, idModule)" +
			"VALUES managedProgram=? where id_lecturer = ?";
		jdbcTemplateObject.update(SQL2, new Object[]{id_lecturer, idModule});
		
		System.out.println("update lecturer " + id_lecturer + "'s taught modules: add " + idModule);
		
	}

	@Override
	public void addTaughtModule(Integer id_lecturer, Integer idModule) {
		String SQL = "INSERT INTO lecturer_teaches_module(id_lecturer, idModule)" +
				"VALUES (?, ?)";
		jdbcTemplateObject.update(SQL, new Object[]{id_lecturer, idModule});
		
		System.out.println("update lecturer " + id_lecturer + "'s taught modules: add " + idModule);
	}

	@Override
	public void addTaughtModule(String firstName, String lastName,
			final List<Integer> idModuleList) {
		String SQL = "SELECT id_lecturer from lecturer WHERE firstName = ? AND nastName = ?";
		final int id_lecturer=jdbcTemplateObject.queryForObject(SQL, Integer.class);
		
		String SQL2 = "INSERT INTO lecturer_teaches_module(id_lecturer, idModule)" +
				"VALUES (?, ?)";
		System.out.println("update lecturer " + id_lecturer + "'s taught modules: add ");
		jdbcTemplateObject.update(SQL2, new BatchPreparedStatementSetter() {

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
	public void addTaughtModule(final Integer id_lecturer, final List<Integer> idModuleList) {
		String SQL = "INSERT INTO lecturer_teaches_module(id_lecturer, idModule)" +
				"VALUES (?, ?)";
		
		System.out.println("update lecturer " + id_lecturer + "'s taught modules: add ");
		jdbcTemplateObject.update(SQL, new BatchPreparedStatementSetter() {

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
	public void removeTaughtModule(String firstName, String lastName,
			Integer idModule) {
		String SQL= "SELECT id_lecturer from lecturer WHERE firstName= ? AND lastName= ?";
		final int id_lecturer=jdbcTemplateObject.queryForObject(SQL, Integer.class);
		
		String SQL2 = "DELETE from lecturer_teaches_module WHERE id_lecturer= ?";
		jdbcTemplateObject.update(SQL2, new Object[]{id_lecturer, idModule});
		
		System.out.println("update lecturer " + id_lecturer + "'s taught modules: remove " + idModule);
		
	}

	@Override
	public void removeTaughtModule(Integer id_lecturer, Integer idModule) {
		String SQL = "DELETE from lecturer_teaches_module WHERE id_lecturer= ?";
		jdbcTemplateObject.update(SQL, new Object[]{id_lecturer, idModule});
		
		System.out.println("update lecturer " + id_lecturer + "'s taught modules: remove " + idModule);
	}

	@Override
	public void removeTaughtModule(final String firstName, final String lastName,
			final List<Integer> idModuleList) {

		String SQL= "SELECT id_lecturer from lecturer WHERE firstName= ? AND lastName= ?";
		final int id_lecturer=jdbcTemplateObject.queryForObject(SQL, Integer.class);
		
		String SQL2 = "DELETE FROM lecturer_teaches_module WHERE id_lecturer = ? " +
			"AND idModule = ?";
		
		System.out.println("update lecturer " + id_lecturer + "'s taught modules: removing ");
		jdbcTemplateObject.update(SQL2, new BatchPreparedStatementSetter() {

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
	public void removeTaughtModule(final Integer id_lecturer, final List<Integer> idModuleList) {
		String SQL = "DELETE FROM lecturer_teaches_module WHERE id_lecturer = ? " +
				"AND idModule = ?";
			
		System.out.println("update lecturer " + id_lecturer + "'s taught modules: remove ");
		jdbcTemplateObject.update(SQL, new BatchPreparedStatementSetter() {

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
	public ArrayList<Module> getTaughtModules(Integer id_lecturer) {
		ArrayList<Module> taughtModules;
		
		String SQL="SELECT * from module"
				+ " JOIN lecturer_teaches_module on "
				+ " lecturer_teaches_module.idModule = module.id_module"
				+ " AND lecturer_teaches_module.idLecturer= ?";
		
		taughtModules = (ArrayList<Module>) jdbcTemplateObject.query(SQL,
				new Object[] {id_lecturer}, new ModuleMapper());
		
		return taughtModules;
	}

	/* (non-Javadoc)
	 * @see com.citonline.db.interfaces.LecturerDAO#getTaughtModules(java.lang.String, java.lang.String)
	 */
	@Override
	public ArrayList<Module> getTaughtModules(String firstName, String lastName) {
		ArrayList<Module> taughtModules;
		
		String SQL="SELECT * from module"
				+ " JOIN lecturer_teaches_module on "
				+ " lecturer_teaches_module.idModule = module.id_module"
				+ " JOIN lecturer on "
				+ " lecturer_teaches_module.idLecturer = lecturer.id_lecturer"
				+ " AND lecturer.firstName= ? AND lecturer.lastName = ?";
		
		taughtModules = (ArrayList<Module>) jdbcTemplateObject.query(SQL,
				new Object[] {firstName, lastName}, new ModuleMapper());
		
		return taughtModules;
	}

	@Override
	public int countRows() {
		String SQL = "select count(id_lecturer) from lecturer";
		int rows=jdbcTemplateObject.queryForObject(SQL, Integer.class);
		return rows;
	}
	
}
