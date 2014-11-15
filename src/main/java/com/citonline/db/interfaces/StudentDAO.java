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
	
	public void updateStudentPhone(String studentNumber, String email);
	
	public void updateStudentPhone(Integer id, String email);
	
	public void updateStudentAddress(String studentNumber, String addressLine1, String addressLine2);
	
	public void updateStudentAddress(Integer id, String addressLine1, String addressLine2);
	
	public void enrollModule(Integer idStudent, Integer idModule);
	
	public void enrollModule(String studentNumber,Integer idModule);
	
	public void enrollModules(final Integer idStudent, final ArrayList<Integer> idModuleList);
	
	public void enrollModules(final String studentNumber,final ArrayList<Integer> idModuleList);
	
	public void removeModule(Integer idStudent, Integer idModule);
	
	public void removeModule(String studentNumber, Integer idModule);
	
	public ArrayList<Module> getEnrolledModules(Integer id_student);
	
	public ArrayList<Module> getEnrolledModules(String studentNumber);
	
	public int countRows();
}