package com.linkcm.core.service.sys;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.linkcm.core.annotation.ErrorMessage;
import com.linkcm.core.dao.BaseRepository;
import com.linkcm.core.entity.sys.SysPermission;
import com.linkcm.core.entity.type.ResourceType;
import com.linkcm.core.repository.sys.SysPermissionRepository;
import com.linkcm.core.service.CoreService;
import com.linkcm.core.util.EasyUiUtils;
import com.linkcm.core.web.EasyUiDataGrid;

@Component
@Transactional
public class SysPermissionService extends CoreService<SysPermission, Long> {

	@Autowired
	private SysPermissionRepository sysPermissionRepository;

	@ErrorMessage("sys_permission_saveFail")
	public void save(Long roleId, Long[] resourceIds) {
		List<SysPermission> exists = sysPermissionRepository.findByRoleId(roleId);
		List<SysPermission> create = getCreateList(roleId, resourceIds, exists);
		List<SysPermission> remove = getRemoveList(resourceIds, exists);
		if (!create.isEmpty()) {
			save(create.toArray(new SysPermission[0]));
		}
		for (SysPermission permission : remove) {
			delete(permission.getId());
		}
	}

	/**
	 * 获取所有实体
	 * 
	 * @param sysRoleDao
	 */
	@ErrorMessage("sys_permission_getListFail")
	public EasyUiDataGrid findAllByRoleId(Long roleId) {
		List<?> result = sysPermissionRepository.findVoByRoleId(roleId);
		ResourceType.transformer.transfer("resourceType", result);
		return EasyUiUtils.getEasyUiDataGrid(result);
	}

	private List<SysPermission> getRemoveList(Long[] resourceIds, List<SysPermission> exists) {
		List<SysPermission> remove = new LinkedList<SysPermission>();
		boolean existsPermission = false;
		for (SysPermission permission : exists) {
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

	private List<SysPermission> getCreateList(Long roleId, Long[] resourceIds, List<SysPermission> exists) {
		List<SysPermission> create = new LinkedList<SysPermission>();
		if (resourceIds.length == 0) {
			return create;
		}
		boolean existsPermission = false;
		for (Long resourceid : resourceIds) {
			if (!"".equals(resourceid)) {
				for (SysPermission permission : exists) {
					if (permission.getPermissionId().equals(resourceid)) {
						existsPermission = true;
						break;
					}
				}
				if (existsPermission) {
					existsPermission = false;
				} else {
					SysPermission permission = new SysPermission();
					permission.setRoleId(roleId);
					permission.setPermissionId(resourceid);
					create.add(permission);
				}
			}

		}
		return create;
	}

	@Override
	protected BaseRepository<SysPermission, Long> getDao() {
		return sysPermissionRepository;
	}

	@Override
	public String getModule() {
		return "权限管理";
	}

}
