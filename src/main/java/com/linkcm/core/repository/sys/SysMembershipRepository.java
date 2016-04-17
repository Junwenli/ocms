package com.linkcm.core.repository.sys;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.linkcm.core.dao.BaseRepository;
import com.linkcm.core.entity.sys.SysMembership;

public interface SysMembershipRepository extends BaseRepository<SysMembership, Long> {

	/**
	 * 通过用户ID获得用户与角色关联列表
	 * 
	 * @param userId
	 * @return List<SysMembership>
	 * 
	 * */
	public List<SysMembership> findByUserId(Long userId);

	/**
	 * 通过角色ID获得用户与角色关联列表
	 * 
	 * @param roleId
	 * @return List<SysMembership>
	 * 
	 * */
	public List<SysMembership> findByRoleId(Long roleId);

	@Query("select new Map(r.id as roleId,r.title as title,r.description as description,r.status as status,(select 1 from SysMembership m where m.roleId=r.id and m.userId=?) as checked) from SysRole r")
	public List<?> findVoByRoleId(Long userId);
}
