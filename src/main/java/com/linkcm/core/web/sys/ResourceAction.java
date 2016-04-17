package com.linkcm.core.web.sys;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.linkcm.core.annotation.WebRequest;
import com.linkcm.core.annotation.WebRequest.RequestType;
import com.linkcm.core.entity.sys.SysResource;
import com.linkcm.core.security.MyInvocationSecurityMetadataSource;
import com.linkcm.core.service.CoreService;
import com.linkcm.core.service.sys.SysResourceService;
import com.linkcm.core.util.CoreUtils;
import com.linkcm.core.util.SecurityUtils;
import com.linkcm.core.util.WebUtils;
import com.linkcm.core.web.CoreAction;

@Namespace("/resource")
public class ResourceAction extends CoreAction<SysResource, Long> {

	private static final long serialVersionUID = 1L;

	private Long _parentId;

	@Autowired
	private SysResourceService sysResourceService;

	@Autowired
	private MyInvocationSecurityMetadataSource myInvocationSecurityMetadataSource;

	@WebRequest(RequestType.AJAX)
	public void saveByOperate() {
		CoreUtils.validateEntity(getEntity());
		getService().save(getEntity(), isOperate());
		myInvocationSecurityMetadataSource.updateResource();
		success("save_success");
	}

	/**
	 * 根据父类型获取类型map
	 * 
	 * */
	public Map<Object, Object> getTypeMap() {
		if (isOperate()) {
			// 新增
			return sysResourceService.getTypeMap(_parentId, isOperate());
		} else {
			// 修改
			return sysResourceService.getTypeMap(getId(), isOperate());
		}
	}

	@WebRequest(RequestType.AJAX)
	public void findOperations() {
		List<Map<String, Object>> result = sysResourceService.findFunctionByUserId(SecurityUtils.getUserId());
		WebUtils.renderJson(result);
	}

	@WebRequest(RequestType.AJAX)
	public void findSystemOperations() {
		WebUtils.renderJson(sysResourceService.findSystemByUserId(SecurityUtils.getUserId()));
	}

	@WebRequest(RequestType.AJAX)
	public void findMenu() {
		WebUtils.renderJson(sysResourceService.findMenuByUserId(SecurityUtils.getUserId()));
	}

	@Override
	public CoreService<SysResource, Long> getService() {
		return sysResourceService;
	}

	public Long get_parentId() {
		return _parentId;
	}

	public void set_parentId(Long _parentId) {
		this._parentId = _parentId;
	}
}
