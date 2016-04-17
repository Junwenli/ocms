package com.linkcm.core.repository.sys;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.linkcm.core.dao.BaseRepository;
import com.linkcm.core.entity.sys.SysIcon;

public interface SysIconRepository extends BaseRepository<SysIcon, Long> {

	@Query("select new Map(role.id as roleId,role.title as title,role.description as description, role.status as status, (select 1 from SysIconRole iconRole where iconRole.roleId=role.id and iconRole.iconId=?) as checked) from SysRole role")
	public List<?> findRoleByIconId(Long iconId);
}
