package com.linkcm.core.web.sys;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import com.linkcm.core.entity.sys.SysParam;
import com.linkcm.core.service.CoreService;
import com.linkcm.core.service.sys.SysParamService;
import com.linkcm.core.web.CoreAction;

/**
 * 系统参数管理
 * 
 * @author ZJY
 */
@Namespace("/param")
public class ParamAction extends CoreAction<SysParam, Long> {

	private static final long serialVersionUID = 1L;
	

	@Autowired
	private SysParamService sysParamMgtService;

	@Override
	public Sort getDefaultSort() {
		return new Sort("id");
	}

	@Override
	public CoreService<SysParam, Long> getService() {
		return sysParamMgtService;
	}


}
