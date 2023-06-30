package com.junit.unittesting.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {
	@Id
	private Integer studentId;
	private String name;
	private String email;
	private Integer mobileNumber; 
	private String address;
	private String gender;
	
	public Student() {
	}
	
	public Student(Integer studentId, String name, String email, Integer mobileNumber, String address, String gender) {
		this.studentId = studentId;
		this.name = name;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.gender = gender;
	}
	
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(Integer mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", name=" + name + ", email=" + email + ", mobileNumber="
				+ mobileNumber + ", address=" + address + ", gender=" + gender + "]";
	}

}
