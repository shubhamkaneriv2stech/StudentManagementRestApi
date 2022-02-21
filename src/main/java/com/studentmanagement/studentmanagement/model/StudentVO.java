package com.studentmanagement.studentmanagement.model;


//This is the Student View Model Class
public class StudentVO {

	private long rollNo;
	
	private String name;
	
	
	private String address;
	private int age;

	private String email;

	

	public StudentVO() {
		super();
		
	}

	
	public long getRollNo() {
		return rollNo;
	}

	public void setRollNo(long rollNo) {
		this.rollNo = rollNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}




	

