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

import com.citonline.db.interfaces.StudentDAO;
import com.citonline.domain.Module;
import com.citonline.interfaces.impl.StudentImpl;

@Repository
public class StudentJdbcDaoSupport extends JdbcDaoSupport implements StudentDAO {

	@Autowired
	DataSource dataSource;
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
	public void createStudent(String firstName, String lastName,
			String studentNumber, String email, String phoneNumber,
			String addressLine1, String addressLine2) {
		
		String SQL = "INSERT INTO Student (firstName, lastName, studentNumber, "
				+ "email, phoneNumber,"
				+ "addressLine1, addressLine2) "
				+ "VALUES(?, ?, ?, ?, ?, ?, ?)";
		
		getJdbcTemplate().update(SQL, new Object[] { firstName, lastName, 
				studentNumber, email, phoneNumber, addressLine1, addressLine2});
		
		System.out.println("Created Student Name = " + firstName + " " + lastName +
				"\nStudent Number = " + studentNumber + ", email = " + email + ", phoneNumber = "
				+phoneNumber + ", address = " + addressLine1 + " " + addressLine2);
		
	}

	@Override
	@Transactional
	public void deleteStudent(Integer id) {
		String SQL = "delete from Student where id_student = ?";
		getJdbcTemplate().update(SQL, new Object[] {id});
		System.out.println("Deleted student where id_student = " + id );		
	}

	@Override
	@Transactional
	public void deleteStudent(String studentNumber) {
		String SQL = "delete from Student where studentNumber = ?";
		getJdbcTemplate().update(SQL, new Object[] {studentNumber});
		System.out.println("Deleted student where studentNumber = " + studentNumber );		
	}

	@Override
	@Transactional
	public StudentImpl getStudent(Integer id) {
		String SQL = "select * from Student where id_student = ?";
		StudentImpl student = (StudentImpl) getJdbcTemplate().queryForObject(SQL, 
						new Object[]{id}, new StudentMapper());
		
		return student;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public StudentImpl getStudent(String studentNumber) {
		String SQL = "select * from Student where studentNumber = ?";
		StudentImpl student = (StudentImpl) getJdbcTemplate().queryForObject(SQL, 
						new Object[]{studentNumber}, new StudentMapper());
		
		return student;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public List<StudentImpl> listStudents() {
		String SQL = "select * from student";
		List<StudentImpl> studentList = getJdbcTemplate().query(SQL, 
						new StudentMapper());
		return studentList;
	}

	@Override
	@Transactional
	public void updateStudentEmail(String studentNumber, String email) {
		String SQL = "update student set email = ? where studentNumber = ?";
		getJdbcTemplate().update(SQL, new Object[] {email,studentNumber});
		System.out.println("Updated student email to " + email 
				+ " where studentNumber = " + studentNumber );
	}

	@Override
	@Transactional
	public void updateStudentEmail(Integer id, String email) {
		String SQL = "update student set email = ? where id_student = ?";
		getJdbcTemplate().update(SQL, new Object[] {email,id});
		System.out.println("Updated student email to " 
		+ email + " where id_student = " + id );		
	}	

	@Override
	@Transactional
	public void updateStudentPhone(String studentNumber, String phoneNumber) {
		String SQL = "update student set phoneNumber = ? where studentNumber = ?";
		getJdbcTemplate().update(SQL, new Object[] {phoneNumber,studentNumber});
		System.out.println("Updated student phone number to " + phoneNumber 
				+ " where studentNumber = " + studentNumber );
	}

	@Override
	@Transactional
	public void updateStudentPhone(Integer id, String phoneNumber) {
		String SQL = "update student set phoneNumber = ? where id_student = ?";
		getJdbcTemplate().update(SQL, new Object[] {phoneNumber,id});
		System.out.println("Updated student phone number to " 
		+ phoneNumber + " where id_student = " + id );
	}
	
	@Override
	@Transactional
	public void updateStudentAddress(String studentNumber, 
			String addressLine1, String addressLine2) {
		String SQL = "update student set addressLine1 = ?, addressLine2 = ? "
				+ "where studentNumber = ?";
		getJdbcTemplate().update(SQL, new Object[] {addressLine1,
				addressLine2,studentNumber});
		System.out.println("Updated student address to " + addressLine1 + ", "
				+addressLine1+ " where studentNumber = " + studentNumber );		
	}

	@Override
	@Transactional
	public void updateStudentAddress(Integer id, String addressLine1, String addressLine2) {
		String SQL = "update student set addressLine1 = ?, addressLine2 = ? where id_student = ?";
		getJdbcTemplate().update(SQL, new Object[] {addressLine1,addressLine2,id});
		System.out.println("Updated student address to " + addressLine1 + ", "
		+addressLine1+ " where id_student = " + id );
	}

	@Override
	@Transactional
	public void enrollModule(Integer idStudent,Integer id_Module) {
		
		String SQL = "INSERT into student_enrolls_for (id_Module,idStudent) "
				+ "values (?,?)";
		getJdbcTemplate().update(SQL, new Object[] {id_Module,idStudent});
		
		System.out.println("Student "+idStudent + " has enrolled for module" + id_Module);			
	}
	
	@Override
	@Transactional
	public void enrollModule(String studentNumber,Integer id_Module) {
		
		String SQL = "select id_student from student where studentNumber = ?";
		
		final int id_student=getJdbcTemplate().queryForObject(SQL,
				new Object[]{studentNumber}, Integer.class);
		
		String SQL1 = "INSERT into student_enrolls_for (id_Module,idStudent) "
				+ "values (?,?)";
		getJdbcTemplate().update(SQL1, new Object[] {id_Module,id_student});
		
		System.out.println("Student "+id_student + " has enrolled for module" + id_Module);			
	}
	
	@Override
	@Transactional
	public void enrollModules(final Integer idStudent,final ArrayList<Integer> idModuleList) {
		String SQL = "INSERT INTO student_enrolls_for (idStudent,id_Module) VALUES (?, ?)";
		
		System.out.println("Student "+idStudent + " has enrolled for modules: ");			

		getJdbcTemplate().batchUpdate(SQL, new BatchPreparedStatementSetter() {

			public int getBatchSize() {
				return idModuleList.size();
			}
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				int idModule = idModuleList.get(i);
				ps.setInt(1, idStudent);
				ps.setInt(2, idModule);
				System.out.print(idModule + ",");
			}		
		});
	}
	
	@Override
	@Transactional
	public void enrollModules(final String studentNumber,final ArrayList<Integer> idModuleList) {		
		
		String SQL = "select id_student from student where studentNumber = ?";
		
		final int id_student=getJdbcTemplate().queryForObject(SQL,
				new Object[]{studentNumber}, Integer.class);
		
		
		String SQL2 = "INSERT INTO student_enrolls_for (idStudent,id_Module) VALUES (?, ?)";
		
		System.out.println("Student "+id_student + " has enrolled for modules: ");			

		getJdbcTemplate().batchUpdate(SQL2, new BatchPreparedStatementSetter() {

			public int getBatchSize() {
				return idModuleList.size();
			}
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				int idModule = idModuleList.get(i);
				ps.setInt(1, id_student);
				ps.setInt(2, idModule);
				System.out.print(idModule + ",");
			}		
		});
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public ArrayList<Module> getEnrolledModules(Integer id_student) {
		
		ArrayList<Module> enrolledModules;
		
		String SQL="SELECT * from module"
				+ " JOIN student_enrolls_for on "
				+ " student_enrolls_for.id_Module = module.id_module"
				+ " AND student_enrolls_for.idStudent= ?";
				
		enrolledModules = (ArrayList<Module>) getJdbcTemplate().query(SQL,
				new Object[] {id_student}, new ModuleMapper());
		
		return enrolledModules;
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public ArrayList<Module> getEnrolledModules(String studentNumber) {
		
		String SQL = "select id_student from student where studentNumber = ?";
		
		final int id_student=getJdbcTemplate().queryForObject(SQL,
				new Object[]{studentNumber}, Integer.class);
		
		ArrayList<Module> enrolledModules;
		
		String SQL2="SELECT * from module"
				+ " JOIN student_enrolls_for on "
				+ " student_enrolls_for.id_Module = module.id_module"
				+ " AND student_enrolls_for.idStudent= ?";
				
		enrolledModules = (ArrayList<Module>) getJdbcTemplate().query(SQL2,
				new Object[] {id_student}, new ModuleMapper());
		
		return enrolledModules;
	}

	@Override
	@Transactional
	public void removeModule(Integer idStudent, Integer idModule) {
		String SQL = "DELETE from student_enrolls_for WHERE idStudent = ? and id_Module = ?";
		getJdbcTemplate().update(SQL, new Object[]{idStudent, idModule});
		
		System.out.println("Student " + idStudent + " has disenrolled from moudle: " + idModule);
	}
	
	@Override
	@Transactional
	public void removeModule(String studentNumber, Integer idModule) {
		
		String SQL = "select id_student from student where studentNumber = ?";
		
		final int id_student=getJdbcTemplate().queryForObject(SQL,
				new Object[]{studentNumber}, Integer.class);
				
		String SQL2 = "DELETE from student_enrolls_for WHERE idStudent = ? and id_Module = ?";
		getJdbcTemplate().update(SQL2, new Object[]{id_student, idModule});
		
		System.out.println("Student " + id_student + " has disenrolled from moudle: " + idModule);
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
	public int countRows() {
		String SQL = "select count(id_student) from student";
		int rows=getJdbcTemplate().queryForObject(SQL, Integer.class);
		return rows;
	}

}
