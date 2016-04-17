package com.linkcm.core.repository.sys;

import com.linkcm.core.dao.BaseRepository;
import com.linkcm.core.entity.sys.SysParam;

public interface SysParamRepository extends BaseRepository<SysParam, Long> {

	public SysParam findByParamName(String paramName);
	
}
