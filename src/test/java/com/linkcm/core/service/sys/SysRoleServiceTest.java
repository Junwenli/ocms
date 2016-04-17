package com.linkcm.core.service.sys;

import java.util.HashSet;
import java.util.Set;

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

import com.linkcm.core.entity.sys.SysRole;
import com.linkcm.core.entity.type.RoleStatus;
import com.linkcm.core.repository.sys.SysMembershipRepository;
import com.linkcm.core.util.CoreUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:applicationContextTest-repository.xml",
		"classpath:applicationContextTest.xml" })
public class SysRoleServiceTest implements ApplicationContextAware {

	@Autowired
	private SysRoleService sysRoleService;

	@Autowired
	private SysMembershipRepository sysMembershipRepository;

	private static ApplicationContext context;

	@Before
	public void setUp() {
		CoreUtils.executeSql("sys_role.sql", context);
	}

	@After
	public void tearDown() {
		CoreUtils.executeSql("clear.sql", context);
	}

	/**
	 * 测试保存角色. 保存角色后，根据对应id查询获取实体不为空
	 * 
	 * */
	@Test
	public void save() {
		SysRole entity = createEntity();
		sysRoleService.save(entity, true);
		Assert.assertEquals(entity.getId(),
				sysRoleService.getEntity(entity.getId()).getId());
	}

	/**
	 * 测试删除角色 .保存角色后再删除，根据对应id查询获取实体为空
	 * 
	 * */
	@Test
	public void delete() {
		SysRole entity = createEntity();
		sysRoleService.save(entity, true);
		Long id = entity.getId();
		sysRoleService.delete(id);
		Assert.assertNull(sysRoleService.getEntity(id));
	}

	/**
	 * 测试删除角色时把用户角色关系表对应的数据也删除. 保存角色后再对用户角色关系表插入两条数据，删除角色时这两条数据也随之删除
	 * 
	 * */
	@Test
	public void deleteMemberShip() {
		Assert.assertEquals(2, sysMembershipRepository.findByRoleId(1L).size());
		sysRoleService.delete(1L);
		Assert.assertEquals(0, sysMembershipRepository.findByRoleId(1L).size());
	}

	/**
	 * 校验角色名称唯一 .连续保存角色，主键设不同值，名称属性设为相同值，第二次保存抛出异常"角色代码已存在"
	 * 
	 * */
	@Test
	public void checkTiltleUnique() {
		SysRole entity = createEntity();
		entity.setTitle("unique_abc");
		sysRoleService.save(entity, true);
		String exMessage = null;
		try {
			SysRole duplicateEntity = createEntity();
			duplicateEntity.setTitle("unique_abc");
			sysRoleService.save(duplicateEntity, true);
		} catch (Exception e) {
			exMessage = e.getMessage();
		}
		Assert.assertEquals("sys_role_nameExist", exMessage);
	}

	/**
	 * 测试根据用户id获取授权角色.
	 * 向角色表插入test1,test2,test3三个角色，用户表插入user3,用户角色表插入对应数据，根据user3查询时返回三个授权角色
	 * 
	 * */
	@Test
	public void findGrantedAuthorityByUserId() {
		Set<GrantedAuthority> set = new HashSet<GrantedAuthority>();
		set.add(new GrantedAuthorityImpl("test3"));
		set.add(new GrantedAuthorityImpl("test2"));
		set.add(new GrantedAuthorityImpl("test1"));
		Assert.assertEquals(set, sysRoleService.findGrantedAuthorityByUserId(
				3L, new HashSet<GrantedAuthority>()));
	}

	private SysRole createEntity() {
		SysRole entity = new SysRole();
		entity.setDescription("test_abc");
		entity.setStatus(RoleStatus.normal.value);
		entity.setTitle("test_abc");
		return entity;
	}

	@Override
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		SysRoleServiceTest.context = context;
	}

}
