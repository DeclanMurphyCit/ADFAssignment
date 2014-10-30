package com.citonline.db;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.citonline.domain.Lecturer;

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
			String roomNumber) {
		
		String SQL = "INSERT INTO Lecturer (firstName, lastName, email, roomNumber)"
				+ "VALUES(?, ?, ?, ?)";
		
		jdbcTemplateObject.update(SQL, new Object[] { firstName, lastName, email, roomNumber});
		
<<<<<<< HEAD
		System.out.println("Created Record Name = " + firstName + " " + lastName +
				"\nemail = " + email + ", roomNumber = " + roomNumber);

	}

	@Override
	public void createLecturer(String firstName, String lastName, String email,
			String roomNumber, Integer idProgram) {

		String SQL = "INSERT INTO Lecturer (firstName, lastName, email, roomNumber, idProgram)"
				+ "VALUES(?, ?, ?, ?, ?)";
		
		jdbcTemplateObject.update(SQL, new Object[] { firstName, lastName, email, roomNumber, idProgram});
		
		System.out.println("Created Record Name = " + firstName + " " + lastName +
				"\nemail = " + email + ", roomNumber = " + roomNumber + ", idProgram = " + idProgram);

	}

	@Override
	public Lecturer getLecturer(Integer id) {
		String SQL = "select * from songwriter where id = ?";
		Lecturer lecturer = (Lecturer) jdbcTemplateObject.queryForObject(SQL, 
						new Object[]{id}, new LecturerMapper());
		
		return lecturer;
	}

	@Override
	public List<Lecturer> listLecturers() {
		String SQL = "select * from lecturer";
		List<Lecturer> lecturers = jdbcTemplateObject.query(SQL, 
						new LecturerMapper());
		return lecturers;
	}

	@Override
	public void updateLecturerEmail(String firstName, String lastName,
			String email) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateLecturerEmail(Integer id, String email) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateLecturerRoomNumber(String firstName, String lastName,
			String roomNumber) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateLecturerRoomNumber(Integer id, String roomNumber) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateLecturerManagedProgram(String firstName, String lastName,
			Integer idManagedProgram) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateLecturerManagedProgram(Integer id, Integer managedProgram) {
		// TODO Auto-generated method stub

=======
		System.out.println("Created Lecturer Name = " + firstName + " " + lastName +
				"\nemail = " + email + ", roomNumber = " + roomNumber);

	}

	@Override
	public void createLecturer(String firstName, String lastName, String email,
			String roomNumber, Integer idProgram) {

		String SQL = "INSERT INTO Lecturer (firstName, lastName, email, roomNumber, idProgram)"
				+ "VALUES(?, ?, ?, ?, ?)";
		
		jdbcTemplateObject.update(SQL, new Object[] { firstName, lastName, email, roomNumber, idProgram});
		
		System.out.println("Created Lecturer Name = " + firstName + " " + lastName +
				"\nemail = " + email + ", roomNumber = " + roomNumber + ", idProgram = " + idProgram);

	}	

	@Override
	public void deleteLecturer(Integer id) {
		String SQL = "delete from Lecturer where id = ?";
		jdbcTemplateObject.update(SQL, new Object[] {id});
		System.out.println("Deleted Lecturer with ID = " + id );
	}

	@Override
	public void deleteLecturer(String firstName, String lastName) {
		String SQL = "delete from Lecturer where firstName = ? and lastName = ?";
		jdbcTemplateObject.update(SQL, new Object[] {firstName, lastName});
		System.out.println("Deleted Lecturer with Name = " + firstName + " " + lastName );
	}

	@Override
	public Lecturer getLecturer(Integer id) {
		String SQL = "select * from songwriter where id = ?";
		Lecturer lecturer = (Lecturer) jdbcTemplateObject.queryForObject(SQL, 
						new Object[]{id}, new LecturerMapper());
		
		return lecturer;
	}
	

	@Override
	public Lecturer getLecturer(String firstName, String lastName) {
		String SQL = "select * from songwriter where firstName = ? and lastName = ? ";
		Lecturer lecturer = (Lecturer) jdbcTemplateObject.queryForObject(SQL, 
						new Object[]{firstName, lastName}, new LecturerMapper());
		
		return lecturer;
	}

	@Override
	public List<Lecturer> listLecturers() {
		String SQL = "select * from lecturer";
		List<Lecturer> lecturers = jdbcTemplateObject.query(SQL, 
						new LecturerMapper());
		return lecturers;
	}

	@Override
	public void updateLecturerEmail(String firstName, String lastName,
			String email) {
		String SQL = "UPDATE Lecturer SET email=? where firstName = ? and lastName = ?";
		jdbcTemplateObject.update(SQL, new Object[]{email, firstName, lastName});
		
		System.out.println("update Lecturer " + firstName + " " + lastName +
				"'s email. Set to: " + email);
	}

	@Override
	public void updateLecturerEmail(Integer id, String email) {
		String SQL = "UPDATE Lecturer SET email=? where id = ?";
		jdbcTemplateObject.update(SQL, new Object[]{email, id});
		
		System.out.println("update Lecturer " + id + "'s email. Set to: " + email);
	}

	@Override
	public void updateLecturerRoomNumber(String firstName, String lastName,
			String roomNumber) {
		String SQL = "UPDATE Lecturer SET roomNumber=? where firstName = ? and lastName = ?";
		jdbcTemplateObject.update(SQL, new Object[]{roomNumber, firstName, lastName});
		
		System.out.println("update Lecturer " + firstName + " " + lastName +
				"'s roomNumber. Set to: " + roomNumber);
	}

	@Override
	public void updateLecturerRoomNumber(Integer id, String roomNumber) {
		String SQL = "UPDATE Lecturer SET roomNumber=? where id = ?";
		jdbcTemplateObject.update(SQL, new Object[]{roomNumber, id});
		
		System.out.println("update Lecturer " + id + "'s roomNumber. Set to: " + roomNumber);
	}

	@Override
	public void updateLecturerManagedProgram(String firstName, String lastName,
			Integer idManagedProgram) {
		String SQL = "UPDATE Lecturer SET managedProgram=? where firstName = ? and lastName = ?";
		jdbcTemplateObject.update(SQL, new Object[]{idManagedProgram, firstName, lastName});
		
		System.out.println("update Lecturer " + firstName + " " + lastName +
				"'s managedProgram. Set to: " + idManagedProgram);
	}

	@Override
	public void updateLecturerManagedProgram(Integer id, Integer idManagedProgram) {
		String SQL = "UPDATE Lecturer SET managedProgram=? where id = ?";
		jdbcTemplateObject.update(SQL, new Object[]{idManagedProgram, id});
		
		System.out.println("update Lecturer " + id + "'s managedProgram. Set to: " + idManagedProgram);
>>>>>>> branch 'master' of https://github.com/DeclanMurphyCit/ADFAssignment
	}

	@Override
	public void addTaughtModule(String firstName, String lastName,
			Integer idModule) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addTaughtModule(Integer id, Integer idModule) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addTaughtModule(String firstName, String lastName,
			List<Integer> idModuleList) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addTaughtModule(Integer id, List<Integer> idModuleList) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeTaughtModule(String firstName, String lastName,
			Integer idModule) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeTaughtModule(Integer id, Integer idModule) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeTaughtModule(String firstName, String lastName,
			List<Integer> idModuleList) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeTaughtModule(Integer id, List<Integer> idModuleList) {
		// TODO Auto-generated method stub

	}

}
