package ${packageName}.service.${module};
<%@ taglib prefix="s" uri="/struts-tags" %>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.linkcm.core.dao.BaseRepository;
import ${packageName}.entity.${name};
import ${packageName}.repository.${module}.${name}Repository;
import com.linkcm.core.service.CoreService;<s:if test="beforeSave==true">
import com.linkcm.core.service.ServiceException;</s:if>

/**
 * ${desc}管理
 * 
 * 
 */
@Component
@Transactional
public class ${name}Service extends CoreService<${name}, ${idType}> {

	@Autowired
	private ${name}Repository ${name1}Repository;

	@Override
	protected BaseRepository<${name}, ${idType}> getDao() {
		return ${name1}Repository;
	}
	
	@Override
	public String getModule() {
		return "${desc}管理";
	}
	<s:if test="beforeSave==true">
	protected void beforeSave(${name} entity, boolean operate) {<s:iterator value="indexes" id="tableIndex"> <s:iterator value="#tableIndex.columns" id="col" status="colIndex"><s:if test='#col.id&&#col.type.equals("String")'>
		if (operate && exists(entity.get${methodName}())) {
			throw new ServiceException("${entityDesc}${col.desc}已存在");
		}</s:if></s:iterator></s:iterator><s:iterator value="indexes" id="tableIndex"><s:if test='#tableIndex.columns.get(0).id!=true'>
		if (isEntityUniqueById(entity.getId(), <s:iterator value="#tableIndex.columns" id="col" status="colIndex">"${fieldName}", entity.get${methodName}()<s:if test="#colIndex.last!=true">,</s:if></s:iterator>)) {
			throw new ServiceException("${entityDesc}<s:iterator value="#tableIndex.columns" id="col" status="colIndex">${col.desc}<s:if test="#colIndex.last!=true">,</s:if></s:iterator>已存在");
		}</s:if></s:iterator>
	}</s:if>
}
<%@ page contentType="text/html;charset=UTF-8" %>