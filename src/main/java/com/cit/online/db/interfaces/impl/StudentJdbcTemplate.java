package com.cit.online.db.interfaces.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import com.citonline.db.interfaces.StudentDAO;
import com.citonline.domain.Student;
import com.citonline.interfaces.impl.StudentImpl;

public class StudentJdbcTemplate implements StudentDAO {

	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	@Autowired
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
		String SQL = "delete from Student where id_student = ?";
		jdbcTemplateObject.update(SQL, new Object[] {id});
		System.out.println("Deleted student where id_student = " + id );		
	}

	@Override
	public void deleteStudent(String studentNumber) {
		String SQL = "delete from Student where studentNumber = ?";
		jdbcTemplateObject.update(SQL, new Object[] {studentNumber});
		System.out.println("Deleted student where studentNumber = " + studentNumber );		
	}

	@Override
	public StudentImpl getStudent(Integer id) {
		String SQL = "select * from Student where id_student = ?";
		StudentImpl student = (StudentImpl) jdbcTemplateObject.queryForObject(SQL, 
						new Object[]{id}, new StudentMapper());
		
		return student;
	}

	@Override
	public StudentImpl getStudent(String studentNumber) {
		String SQL = "select * from Student where studentNumber = ?";
		StudentImpl student = (StudentImpl) jdbcTemplateObject.queryForObject(SQL, 
						new Object[]{studentNumber}, new StudentMapper());
		
		return student;
	}

	@Override
	public List<StudentImpl> listStudents() {
		String SQL = "select * from student";
		List<StudentImpl> studentList = jdbcTemplateObject.query(SQL, 
						new StudentMapper());
		return studentList;
	}

	@Override
	public void updateStudentEmail(String studentNumber, String email) {
		String SQL = "update student set email = ? where studentNumber = ?";
		jdbcTemplateObject.update(SQL, new Object[] {email,studentNumber});
		System.out.println("Updated student email to " + email + " where studentNumber = " + studentNumber );
	}

	@Override
	public void updateStudentEmail(Integer id, String email) {
		String SQL = "update student set email = ? where id_student = ?";
		jdbcTemplateObject.update(SQL, new Object[] {email,id});
		System.out.println("Updated student email to " + email + " where id_student = " + id );
		
	}
	
	@Override
	public void updateStudentAddress(String studentNumber, String addressLine1, String addressLine2) {
		String SQL = "update student set addressLine1 = ?, addressLine2 = ? where studentNumber = ?";
		jdbcTemplateObject.update(SQL, new Object[] {addressLine1,addressLine1,studentNumber});
		System.out.println("Updated student address to " + addressLine1 + ", "+addressLine1+ " where studentNumber = " + studentNumber );
		
	}

	@Override
	public void updateStudentAddress(Integer id, String addressLine1, String addressLine2) {
		String SQL = "update student set email = ? where id_student = ?";
		jdbcTemplateObject.update(SQL, new Object[] {addressLine1,addressLine1,id});
		System.out.println("Updated student address to " + addressLine1 + ", "+addressLine1+ " where id_student = " + id );
	}

	@Override
	public void enrollModule(Integer idModule, Integer idStudent) {
	
		//String SQL = "SELECT id_student from student WHERE studentNumber = ?";
		
		String SQL = "INSERT into student_enrolls_for (idModule,idStudent) "
				+ "values (?,?)";
		jdbcTemplateObject.update(SQL, new Object[] {idModule,idStudent});
		
		System.out.println("Student "+idStudent + " has enrolled for module" + idModule);			
	}
	
	@Override
	public void enrollModules(final Integer idStudent, final List<Integer> idModuleList) {
		String SQL = "INSERT INTO student_enrolls_for (idStudent,idModule)" +
				"VALUES (?, ?)";
		
		System.out.println("Student "+idStudent + " has enrolled for modules: ");			

		jdbcTemplateObject.update(SQL, new BatchPreparedStatementSetter() {

			public int getBatchSize() {
				return idModuleList.size();
			}
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Integer idModule = idModuleList.get(i);
				ps.setInt(1, idStudent);
				ps.setInt(2, idModule);
				System.out.print(idModule + ",");
			}		
		});
	}	

	@Override
	public void removeModule(Integer idStudent, Integer idModule) {
		String SQL = "DELETE from student_enrolls_for WHERE idStudent = ? and idModule = ?";
		jdbcTemplateObject.update(SQL, new Object[]{idStudent, idModule});
		
		System.out.println("Student " + idStudent + " has disenrolled from moudle: " + idModule);
	}
}
