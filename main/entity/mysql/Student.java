package com.linkcm.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linkcm.core.annotation.Validation;
import com.linkcm.core.util.CoreUtils;
import com.linkcm.demo.entity.type.SexType;

@Entity
@Table(name = "student")
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private Long id;
	@Validation(name = "姓名", nullable = false, min = 2, max = 50)
	@Column(name = "name")
	private String name;
	@Validation(name = "年龄", nullable = false, min = 6, max = 30)
	@Column(name = "age")
	private Integer age;
	@Column(name = "class_id")
	private Long classId;
	@Column(name = "sex")
	private Integer sex;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getSexDesc() {
		return SexType.getDesc(sex);
	}

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public String getClassTitle() {
		return CoreUtils.code2value(classId, ClassInfo.class, "id", "classname");
	}

}
