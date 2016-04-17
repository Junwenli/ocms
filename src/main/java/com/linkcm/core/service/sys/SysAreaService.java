package com.linkcm.core.service.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.linkcm.core.dao.BaseRepository;
import com.linkcm.core.entity.sys.SysArea;
import com.linkcm.core.repository.sys.SysAreaRepository;
import com.linkcm.core.service.CoreService;
import com.linkcm.core.service.ServiceException;


@Component
@Transactional
public class SysAreaService extends CoreService<SysArea, Long>{

	@Autowired
	private SysAreaRepository sysAreaRepository;
	
	@Override
	protected BaseRepository<SysArea, Long> getDao() {
		return sysAreaRepository;
	}

	@Override
	protected void beforeSave(SysArea entity, boolean operate) {
		if (isEntityUniqueById(entity.getId(), "areaCode", entity.getAreaCode())) {
			throw new ServiceException("sys_areaCode_codeExist");
		}
	}

	@Override
	public String getModule() {
		return "区域管理";
	}
	

}
