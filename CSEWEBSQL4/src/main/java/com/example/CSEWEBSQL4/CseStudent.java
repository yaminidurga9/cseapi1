package com.example.CSEWEBSQL4;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="manystudents")
public class CseStudent {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
private int id;
	@Column
private String name;
	@Column
private String branch;
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	@JoinTable(name="students_subjects",joinColumns= {@JoinColumn(name="csestudent_id")},inverseJoinColumns= {@JoinColumn(name="subject_id")})
	//@JsonManagedReference
	@JsonIgnoreProperties("student")
	private  Set<CseSubjects> subjects=new HashSet<>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public Set<CseSubjects> getSubjects() {
		return subjects;
	}
	public void setSubjects(Set<CseSubjects> subjects) {
		this.subjects = subjects;
	}
	
}
