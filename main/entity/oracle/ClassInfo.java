package com.linkcm.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "classinfo")
public class ClassInfo {
	@Id
	@SequenceGenerator(name = "classinfoGenerator", sequenceName = "S_CLASSINFO")
	@GeneratedValue(generator = "classinfoGenerator", strategy = GenerationType.SEQUENCE)
	@Column(name = "classid")
	private Long id;
	@Column(name = "classname")
	private String classname;
	@Column(name = "teacher")
	private String teacher;

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

}
