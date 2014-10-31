package com.citonline.db;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.citonline.domain.Lecturer;
import com.citonline.domain.Student;

public class StudentJdbcTemplate implements StudentDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	@Override
	public void createStudent(String firstName, String lastName,
			String studentNumber, String email, String phoneNumber,
			String addressLine1, String addressLine2) {
		
		String SQL = "INSERT INTO Student (firstName, lastName, studentNumber, email, phoneNumber,"
				+ "addressLine1, addressLine2) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
		
		jdbcTemplateObject.update(SQL, new Object[] { firstName, lastName, studentNumber, email,
				phoneNumber, addressLine1, addressLine2});
		
		System.out.println("Created Student Name = " + firstName + " " + lastName +
				"\nStudent Number = " + studentNumber + ", email = " + email + ", phoneNumber = "
				+phoneNumber + ", address = " + addressLine1 + " " + addressLine2);
		
	}

	@Override
	public void deleteStudent(Integer id) {
		String SQL = "delete from Student where id = ?";
		jdbcTemplateObject.update(SQL, new Object[] {id});
		System.out.println("Deleted student where id = " + id );		
	}

	@Override
	public void deleteStudent(String studentNumber) {
		String SQL = "delete from Student where studentNumber = ?";
		jdbcTemplateObject.update(SQL, new Object[] {studentNumber});
		System.out.println("Deleted student where student number = " + studentNumber );
		
	}

	@Override
	public Student getStudent(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Student getStudent(String studentNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> listStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateStudentEmail(String studentNumber, String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateStudentEmail(Integer id, String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addProgram(Integer idProgram) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addModule(Integer idModule) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addModuleDeferral(Integer idModule) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addProgramDeferral(Integer idProgram) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeModuleDeferral(Integer idModule) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeProgramDeferral(Integer idProgram) {
		// TODO Auto-generated method stub
		
	}

}
