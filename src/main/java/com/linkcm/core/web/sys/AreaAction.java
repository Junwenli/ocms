package com.linkcm.core.web.sys;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.linkcm.core.entity.sys.SysArea;
import com.linkcm.core.service.CoreService;
import com.linkcm.core.service.sys.SysAreaService;
import com.linkcm.core.web.CoreAction;

@Namespace("/area")
public class AreaAction extends CoreAction<SysArea, Long> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SysAreaService sysAreaService;

	@Override
	public CoreService<SysArea, Long> getService() {
		return sysAreaService;
	}

}
