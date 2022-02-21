package com.studentmanagement.studentmanagement.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.studentmanagement.studentmanagement.model.StudentVO;



//This is Student Entity Class
@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROLL_NO")
	private long rollNo;
	
	@NotEmpty
	@Size(min = 2,max=40)
	@Column(name = "NAME")
	private String name;
	
	@NotEmpty
	@Column(name="ADDRESS")
	private String address;
	
	@Min(18)
	@Max(100)
	@Column(name="AGE")
	private int age;
	
	@Email
	@NotEmpty
	@Column(name = "EMAIL")
	private String email;
	
	 @Override
	public String toString() {
		return "Student [rollNo=" + rollNo + ", name=" + name + ", address=" + address + ", age=" + age + ", Email="
				+ email + "]";
	}

	public Student(long rollNo, String name, String address, int age, String email) {
		super();
		this.rollNo = rollNo;
		this.name = name;
		this.address = address;
		this.age = age;
		this.email = email;
	}

	public Student() {
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

	public StudentVO toConvertStudentVo(){
		    StudentVO studentVO=new StudentVO();
		    studentVO.setRollNo(getRollNo());
		    studentVO.setAddress(getAddress());
		    studentVO.setEmail(getEmail());
		    studentVO.setName(getName());
		    studentVO.setAge(getAge());
		    return studentVO;
		 }
}

