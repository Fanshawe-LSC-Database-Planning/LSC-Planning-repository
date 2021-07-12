package com.fanshawe.nfttracker.api.request;

import java.util.List;

import com.fanshawe.nfttracker.api.entities.Course;

import io.jsonwebtoken.lang.Strings;

public class ProfessorRequest {

	private String firstName;

	private String lastName;

	private String emailId;

	private Long contactNumber;
	private Long employeeId;
	private String empType;

	private String address;

	private String city;
	private String province;
	private String postalCode;
	private List<Course> suggestedCourses;

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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmpType() {
		return empType;
	}

	public void setEmpType(String empType) {
		this.empType = empType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public List<Course> getSuggestedCourses() {
		return suggestedCourses;
	}

	public void setSuggestedCourses(List<Course> suggestedCourses) {
		this.suggestedCourses = suggestedCourses;
	}

	public Boolean validateMandatoryParameters() {
		if (Strings.hasText(getFirstName()) && Strings.hasText(getLastName()) && Strings.hasText(getEmailId())
				&& Strings.hasText(getEmpType())) {
			return true;
		}
		return false;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public String toString() {
		return "ProfessorRequest [firstName=" + firstName + ", lastName=" + lastName + ", emailId=" + emailId
				+ ", contactNumber=" + contactNumber + ", employeeId=" + employeeId + ", empType=" + empType
				+ ", address=" + address + ", city=" + city + ", province=" + province + ", postalCode=" + postalCode
				+ ", suggestedCourses=" + suggestedCourses + "]";
	}

}
