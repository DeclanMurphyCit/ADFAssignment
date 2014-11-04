package com.citonline.db.interfaces;

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
	
	public void updateStudentAddress(String studentNumber, String addressLine1, String addressLine2);
	
	public void updateStudentAddress(Integer id, String addressLine1, String addressLine2);
	
	public void enrollModule(Integer idModule, Integer idStudent);
	
	public void enrollModules(final Integer idStudent, final List<Integer> idModuleList);
	
	public void removeModule(Integer idStudent, Integer idModule);
	
	public void addModuleDeferral(Integer idModule);

	public void removeModuleDeferral(Integer idModule);
	
}