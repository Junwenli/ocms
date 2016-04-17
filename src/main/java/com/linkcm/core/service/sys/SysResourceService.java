package com.linkcm.core.service.sys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.linkcm.core.annotation.ErrorMessage;
import com.linkcm.core.dao.BaseRepository;
import com.linkcm.core.entity.sys.SysPermission;
import com.linkcm.core.entity.sys.SysResource;
import com.linkcm.core.entity.type.ResourceType;
import com.linkcm.core.entity.type.RoleStatus;
import com.linkcm.core.repository.sys.SysPermissionRepository;
import com.linkcm.core.repository.sys.SysResourceRepository;
import com.linkcm.core.service.CoreService;
import com.linkcm.core.service.ServiceException;
import com.linkcm.core.util.I18nUtils;
import com.linkcm.core.util.PropertyUtils;

@Component
@Transactional
public class SysResourceService extends CoreService<SysResource, Long> {

	@Autowired
	private SysResourceRepository sysResourceRepository;

	@Autowired
	private SysPermissionRepository sysPermissionRepository;

	@Autowired
	private SysPermissionService sysPermissionService;

	public final static String APP_ID = PropertyUtils.get("app.id");

	/**
	 * 保存权限
	 * 
	 * @param entity
	 */
	protected void beforeSave(SysResource entity, boolean operate) {
		if (isEntityUniqueById(entity.getId(), "permissionCode", entity.getPermissionCode())) {
			throw new ServiceException("sys_resource_codeExist");
		}
		List<SysResource> sysResource = sysResourceRepository.findByTitle(entity.getTitle());

		if (entity.getPermissionType().equals(ResourceType.SYSTEM.value)) {
			entity.setSystemId(entity.getPermissionCode());
		} else {
			SysResource parent = sysResourceRepository.findOne(entity.get_parentId());
			entity.setSystemId(parent.getSystemId());
		}

		for (int i = 0; i < sysResource.size(); i++) {
			if (!"".equals(sysResource.get(i).get_parentId())
					&& entity.get_parentId().equals(sysResource.get(i).get_parentId())) {
				if (!entity.getId().equals(sysResource.get(i).getId())) {
					throw new ServiceException("sys_resource_nameExist");
				}
			}
		}
	}

	/**
	 * 删除权限
	 * 
	 * @author ChunPIG
	 * @param entity
	 */
	@Override
	protected void beforeDelete(SysResource entity) {
		// 查询出所有父类资源ID为当前要删除的资源ID的子资源
		List<SysResource> subList = sysResourceRepository.findBy_parentId(entity.getId());

		// 将所有子资源的父类ID指定当前要删除的资源的父资源ID
		for (SysResource sub : subList) {
			sub.set_parentId(entity.get_parentId());
			save(sub);
		}
		// 获得已关联到当前要删除的资源的角色权限映射实体集合
		List<SysPermission> spList = sysPermissionRepository.findByPermissionId(entity.getId());
		sysPermissionService.delete(spList);
	}

	/**
	 * 根据资源编码获取类型map
	 * 
	 * */
	@ErrorMessage("sys_resource_saveException")
	public Map<Object, Object> getTypeMap(Long permissionId, boolean operate) {
		Map<Object, Object> result = new HashMap<Object, Object>();
		// 如果Operate：true则新增，false则修改
		if (operate) {
			// 新增操作
			// 首先判断是否传入了ID
			// 如ID为传，则直接是系统
			if (permissionId == null) {
				result.put(ResourceType.SYSTEM.value, ResourceType.SYSTEM.getDesc());
			} else {
				SysResource resource = sysResourceRepository.findOne(permissionId);
				// 如系统：菜单，功能
				if (resource.getPermissionType() == ResourceType.SYSTEM.value) {
					result.put(ResourceType.MENU.value, ResourceType.MENU.getDesc());
					result.put(ResourceType.FUNCTION.value, ResourceType.FUNCTION.getDesc());
				}
				// 如菜单：菜单，功能
				if (resource.getPermissionType() == ResourceType.MENU.value) {
					result.put(ResourceType.MENU.value, ResourceType.MENU.getDesc());
					result.put(ResourceType.FUNCTION.value, ResourceType.FUNCTION.getDesc());
				}
				// 如功能：功能
				if (resource.getPermissionType() == ResourceType.FUNCTION.value) {
					result.put(ResourceType.FUNCTION.value, ResourceType.FUNCTION.getDesc());
				}
			}
		} else {
			// 修改操作
			// 获得要修改的资源实体
			SysResource resource = sysResourceRepository.findOne(permissionId);

			// 获得被修改的资源的所有子资源
			List<SysResource> subList = sysResourceRepository.findBy_parentId(permissionId);

			if (subList.size() > 0) {
				// 有子类
				List<Integer> typeList = new ArrayList<Integer>();
				for (SysResource entity : subList) {
					typeList.add(entity.getPermissionType());
				}
				int max = typeList.get(0);
				for (int i = 0; i < typeList.size(); i++) {
					if (typeList.get(i) > max) {
						max = typeList.get(i);
					}
				}
				// 如系统：系统
				if (resource.getPermissionType() == ResourceType.SYSTEM.value) {
					result.put(ResourceType.SYSTEM.value, ResourceType.SYSTEM.getDesc());
				}
				// 如菜单：菜单；再判断其子类是否全部是功能，如是则可改成功能，否则只能是菜单
				if (resource.getPermissionType() == ResourceType.MENU.value) {
					result.put(ResourceType.MENU.value, ResourceType.MENU.getDesc());
					if (max == ResourceType.FUNCTION.value) {
						result.put(ResourceType.FUNCTION.value, ResourceType.FUNCTION.getDesc());
					}
				}
				// 如功能：功能，再判断其子类是否有，如有
				if (resource.getPermissionType() == ResourceType.FUNCTION.value) {
					result.put(ResourceType.FUNCTION.value, ResourceType.FUNCTION.getDesc());
					if (max == ResourceType.FUNCTION.value) {
						result.put(ResourceType.MENU.value, ResourceType.MENU.getDesc());
					}
				}
			} else {
				// 无子类
				// 则如本身资源类型是系统：显示系统
				// 否则显示菜单和功能
				if (resource.getPermissionType() == ResourceType.SYSTEM.value) {
					result.put(ResourceType.SYSTEM.value, ResourceType.SYSTEM.getDesc());
				} else {
					result.put(ResourceType.MENU.value, ResourceType.MENU.getDesc());
					result.put(ResourceType.FUNCTION.value, ResourceType.FUNCTION.getDesc());
				}
			}
		}

		return result;
	}

	@ErrorMessage("sys_resource_getResourceException")
	public List<SysResource> findBySystemId() {
		return sysResourceRepository.findBySystemId(APP_ID);
	}

	@ErrorMessage("获取菜单失败")
	public List<?> findMenuByUsername(String username) {
		// 使用Spring Data获得数据
		return sysResourceRepository.findMenuByUsername(username, ResourceType.SYSTEM.value, ResourceType.MENU.value);
	}

	@ErrorMessage("sys_resource_getMenuFail")
	public List<Map<String, Object>> findMenuByUserId(Long userId) {
		// 使用Spring Data获得数据
		List<Map<String, Object>> giveMap = sysResourceRepository.findMenuByGiveUserIdAndSystem(userId,
				ResourceType.SYSTEM.value, ResourceType.MENU.value, APP_ID);
		List<Map<String, Object>> result = sysResourceRepository.findMenuByUserIdAndSystem(userId,
				ResourceType.SYSTEM.value, ResourceType.MENU.value, APP_ID, RoleStatus.normal.value);
		result.addAll(giveMap);
		for (Map<String, Object> map : result) {
			String code = map.get("code").toString();
			String name = I18nUtils.getMassage(code);
			if (!code.equals(name)) {
				map.put("name", name);
			}

		}
		return result;
	}

	@ErrorMessage("findOperations_failure")
	public List<Map<String, Object>> findFunctionByUserId(Long userId) {
		List<Map<String, Object>> giveMap = sysResourceRepository.findFunctionByGiveUserId(userId,
				ResourceType.FUNCTION.value);
		List<Map<String, Object>> result = sysResourceRepository.findFunctionByUserId(userId,
				ResourceType.FUNCTION.value, RoleStatus.normal.value);
		result.addAll(giveMap);
		for (Map<String, Object> map : result) {
			String code = map.get("code").toString();
			String name = I18nUtils.getMassage(code);
			if (!code.equals(name)) {
				map.put("title", name);
			}

		}
		return result;
	}

	@ErrorMessage("findSystemOperations_failure")
	public List<Map<String, Object>> findSystemByUserId(Long userId) {
		List<Map<String, Object>> giveMap = sysResourceRepository.findFunctionByGiveUserId(userId,
				ResourceType.SYSTEM.value);
		List<Map<String, Object>> map = sysResourceRepository.findFunctionByUserId(userId, ResourceType.SYSTEM.value,
				RoleStatus.normal.value);
		map.addAll(giveMap);
		return map;
	}

	@Override
	protected BaseRepository<SysResource, Long> getDao() {
		return sysResourceRepository;
	}

	@Override
	public String getModule() {
		return "权限管理";
	}
}
