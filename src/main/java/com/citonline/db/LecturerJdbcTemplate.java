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
