package com.linkcm.core.service.sys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.linkcm.core.annotation.ErrorMessage;
import com.linkcm.core.dao.BaseRepository;
import com.linkcm.core.entity.sys.SysParam;
import com.linkcm.core.repository.sys.SysParamRepository;
import com.linkcm.core.service.CoreService;
import com.linkcm.core.service.ServiceException;
import com.linkcm.core.util.CoreUtils;

/**
 * 参数管理
 * 
 * @author ZJY
 * 
 */
@Component
@Transactional
public class SysParamService extends CoreService<SysParam, Long> {

	@Autowired
	private SysParamRepository sysParamRepository;

	@Override
	protected void beforeSave(SysParam entity, boolean operate) {
		if (isEntityUniqueById(entity.getId(), "paramName", entity.getParamName())) {
			throw new ServiceException("sys_parameter_nameExist");
		}
	}

	/**
	 * 获取指定的参数值
	 * @param X 范型参数，作为默认值，可以为数值、布尔、字符等类型，
	 * 
	 * */
	@SuppressWarnings("unchecked")
	@ErrorMessage("获取参数失败")
	public <X> X getParam(String paramName, X x) {
		SysParam param = sysParamRepository.findByParamName(paramName);
		return param == null ? x : (X) CoreUtils.convertStringToObject(param.getParamValue(), x.getClass());
	}

	@Override
	protected BaseRepository<SysParam, Long> getDao() {
		return sysParamRepository;
	}

	@Override
	public String getModule() {
		return "参数管理";
	}
}
