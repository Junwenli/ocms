package com.linkcm.core.service.sys;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.linkcm.core.annotation.ErrorMessage;
import com.linkcm.core.dao.BaseRepository;
import com.linkcm.core.entity.sys.SysMembership;
import com.linkcm.core.entity.sys.SysRole;
import com.linkcm.core.entity.type.RoleStatus;
import com.linkcm.core.repository.sys.SysMembershipRepository;
import com.linkcm.core.repository.sys.SysRoleRepository;
import com.linkcm.core.service.CoreService;
import com.linkcm.core.service.ServiceException;

/**
 * 角色管理
 * 
 * @author antking
 * 
 */
@Component
@Transactional
public class SysRoleService extends CoreService<SysRole, Long> {

	@Autowired
	private SysRoleRepository sysRoleRepository;

	@Autowired
	private SysMembershipRepository sysMembershipRepository;

	@Autowired
	private SysMembershipService sysMembershipService;

	@Override
	protected void beforeSave(SysRole entity, boolean operate) {
		if (isEntityUniqueById(entity.getId(), "roleCode", entity.getRoleCode())) {
			throw new ServiceException("sys_role_codeExist");
		}
		if (isEntityUniqueById(entity.getId(), "title", entity.getTitle())) {
			throw new ServiceException("sys_role_nameExist");
		}
	}

	protected void beforeDelete(SysRole entity) {
		if ("adminRole".equals(entity.getRoleCode())) {
			throw new ServiceException("sys_role_deleteAdminRole");
		}
		List<SysMembership> list = sysMembershipRepository.findByRoleId(entity.getId());
		sysMembershipService.delete(list);
	}

	@ErrorMessage("获取角色失败")
	public List<SysRole> findByUsername(String username) {
		return sysRoleRepository.findByUsername(username);
	}

	@ErrorMessage("获取角色失败")
	public List<SysRole> findByUserId(Long userId) {
		return sysRoleRepository.findByUserId(userId, RoleStatus.normal.value);
	}

	@ErrorMessage("获取角色失败")
	public List<SysRole> findByUrl(String url) {
		return sysRoleRepository.findByUrl(url, RoleStatus.normal.value);
	}

	@ErrorMessage("获取权限失败")
	public Set<GrantedAuthority> findGrantedAuthorityByUsername(String username) {
		List<SysRole> list = sysRoleRepository.findByUsername(username);
		Set<GrantedAuthority> set = new HashSet<GrantedAuthority>();
		for (SysRole role : list) {
			set.add(new GrantedAuthorityImpl(role.getTitle()));
		}
		return set;
	}

	@ErrorMessage("获取权限失败")
	public Set<GrantedAuthority> findGrantedAuthorityByUserId(Long userId, Set<GrantedAuthority> grantedAuths) {
		List<SysRole> list = sysRoleRepository.findByUserId(userId, RoleStatus.normal.value);
		for (SysRole role : list) {
			grantedAuths.add(new GrantedAuthorityImpl(role.getRoleCode().toString()));
		}
		return grantedAuths;
	}

	@Override
	protected BaseRepository<SysRole, Long> getDao() {
		return sysRoleRepository;
	}

	@Override
	public String getModule() {
		return "角色管理";
	}

}
