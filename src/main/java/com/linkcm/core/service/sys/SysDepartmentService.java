package com.linkcm.core.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.linkcm.core.dao.BaseRepository;
import com.linkcm.core.entity.sys.SysDepartment;
import com.linkcm.core.entity.sys.SysUser;
import com.linkcm.core.repository.sys.SysDepartmentRepository;
import com.linkcm.core.repository.sys.SysUserRepository;
import com.linkcm.core.service.CoreService;
import com.linkcm.core.service.ServiceException;

@Component
@Transactional
public class SysDepartmentService extends CoreService<SysDepartment, Long> {

	@Autowired
	private SysDepartmentRepository sysDepartmentRepository;

	@Autowired
	private SysUserRepository sysUserRepository;

	@Autowired
	private SysUserService sysUserMgtService;

	@Override
	protected BaseRepository<SysDepartment, Long> getDao() {
		return sysDepartmentRepository;
	}

	protected void beforeSave(SysDepartment entity, boolean operate) {
		if (isEntityUniqueById(entity.getId(), "deptCode", entity.getDeptCode())) {
			throw new ServiceException("sys_department_codeExist");
		}
		if (isEntityUniqueById(entity.getId(), "title", entity.getTitle())) {
			throw new ServiceException("sys_department_nameExist");
		}
	}

	protected void beforeDelete(SysDepartment entity) {
		List<SysUser> list = sysUserRepository.findByDeptId(entity.getId());
		for (SysUser user : list) {
			user.setDeptId(null);
			sysUserMgtService.save(user, false);
		}
	}

	public int getUseRate() {
		int useRate = 100;
		List<SysDepartment> depts = findAll();
		for (SysDepartment entity : depts) {
			useRate = useRate - entity.getResRate();
		}
		return useRate;
	}

	@Override
	public String getModule() {
		return "部门管理";
	}
}
