package com.linkcm.core.repository.sys;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;

import com.linkcm.core.dao.BaseRepository;
import com.linkcm.core.entity.sys.SysGivepermission;

public interface SysGivePermissionRepository extends BaseRepository<SysGivepermission, Long> {

	/**
	 * 通过用户ID获得权限列表
	 * 
	 * @param userId
	 * @return List<SysGivepermission>
	 * 
	 * */
	public List<SysGivepermission> findByUserId(Long userId);

	/**
	 * 通过用户ID获得权限列表
	 * 
	 * @param userId
	 * @return List<SysResource>
	 * 
	 * */
	@Query("select distinct new Map(resource.id as permissionId)  "
			+ "from SysMembership membership,SysUser user,SysPermission p,SysResource resource where   resource.id=p.permissionId and p.roleId=membership.roleId and membership.userId=user.id and user.id=?")
	public List<Map<String, Object>> findResourceByUserId(Long userId);

	/**
	 * 通过url获得权限列表
	 * 
	 * @param url
	 * @return List<v>
	 * 
	 * */
	@Query("select p from SysGivepermission p,SysResource resource where  p.permissionId=resource.id and resource.url=?")
	public List<SysGivepermission> findByUrl(String url);

	@Query("select distinct new Map(resource.id as permissionId,resource.title as title,resource.permissionType as resourceType,resource._parentId as _parentId,resource.url as url,(select 1 from SysGivepermission p where p.permissionId=resource.id and p.userId=?1) as checked)"
			+ " from SysMembership membership,SysUser user,SysPermission p,SysResource resource where   resource.id=p.permissionId and p.roleId=membership.roleId and membership.userId=user.id and user.id=?2")
	public List<Map<String, Object>> findVoByUserId(Long userId, Long userIdParent);

	/**
	 * 根据权限ID活动角色权限映射
	 * 
	 * @author ChunPIG
	 * @param permissionId
	 * @return
	 */
	public List<SysGivepermission> findByPermissionId(Long permissionId);
}
