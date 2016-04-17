package com.linkcm.core.repository.sys;

import java.util.List;

import org.jbpm.api.identity.Group;
import org.springframework.data.jpa.repository.Query;

import com.linkcm.core.dao.BaseRepository;
import com.linkcm.core.entity.sys.SysRole;

public interface SysRoleRepository extends BaseRepository<SysRole, Long>,SysRoleRepositoryCustom {

	/**
	 * 通过用户名获得角色列表
	 * 
	 * @param username
	 * @return List<SysRole>
	 * 
	 * */
	@Query("select r from SysRole r,SysMembership membership,SysUser user where r.id=membership.roleId and membership.userId=user.id and user.name=?")
	public List<SysRole> findByUsername(String username);
	
	/**
	 * 通过用户名获得角色列表
	 * 
	 * @param username
	 * @return List<SysRole>
	 * 
	 * */
	@Query("select r from SysRole r,SysMembership membership,SysUser user where r.id=membership.roleId and membership.userId=user.id and user.id=?1 and r.status=?2")
	public List<SysRole> findByUserId(Long userId,int roleStatus);

	/**
	 * 通过url获得角色列表
	 * 
	 * @param url
	 * @return List<SysRole>
	 * 
	 * */
	@Query("select role from SysRole role,SysPermission p,SysResource resource where role.id=p.roleId and p.permissionId=resource.id and resource.url=?1 and role.status=?2")
	public List<SysRole> findByUrl(String url,int roleStatus);
	
	/**
	 * 通过id获得角色
	 * 
	 * @param id
	 * @return SysUser
	 * 
	 * */
	public SysRole findById(Long id);
	
	/**
	 * 通过用户Id获得组(角色)列表
	 * 
	 * @param username
	 * @return List<SysRole>
	 * 
	 * */
	@Query("select r from SysRole r,SysMembership membership,SysUser user where r.id=membership.roleId and membership.userId=user.id and user.id=?")
	public List<Group> findGroupByUserId(Long userId);
	
	/**
	 * 通过用户Id、角色Id获得组(角色)列表
	 * 
	 * @param username
	 * @return List<SysRole>
	 * 
	 * */
	@Query("select r from SysRole r,SysMembership membership,SysUser user where r.id=membership.roleId and membership.userId=user.id and user.id=? and r.id=?")
	public List<Group> findGroupByUserIdGroupId(Long userId,String groupId);

}
