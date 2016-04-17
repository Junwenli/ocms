package com.linkcm.demo.web.school;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import com.linkcm.core.annotation.WebRequest;
import com.linkcm.core.annotation.WebRequest.RequestType;
import com.linkcm.core.service.CoreService;
import com.linkcm.core.util.ServletUtils;
import com.linkcm.core.util.WebUtils;
import com.linkcm.core.web.CoreAction;
import com.linkcm.core.web.EasyUiDataGrid;
import com.linkcm.demo.entity.ClassInfo;
import com.linkcm.demo.service.school.ClassInfoService;

@Namespace("/classinfo")
public class ClassInfoAction extends CoreAction<ClassInfo, Long> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ClassInfoService classInfoService;

	@WebRequest(RequestType.AJAX)
	public void listAjax() {
		getLogger().info("班级管理－－查询数据");
		EasyUiDataGrid entity = getService().searchForAjax(getPageable(), ServletUtils.getParameters());
		WebUtils.renderJson(entity);
	}

	@Override
	public CoreService<ClassInfo, Long> getService() {
		return classInfoService;
	}

	@Override
	public Sort getDefaultSort() {
		return new Sort("teacher");
	}
}
