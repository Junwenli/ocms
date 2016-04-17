package com.linkcm.core.web.sys;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import com.linkcm.core.entity.sys.SysDepartment;
import com.linkcm.core.service.CoreService;
import com.linkcm.core.service.sys.SysDepartmentService;
import com.linkcm.core.web.CoreAction;

@Namespace("/department")
public class DepartmentAction extends CoreAction<SysDepartment, Long> {

	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private int useRate;
	@Autowired
	private SysDepartmentService sysDepartmentMgtService;

	public String user() {
		prepareModel();
		return "user";
	}

	@Override
	public CoreService<SysDepartment, Long> getService() {
		return sysDepartmentMgtService;
	}

	@Override
	public Sort getDefaultSort() {
		return new Sort("id");
	}

	public int getUseRate() {
		return sysDepartmentMgtService.getUseRate();
	}

	public void setUseRate(int useRate) {
		this.useRate = useRate;
	}

}
