package com.citonline.db;

import java.util.List;

import javax.sql.DataSource;

import com.citonline.domain.Student;

public interface StudentDAO {

	public void setDataSource(DataSource dataSource);
	

	public void createStudent(String firstName, String lastName, String studentNumber, String email,
			String phoneNumber, String addressLine1,String addressLine2);
	
	public void deleteStudent(Integer id);

	public void deleteStudent(String studentNumber);	
	
	public Student getStudent(Integer id);

	public Student getStudent(String studentNumber);
	
	public List<Student> listStudents();   
	
	public void updateStudentEmail(String studentNumber, String email);
	
	public void updateStudentEmail(Integer id, String email);
	
	public void addProgram(Integer idProgram);
	
	public void addModule(Integer idModule);
	
	public void addModuleDeferral(Integer idModule);
	
	public void addProgramDeferral(Integer idProgram);

	public void removeModuleDeferral(Integer idModule);
	
	public void removeProgramDeferral(Integer idProgram);	
}