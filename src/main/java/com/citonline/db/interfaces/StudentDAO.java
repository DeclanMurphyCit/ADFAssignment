package com.citonline.db.interfaces;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import com.citonline.domain.Module;
import com.citonline.interfaces.impl.StudentImpl;

@Service
public interface StudentDAO {

	public void setDataSource(DataSource dataSource);
	

	public void createStudent(String firstName, String lastName, String studentNumber, String email,
			String phoneNumber, String addressLine1,String addressLine2);
	
	public void deleteStudent(Integer id);

	public void deleteStudent(String studentNumber);	
	
	public StudentImpl getStudent(Integer id);

	public StudentImpl getStudent(String studentNumber);
	
	public List<StudentImpl> listStudents();   
	
	public void updateStudentEmail(String studentNumber, String email);
	
	public void updateStudentEmail(Integer id, String email);
	
	public void updateStudentAddress(String studentNumber, String addressLine1, String addressLine2);
	
	public void updateStudentAddress(Integer id, String addressLine1, String addressLine2);
	
	public void enrollModule(Integer idModule, Integer idStudent);
	
	public void enrollModules(final Integer idStudent, final List<Integer> idModuleList);
	
	public void removeModule(Integer idStudent, Integer idModule);
	
	public int countRows();

	public ArrayList<Module> getEnrolledModules(Integer id_student);
}