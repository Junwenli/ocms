package ${packageName}.web.${module};
<%@ taglib prefix="s" uri="/struts-tags" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><c:set var="entityName" value="${name}"/><c:set var="mapFlag" value="false"/>
<s:iterator value="entityCols" id="col"><s:if test="#col.enum"><c:if test="${mapFlag==false}">import java.util.Map;<c:set var="mapFlag" value="true"/></c:if></s:if></s:iterator>
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import ${packageName}.service.${module}.${name}Service;
import  ${packageName}.entity.${name};
import com.linkcm.core.service.CoreService;
import com.linkcm.core.web.CoreAction;<s:iterator value="entityCols" id="col"><s:if test="#col.enum">
import ${packageName}.entity.type.${entityName}${enumFieldName};</s:if></s:iterator>
/**
 * ${desc}管理
 * 
 */
@Namespace("/${lowerCaseName}")
public class ${name}Action extends CoreAction<${name}, ${idType}> {

	private static final long serialVersionUID = 1L;

	@Autowired
	private ${name}Service ${name1}Service;

	@Override
	public CoreService<${name}, ${idType}> getService() {
		return ${name1}Service;
	}
	<s:iterator value="entityCols" id="col"><s:if test="#col.enum">
	public Map<Object, Object> get${enumFieldName}Map() {
		return getChooseMap(${entityName}${enumFieldName}.values());
	}</s:if></s:iterator>
}
<%@ page contentType="text/html;charset=UTF-8" %>