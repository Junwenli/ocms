package ${packageName}.entity;
<%@ taglib prefix="s" uri="/struts-tags" %><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><c:set var="entityName" value="${name}"/>
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;<s:if test="oracle">
import javax.persistence.SequenceGenerator;</s:if>
import javax.persistence.Table;<s:iterator value="entityCols" id="col"><s:if test="#col.enum">
import ${packageName}.entity.type.${entityName}${enumFieldName};</s:if></s:iterator>
import com.linkcm.core.annotation.Validation;

@Entity
@Table(name = "${table}")
public class ${name} {

	<s:iterator value="entityCols" id="col">
	<s:if test="#col.id">@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)</s:if>
	@Validation(name = "${col.desc}"<s:if test="#col.enum">,enumClass=${entityName}${enumFieldName}.class</s:if><s:else><s:if test='#col.type.equals("String")'>,max=${col.length}</s:if><s:elseif test="#col.id!=true">,min=${col.min},max=${col.max}L</s:elseif></s:else><s:if test="#col.nullable!=true&&#col.id!=true">,nullable=false</s:if>)
	@Column(name = "${col.name}")
	private ${col.type} ${fieldName};
	</s:iterator>
	<s:iterator value="entityCols" id="col">
	public ${col.type} get${methodName}() {
		return ${fieldName};
	}

	public void set${methodName}(${col.type} ${fieldName}) {
		this.${fieldName} = ${fieldName};
	}
	<s:if test="#col.enum">
	public String get${enumFieldName}Desc() {
		return ${entityName}${enumFieldName}.getDesc(${fieldName});
	}
	</s:if>
	</s:iterator>
}
<%@ page contentType="text/html;charset=UTF-8" %>