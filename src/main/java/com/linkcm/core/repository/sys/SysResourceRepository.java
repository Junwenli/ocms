package com.linkcm.core.repository.sys;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;

import com.linkcm.core.dao.BaseRepository;
import com.linkcm.core.entity.sys.SysResource;

/**
 * @Desc TODO
 * @author ChunPIG
 * @date 2012-2-1
 * @version 1.0
 */
public interface SysResourceRepository extends BaseRepository<SysResource, Long> {

	public List<SysResource> findBy_parentId(Long parentId);

	/**
	 * 根据用户属于的角色查找相应的菜单
	 * 
	 * @param username
	 * @param systemValue
	 * @param menuValue
	 * @return
	 */
	@Query("select new Map(r.id as id,r._parentId as pId,r.url as page,r.title as name, 'true' as open) from SysResource r ,SysPermission p,SysMembership m,SysUser u where r.id=p.permissionId and p.roleId=m.roleId and m.userId = u.id and u.name=?1 and (r.permissionType=?2 or r.permissionType=?3) order by r.id")
	public List<?> findMenuByUsername(String username, int systemValue, int menuValue);

	/**
	 * 根据用户属于的角色查找相应的菜单
	 * 
	 * @param username
	 * @param systemValue
	 * @param menuValue
	 * @return
	 */
	@Query("select distinct new Map(r.id as id,r._parentId as pId,r.url as page,r.permissionCode as code,r.title as name, 'true' as open) from SysResource r ,SysPermission p,SysMembership m,SysUser u,SysRole role  where r.id=p.permissionId and p.roleId=m.roleId  and m.userId = u.id and u.id=?1 and (r.permissionType=?2 or r.permissionType=?3) and r.systemId=?4  and role.id=m.roleId and role.status=?5 order by r.id")
	public List<Map<String, Object>> findMenuByUserIdAndSystem(Long userId, int systemValue, int menuValue,
			String systemId, int roleStatus);

	/**
	 * 根据用户属于的角色查找相应的菜单
	 * 
	 * @param username
	 * @param systemValue
	 * @param menuValue
	 * @return
	 */
	@Query("select distinct new Map(r.id as id,r._parentId as pId,r.url as page,r.permissionCode as code,r.title as name, 'true' as open) from SysResource r ,SysGivepermission p,SysUser u where r.id=p.permissionId and  p.userId = u.id and u.id=?1 and (r.permissionType=?2 or r.permissionType=?3) and r.systemId=?4 order by r.id")
	public List<Map<String, Object>> findMenuByGiveUserIdAndSystem(Long userId, int systemValue, int menuValue,
			String systemId);

	/**
	 * 根据用户查找相应的功能
	 * 
	 * @param username
	 * @param functionValue
	 * @return
	 */
	@Query("select distinct new Map(r.id as id,r.permissionCode as code,r.title as title) from SysResource r ,SysPermission p,SysMembership m,SysUser u, SysRole role where r.id=p.permissionId and p.roleId=m.roleId and m.userId = u.id and role.id=m.roleId and role.status=?3 and u.id=?1 and r.permissionType=?2  order by r.id ")
	public List<Map<String, Object>> findFunctionByUserId(Long userId, int functionValue, int roleStatus);

	/**
	 * 根据用户查找相应的功能
	 * 
	 * @param username
	 * @param functionValue
	 * @return
	 */
	@Query("select distinct new Map(r.id as id,r.permissionCode as code,r.title as title) from SysResource r ,SysGivepermission p,SysUser u where r.id=p.permissionId and  p.userId = u.id and u.id=?1 and r.permissionType=?2  order by r.id ")
	public List<Map<String, Object>> findFunctionByGiveUserId(Long userId, int functionValue);

	public List<SysResource> findByTitle(String title);

	public List<SysResource> findBySystemId(String systemId);
}
