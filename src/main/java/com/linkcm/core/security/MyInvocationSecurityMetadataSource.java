package com.linkcm.core.security;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntUrlPathMatcher;
import org.springframework.security.web.util.UrlMatcher;


import com.linkcm.core.entity.sys.SysGivepermission;
import com.linkcm.core.entity.sys.SysResource;
import com.linkcm.core.entity.sys.SysRole;
import com.linkcm.core.service.sys.SysGivePermissionService;
import com.linkcm.core.service.sys.SysResourceService;
import com.linkcm.core.service.sys.SysRoleService;

public class MyInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	private SysRoleService sysRoleService;

	private SysResourceService sysResourceService;
	
	private SysGivePermissionService sysGivePermissionService;

	private UrlMatcher urlMatcher = new AntUrlPathMatcher();;
	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	public void updateResource() {
		synchronized (resourceMap) {
			resourceMap.clear();
			loadMap();
		}
	}

	private void loadMap() {
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		List<SysResource> resources = sysResourceService.findBySystemId();
		for (SysResource resource : resources) {
			Collection<ConfigAttribute> atts = new HashSet<ConfigAttribute>();
			List<SysRole> roles = sysRoleService.findByUrl(resource.getUrl());
			List<SysGivepermission> givepermissions=sysGivePermissionService.findByUrl(resource.getUrl());
			for (SysRole role : roles) {
				ConfigAttribute ca = new SecurityConfig(role.getRoleCode().toString());
				atts.add(ca);
			}
			for (SysGivepermission givepermission : givepermissions) {
				ConfigAttribute ca = new SecurityConfig(SysGivePermissionService.APREFIX+givepermission.getId().toString());
				atts.add(ca);
			}

			resourceMap.put(resource.getUrl(), atts);
		}
	}

	public SysRoleService getSysRoleService() {
		return sysRoleService;
	}

	public void setSysRoleService(SysRoleService sysRoleService) {
		this.sysRoleService = sysRoleService;
	}

	public SysResourceService getSysResourceService() {
		return sysResourceService;
	}

	public void setSysResourceService(SysResourceService sysResourceService) {
		this.sysResourceService = sysResourceService;
	}

	public SysGivePermissionService getSysGivePermissionService() {
		return sysGivePermissionService;
	}

	public void setSysGivePermissionService(
			SysGivePermissionService sysGivePermissionService) {
		this.sysGivePermissionService = sysGivePermissionService;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		if (resourceMap == null) {
			loadMap();
		}
		String url = ((FilterInvocation) object).getRequestUrl();
		Iterator<String> ite = resourceMap.keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();
			if (resURL != null) {
				if (urlMatcher.pathMatchesUrl(resURL, url)) {
					if ("/**".equals(resURL)) {
						while (ite.hasNext()) {
							String tmpResURL = ite.next();
							if (url.indexOf(tmpResURL.replaceAll("\\*", "")) != -1) {
								return resourceMap.get(tmpResURL);
							}
						}
					}
					return resourceMap.get(resURL);
				}
			}

		}
		return Collections.emptyList();
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
