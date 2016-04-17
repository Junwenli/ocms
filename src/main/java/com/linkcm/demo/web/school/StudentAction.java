package com.linkcm.demo.web.school;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.linkcm.core.service.CoreService;
import com.linkcm.core.util.ServletUtils;
import com.linkcm.core.web.CoreAction;
import com.linkcm.demo.entity.ClassInfo;
import com.linkcm.demo.entity.Student;
import com.linkcm.demo.service.school.StudentService;

@Namespace("/student")
@Results({ @Result(name = "export", type = "excel", location = "/resources/excel/students.xls") })
public class StudentAction extends CoreAction<Student, Long> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private StudentService studentService;

	private List<Student> students;

	public List<Student> getStudents() {
		return students;
	}

	public String export() {
		try {
			students = getService().search(getPageable(), ServletUtils.getParameters()).getContent();
		} catch (Exception e) {
			getLogger().error("导出学生失败", e);
		}

		return "export";
	}

	@Override
	public CoreService<Student, Long> getService() {
		return studentService;
	}

	public Map<Object, Object> getClassMap() {
		return getChooseMap(ClassInfo.class, "id", "classname");
	}

}
