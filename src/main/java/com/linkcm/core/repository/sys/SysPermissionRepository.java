package com.linkcm.core.repository.sys;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.linkcm.core.dao.BaseRepository;
import com.linkcm.core.entity.sys.SysPermission;

public interface SysPermissionRepository extends BaseRepository<SysPermission, Long> {

	/**
	 * 通过角色ID获得权限列表
	 * 
	 * @param roleId
	 * @return List<SysPermission>
	 * 
	 * */
	public List<SysPermission> findByRoleId(Long roleId);

	@Query("select new Map(r.id as permissionId,r.title as title,r.permissionType as resourceType,r._parentId as _parentId,r.url as url,(select 1 from SysPermission p where p.permissionId=r.id and p.roleId=?) as checked) from SysResource r")
	public List<?> findVoByRoleId(Long roleId);

	/**
	 * 根据权限ID活动角色权限映射
	 * 
	 * @author ChunPIG
	 * @param permissionId
	 * @return
	 */
	public List<SysPermission> findByPermissionId(Long permissionId);
}
