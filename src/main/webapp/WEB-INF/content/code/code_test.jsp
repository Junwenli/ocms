package ${packageName}.service.${module};
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
<c:set var="entityDesc" value="${desc}"/>
import ${packageName}.entity.${name};
import com.linkcm.media.util.CoreUtils;
<c:set var="entityName" value="${name}"/>
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContextTest-repository.xml",
		"classpath:applicationContextTest.xml" })
public class ${name}ServiceTest implements ApplicationContextAware {

	@Autowired
	private ${name}Service ${name1}Service;

	private static ApplicationContext context;
	<s:iterator value="entityCols" id="col"><s:if test="#col.id==true">
	private static final ${type} id = <s:if test='#col.type.equals("String")'>"test_abc"</s:if><s:else>1</s:else>;
	</s:if></s:iterator>
	private ${name} entity;

	@Before
	public void setUp() {
		entity = createEntity();
		CoreUtils.executeSql("${name}.sql", context);
	}

	@After
	public void tearDown() {
		CoreUtils.executeSql("clear.sql", context);
	}

	/**
	 * 测试保存${desc}. 保存${desc}后，根据对应id查询获取实体不为空
	 * 
	 * */
	@Test
	public void save() {
		entity.setId(id);
		${name1}Service.save(entity, true);
		Assert.assertEquals(id, ${name1}Service.getEntity(id).getId());
	}

	/**
	 * 测试删除${desc} .保存${desc}后再删除，根据对应id查询获取实体为空
	 * 
	 * */
	@Test
	public void delete() {
		entity.setId(id);
		${name1}Service.save(entity, true);
		${name1}Service.delete(id);
		Assert.assertNull(${name1}Service.getEntity(id));
	}
	<s:iterator value="indexes" id="tableIndex"><s:if test='#tableIndex.columns.size==1&&#tableIndex.columns.get(0).id&&#tableIndex.columns.get(0).type.equals("String")'>
	/**
	 * 校验${entityDesc}主键唯一 .连续保存${entityDesc}，主键设相同值,，第二次保存抛出异常"${entityDesc}<s:property escape='false' value="#tableIndex.columns.get(0).desc"/>已存在"
	 * 
	 * */
	@Test
	public void check<s:iterator value="#tableIndex.columns" id="col"  status="colIndex"><s:property value="#col.methodName"/><s:if test="#colIndex.last!=true">And</s:if></s:iterator>Unique() {
		<s:iterator value="#tableIndex.columns" id="col">entity.set<s:property value="#col.methodName"/>(<s:if test='#col.type.equals("String")'>"unique_abc"</s:if><s:else>1</s:else>);
		</s:iterator>
		${name1}Service.save(entity, true);
		String exMessage = null;
		try {
			${entityName} duplicateEntity = createEntity();<s:iterator value="#tableIndex.columns" id="col">
			duplicateEntity.set${methodName}(<s:if test='#col.type.equals("String")'>"unique_abc"</s:if><s:else>1</s:else>);</s:iterator>
			${name1}Service.save(duplicateEntity, true);
		} catch (Exception e) {
			exMessage = e.getMessage();
		}
		Assert.assertEquals("${entityDesc}<s:property escape='false' value="#tableIndex.columns.get(0).desc"/>已存在", exMessage);
	}
	</s:if><s:else>
	/**
	 * 校验${entityDesc}<s:iterator value="#tableIndex.columns" id="col" status="colIndex">${desc}<s:if test="#colIndex.last!=true">,</s:if></s:iterator>唯一 .连续保存${entityDesc}，主键设不同值,<s:iterator value="#tableIndex.columns" id="col" status="colIndex">${desc}<s:if test="#colIndex.last!=true">,</s:if></s:iterator>属性设相同的值，第二次保存抛出异常"${entityDesc}<s:iterator value="#tableIndex.columns" id="col" status="colIndex">${desc}<s:if test="colIndex.last!=true">,</s:if></s:iterator>已存在"
	 * 
	 * */
	@Test
	public void check<s:iterator value="#tableIndex.columns" id="col" status="colIndex">${methodName}<s:if test="#colIndex.last!=true">And</s:if></s:iterator>Unique() {
		<s:iterator value="#tableIndex.columns" id="col">entity.set${methodName}(<s:if test='#col.type.equals("String")'>"unique_abc"</s:if><s:else>1</s:else>);
		</s:iterator>
		${name1}Service.save(entity, true);
		String exMessage = null;
		try {
			${entityName} duplicateEntity = createEntity();<s:iterator value="#tableIndex.columns" id="col"  status="colIndex"><s:if test="#colIndex.first==true">
			duplicateEntity.setId(<s:if test='#col.type.equals("String")'>"duplicate_abc"</s:if><s:else>1</s:else>);</s:if>
			duplicateEntity.set${methodName}<s:if test='#col.type.equals("String")'>"unique_abc"</s:if><s:else>1</s:else>);</s:iterator>
			${name1}Service.save(duplicateEntity, true);
		} catch (Exception e) {
			exMessage = e.getMessage();
		}
		Assert.assertEquals("${entityDesc}<s:iterator value="#tableIndex.columns" id="col" status="colIndex">${desc}<s:if test="#colIndex.last!=true">,</s:if></s:iterator>已存在", exMessage);
	}
	</s:else></s:iterator>
	private ${name} createEntity() {
		${name} entity = new ${name}();
		<s:iterator value="entityCols" id="col">
		entity.set${methodName}(<s:if test='#col.type.equals("String")'>"test_abc"</s:if><s:else>1</s:else>);</s:iterator>
		return entity;
	}

	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		${name}ServiceTest.context = context;
	}

}
