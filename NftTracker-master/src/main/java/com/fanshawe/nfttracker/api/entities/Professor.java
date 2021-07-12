package com.fanshawe.nfttracker.api.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.jsonwebtoken.lang.Strings;

@Entity
public class Professor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long professorId;

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	@Column(nullable = false)
	private String emailId;

	private Long contactNumber;

	private Long employeeId;

	@Column(nullable = false)
	private String empType;

	private String address;

	private String city;
	private String province;
	private String postalCode;

	private Date professorCreationDate = new Date();

	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "suggested_professors", joinColumns = @JoinColumn(name = "professor_id"), inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> suggestedCourses = new ArrayList<Course>();

	public Long getProfessorId() {
		return professorId;
	}

	public void setProfessorId(Long professorId) {
		this.professorId = professorId;
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

	public List<Course> getSuggestedCourses() {
		return suggestedCourses;
	}

	public void setSuggestedCourses(List<Course> suggestedCourses) {
		this.suggestedCourses = suggestedCourses;
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

	public Date getProfessorCreationDate() {
		return professorCreationDate;
	}

	public void setProfessorCreationDate(Date professorCreationDate) {
		this.professorCreationDate = professorCreationDate;
	}

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Boolean validateMandatoryParameters() {
		if (Strings.hasText(getFirstName()) && Strings.hasText(getLastName()) && Strings.hasText(getEmailId())
				&& Strings.hasText(getEmpType())) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Professor [professorId=" + professorId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", emailId=" + emailId + ", contactNumber=" + contactNumber + ", employeeId=" + employeeId
				+ ", empType=" + empType + ", address=" + address + ", city=" + city + ", province=" + province
				+ ", postalCode=" + postalCode + ", professorCreationDate=" + professorCreationDate
				+ ", suggestedCourses=" + suggestedCourses + "]";
	}

}