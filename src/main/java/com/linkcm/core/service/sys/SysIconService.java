package com.linkcm.core.service.sys;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.linkcm.core.annotation.ErrorMessage;
import com.linkcm.core.dao.BaseRepository;
import com.linkcm.core.entity.sys.SysIcon;
import com.linkcm.core.entity.sys.SysIconRole;
import com.linkcm.core.entity.type.RoleStatus;
import com.linkcm.core.repository.sys.SysIconRepository;
import com.linkcm.core.repository.sys.SysIconRoleRepository;
import com.linkcm.core.service.CoreService;
import com.linkcm.core.util.SecurityUtils;

@Component
@Transactional
public class SysIconService extends CoreService<SysIcon, Long> {

	@Autowired
	private SysIconRepository sysIconRepository;

	@Autowired
	private SysIconRoleRepository sysIconRoleRepository;

	@Override
	protected BaseRepository<SysIcon, Long> getDao() {
		return sysIconRepository;
	}

	@ErrorMessage("grant_failure")
	public List<?> findRoleByIconId(Long iconId) {
		List<?> result = sysIconRepository.findRoleByIconId(iconId);
		RoleStatus.transformer.transfer("status", result);
		return result;
	}

	@ErrorMessage("grant_failure")
	public List<SysIcon> findIconByUserId() {
		return sysIconRoleRepository.findByUserId(SecurityUtils.getUserId());
	}

	@ErrorMessage("grant_failure")
	public void grant(Long iconId, Long[] roleIds) {
		List<SysIconRole> exists = sysIconRoleRepository.findByIconId(iconId);
		List<SysIconRole> create = getCreateList(iconId, roleIds, exists);
		List<SysIconRole> remove = getRemoveList(roleIds, exists);
		if (!create.isEmpty()) {
			sysIconRoleRepository.save(create);
		}
		sysIconRoleRepository.delete(remove);
	}

	private List<SysIconRole> getRemoveList(Long[] roleIds, List<SysIconRole> exists) {
		List<SysIconRole> remove = new LinkedList<SysIconRole>();
		boolean existsPermission = false;
		for (SysIconRole sysIconRole : exists) {
			for (Long roleId : roleIds) {
				if (sysIconRole.getRoleId().equals(roleId)) {
					existsPermission = true;
					break;
				}
			}
			if (existsPermission) {
				existsPermission = false;
			} else {
				remove.add(sysIconRole);
			}
		}
		return remove;
	}

	private List<SysIconRole> getCreateList(Long iconId, Long[] roleIds, List<SysIconRole> exists) {
		List<SysIconRole> create = new LinkedList<SysIconRole>();
		if (roleIds.length == 0) {
			return create;
		}
		boolean existsPermission = false;
		for (Long roleId : roleIds) {
			if (!"".equals(roleId)) {
				for (SysIconRole sysIconRole : exists) {
					if (sysIconRole.getRoleId().equals(roleId)) {
						existsPermission = true;
						break;
					}
				}
				if (existsPermission) {
					existsPermission = false;
				} else {
					SysIconRole sysIconRole = new SysIconRole();
					sysIconRole.setIconId(iconId);
					sysIconRole.setRoleId(roleId);
					create.add(sysIconRole);
				}
			}

		}
		return create;
	}

	@Override
	public String getModule() {
		return "首页管理";
	}

}
