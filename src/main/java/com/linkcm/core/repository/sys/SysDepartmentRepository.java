package com.linkcm.core.repository.sys;

import com.linkcm.core.dao.BaseRepository;
import com.linkcm.core.entity.sys.SysDepartment;

public interface SysDepartmentRepository extends
		BaseRepository<SysDepartment, Long> {
	
	/**
	 * 通过部门编号获取部门信息
	 * 
	 * @param id
	 * @return SysUser
	 * 
	 * */
	public SysDepartment findById(Long id);
}
