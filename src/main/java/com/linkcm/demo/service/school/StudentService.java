package com.linkcm.demo.service.school;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.linkcm.core.annotation.ErrorMessage;
import com.linkcm.core.dao.BaseRepository;
import com.linkcm.core.entity.sys.QueryParam;
import com.linkcm.core.service.CoreService;
import com.linkcm.core.util.EasyUiUtils;
import com.linkcm.core.web.EasyUiDataGrid;
import com.linkcm.demo.entity.Student;
import com.linkcm.demo.entity.type.SexType;
import com.linkcm.demo.repository.school.StudentRepository;

@Component
@Transactional
public class StudentService extends CoreService<Student, Long> {

	@Autowired
	private StudentRepository studentRepository;

	@ErrorMessage(LIST_FAILURE)
	public EasyUiDataGrid searchForAjax(final Pageable pageable, final List<QueryParam> filters) {
		Page<?> pageEntity = studentRepository.findStudent(pageable, filters);
		SexType.transformer.transfer("sexDesc", pageEntity);
		return EasyUiUtils.getEasyUiDataGrid(pageEntity);
	}

	@Override
	protected BaseRepository<Student, Long> getDao() {
		return studentRepository;
	}

	@Override
	public String getModule() {
		return "学生管理";
	}

}
