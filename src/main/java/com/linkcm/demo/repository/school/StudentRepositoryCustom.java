package com.linkcm.demo.repository.school;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.linkcm.core.entity.sys.QueryParam;

public interface StudentRepositoryCustom {

	public Page<?> findStudent(Pageable pageable, List<QueryParam> filters);

}
