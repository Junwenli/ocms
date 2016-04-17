package com.linkcm.core.repository.sys;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.linkcm.core.dao.BaseRepository;
import com.linkcm.core.entity.sys.SysIcon;
import com.linkcm.core.entity.sys.SysIconRole;

public interface SysIconRoleRepository extends BaseRepository<SysIconRole, Long> {

	public List<SysIconRole> findByIconId(Long iconId);

	@Query("select distinct icon from SysIconRole iconRole,SysIcon icon,SysMembership m,SysUser user where iconRole.iconId=icon.id and iconRole.roleId=m.roleId and m.userId=user.id and user.id=? order by icon.id")
	public List<SysIcon> findByUserId(Long userId);
}
