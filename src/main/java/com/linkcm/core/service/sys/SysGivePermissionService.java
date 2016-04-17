package com.linkcm.core.service.sys;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.linkcm.core.annotation.ErrorMessage;
import com.linkcm.core.dao.BaseRepository;
import com.linkcm.core.entity.sys.SysGivepermission;
import com.linkcm.core.entity.type.ResourceType;
import com.linkcm.core.repository.sys.SysGivePermissionRepository;
import com.linkcm.core.service.CoreService;
import com.linkcm.core.util.EasyUiUtils;
import com.linkcm.core.web.EasyUiDataGrid;

@Component
@Transactional
public class SysGivePermissionService extends CoreService<SysGivepermission, Long> {

	@Autowired
	private SysGivePermissionRepository sysGivePermissionRepository;

	public static final String APREFIX = "user_permission";// 前缀

	@ErrorMessage("sys_roleAuthorizedFail")
	public void save(Long userId, Long[] resourceIds) {
		List<SysGivepermission> exists = sysGivePermissionRepository.findByUserId(userId);
		List<SysGivepermission> create = getCreateList(userId, resourceIds, exists);
		List<SysGivepermission> remove = getRemoveList(resourceIds, exists);
		if (!create.isEmpty()) {
			save(create.toArray(new SysGivepermission[0]));
		}
		for (SysGivepermission permission : remove) {
			delete(permission.getId());
		}
	}

	/**
	 * 获取所有实体
	 * 
	 * @param sysRoleDao
	 */
	@ErrorMessage("sys_givePermission_getListFail")
	public EasyUiDataGrid findAllByUserId(Long userId, Long userIdParent) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> result = sysGivePermissionRepository.findVoByUserId(userId, userIdParent);
		List<Map<String, Object>> maps = sysGivePermissionRepository.findResourceByUserId(userId);
		for (Map<String, Object> r : result) {
			r.put("unchecked", false);
			for (Map<String, Object> map : maps) {
				if (r.get("permissionId").equals(map.get("permissionId"))) {
					r.put("unchecked", true);
				}
			}
			list.add(r);
		}
		ResourceType.transformer.transfer("resourceType", list);
		return EasyUiUtils.getEasyUiDataGrid(result);
	}

	private List<SysGivepermission> getRemoveList(Long[] resourceIds, List<SysGivepermission> exists) {
		List<SysGivepermission> remove = new LinkedList<SysGivepermission>();
		boolean existsPermission = false;
		for (SysGivepermission permission : exists) {
			for (Long resourceid : resourceIds) {
				if (permission.getPermissionId().equals(resourceid)) {
					existsPermission = true;
					break;
				}
			}
			if (existsPermission) {
				existsPermission = false;
			} else {
				remove.add(permission);
			}
		}
		return remove;
	}

	private List<SysGivepermission> getCreateList(Long userId, Long[] resourceIds, List<SysGivepermission> exists) {
		List<SysGivepermission> create = new LinkedList<SysGivepermission>();
		if (resourceIds.length == 0) {
			return create;
		}
		boolean existsPermission = false;
		for (Long resourceid : resourceIds) {
			if (!"".equals(resourceid)) {
				for (SysGivepermission permission : exists) {
					if (permission.getPermissionId().equals(resourceid)) {
						existsPermission = true;
						break;
					}
				}
				if (existsPermission) {
					existsPermission = false;
				} else {
					SysGivepermission permission = new SysGivepermission();
					permission.setUserId(userId);
					permission.setPermissionId(resourceid);
					create.add(permission);
				}
			}

		}
		return create;
	}

	@ErrorMessage("获取权限失败")
	public List<SysGivepermission> findByUrl(String url) {
		return sysGivePermissionRepository.findByUrl(url);
	}

	@ErrorMessage("获取权限失败")
	public Set<GrantedAuthority> findGrantedAuthorityByUserId(Long userId, Set<GrantedAuthority> grantedAuths) {
		List<SysGivepermission> list = sysGivePermissionRepository.findByUserId(userId);
		for (SysGivepermission givepermission : list) {
			grantedAuths.add(new GrantedAuthorityImpl(APREFIX + givepermission.getId().toString()));
		}
		return grantedAuths;
	}

	@Override
	protected BaseRepository<SysGivepermission, Long> getDao() {
		return sysGivePermissionRepository;
	}

	@Override
	public String getModule() {
		return "权限管理";
	}

}
