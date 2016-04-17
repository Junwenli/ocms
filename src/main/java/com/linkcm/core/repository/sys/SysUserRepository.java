package com.linkcm.core.repository.sys;

import java.util.List;

import org.jbpm.api.identity.User;
import org.springframework.data.jpa.repository.Query;

import com.linkcm.core.dao.BaseRepository;
import com.linkcm.core.entity.sys.SysUser;

public interface SysUserRepository extends BaseRepository<SysUser, Long> {

	/**
	 * 通过name获得用户
	 * 
	 * @param name
	 * @return SysUser
	 * 
	 * */
	public SysUser findByName(String name);

	/**
	 * 通过userCode获得用户
	 * 
	 * @param userCode
	 * @return SysUser
	 * 
	 * */
	public SysUser findByUserCode(String userCode);

	/**
	 * 通过id获得用户
	 * 
	 * @param id
	 * @return SysUser
	 * 
	 * */
	public SysUser findById(Long id);

	/**
	 * 根据用户ID和鉴权码查找用户
	 * 
	 * @param userId
	 *            用户ID
	 * @param oldPwd
	 *            鉴权码
	 * @return SysUser
	 */
	public SysUser findByIdAndAuthenticator(String userId, String oldPwd);

	/**
	 * 获得所有没有部门的用户
	 * 
	 * @return
	 */
	public List<SysUser> findByDeptId(Long deptId);

	/**
	 * 根据组Id获得所属用户列表
	 * 
	 * @param userId
	 * @return
	 */
	@Query("select user from SysRole r,SysMembership membership,SysUser user where r.id=membership.roleId and membership.userId=user.id and r.id=?")
	public List<User> findUserByGroupId(String groupId);

	/**
	 * 根据用户获取其他所有用户列表
	 * 
	 * @param userId
	 * @return
	 */
	@Query("select distinct new Map(d.id as id,d.userCode as userCode,d.name as name,d.email as email,d.mobile as mobile,d.status as status) from SysUser d "
			+ "where  d.id !=?1 and d.id not in "
			+ "(select distinct u.id from SysUser  u, SysMembership m,  SysPermission  p  where u.id = m.userId  and m.roleId = p.roleId and  p.permissionId  not in "
			+ "(select distinct p.permissionId  from SysUser   u, SysMembership   m, SysPermission p where u.id = m.userId  and m.roleId = p.roleId  and u.id = ?1))")
	public List<?> findByUserId(Long userId);
}
