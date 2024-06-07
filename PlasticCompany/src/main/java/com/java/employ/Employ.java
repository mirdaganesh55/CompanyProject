package com.java.employ;

import java.util.Date;
import javax.servlet.http.Part;

public class Employ {
	private int empId;
	private String firstName;
	private String lastName;
	private String gender;
	private Date dateOfBirth;
	private String address;
	private String phoneNumber;
	private String email;
	private Part file;
	private String imgUrl;

    public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Part getFile() {
        return file;
    }
 
    public void setFile(Part file) {
        this.file = file;
    }
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Employ() {
		super();
	}

	public Employ(int empId, String firstName, String lastName, String gender, Date dateOfBirth, String address,
			String phoneNumber, String email, Part file, String filePath, String filePathNew) {
		super();
		this.empId = empId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.file = file;
	}

	@Override
	public String toString() {
		return "Employ [empId=" + empId + ", firstName=" + firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", dateOfBirth=" + dateOfBirth + ", address=" + address + ", phoneNumber=" + phoneNumber + ", email="
				+ email + ", file=" + file + "]";
	}
	
}
