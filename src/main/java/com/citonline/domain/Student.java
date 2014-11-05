package com.citonline.domain;

/**
* 
* @author  Declan Murphy
* @since   29-10-2014
*/

public class Student extends Person{	
	
	
	String studentNumber, addressLine1,addressLine2;
	
	public Student(int id, String firstName, String lastName, String email,
			String phoneNumber, String studentNumber, String addressLine1, String addressLine2) {
		super(id, firstName, lastName, email, phoneNumber);
		this.studentNumber = studentNumber;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
	}	

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}
}
