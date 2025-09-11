package com.example.CSEWEBSQL4;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="manysubject")
public class CseSubjects {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
private int id;
	@Column
private String subname;
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="subjects")
	//@JsonBackReference
	@JsonIgnoreProperties("subjects")
private Set<CseStudent> students=new HashSet<>();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSubname() {
		return subname;
	}
	public void setSubname(String subname) {
		this.subname = subname;
	}
	public Set<CseStudent> getStudents() {
		return students;
	}
	public void setStudents(Set<CseStudent> students) {
		this.students = students;
	}
	
}
