package com.linkcm.core.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import com.linkcm.core.annotation.ErrorMessage;
import com.linkcm.core.annotation.Validate;
import com.linkcm.core.dao.BaseRepository;
import com.linkcm.core.entity.sys.QueryParam;
import com.linkcm.core.util.EasyUiUtils;
import com.linkcm.core.web.EasyUiDataGrid;

@Transactional
public abstract class CoreService<T, PK extends Serializable> extends ServiceSupport {

	public static final String DELETE_FAILURE = "删除失败";

	public static final String LIST_FAILURE = "获取列表失败";

	public static final String SAVE_FAILURE = "保存失败";

	/**
	 * 批量保存
	 * 
	 * */
	@ErrorMessage(SAVE_FAILURE)
	public void save(@Validate T entity) {
		save(entity, false, true);
	}

	/**
	 * 批量保存
	 * 
	 * */
	@ErrorMessage(SAVE_FAILURE)
	public void save(@Validate T entity, boolean operate) {
		save(entity, true, operate);
	}

	/**
	 * 批量保存实体
	 * 
	 * */
	@ErrorMessage(SAVE_FAILURE)
	public void save(T[] entitys) {
		for (T entity : entitys) {
			save(entity, false, true);
		}
	}

	/**
	 * 批量保存实体
	 * 
	 * */
	@ErrorMessage(SAVE_FAILURE)
	public void save(Collection<T> entitys) {
		for (T entity : entitys) {
			save(entity, false, true);
		}
	}

	/**
	 * 批量保存实体
	 * 
	 * */
	@ErrorMessage(SAVE_FAILURE)
	public void save(Collection<T> entitys, boolean operate) {
		for (T entity : entitys) {
			save(entity, true, operate);
		}
	}

	/**
	 * 批量保存实体
	 * 
	 * */
	@ErrorMessage(SAVE_FAILURE)
	public void save(T[] entitys, boolean operate) {
		for (T entity : entitys) {
			save(entity, true, operate);
		}
	}

	/**
	 * 当ID需要人工录入时，根据操作保存实体，type为true,operate为true时是新增，false为更新
	 * 
	 * */
	private void save(T entity, boolean type, boolean operate) {

		if (type) {
			beforeSave(entity, operate);
		} else {
			beforeSave(entity);
		}
		getDao().save(entity);

	}

	protected void beforeSave(T t, boolean operate) {

	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	@ErrorMessage(DELETE_FAILURE)
	public void delete(PK id) {
			beforeDelete(this.getEntity(id));
			getDao().delete(id);
	}

	/**
	 * 删除
	 * 
	 */
	@ErrorMessage(DELETE_FAILURE)
	public void delete(T[] entitys) {
		for (T entity : entitys) {
			delete(entity);
		}
	}

	/**
	 * 删除
	 * 
	 */
	@ErrorMessage(DELETE_FAILURE)
	public void delete(Collection<T> entitys) {
		for (T entity : entitys) {
			delete(entity);
		}
	}

	/**
	 * 删除
	 * 
	 */
	@ErrorMessage(DELETE_FAILURE)
	public void delete(T entity) {
			beforeDelete(entity);
			getDao().delete(entity);
	}

	protected void beforeDelete(T t) {

	}

	/**
	 * 判断实体是否被其它表使用
	 * 
	 * */
	public boolean isEndow(Class<?> entityClz, String property, Object value) {
		return getDao().isEndow(entityClz, property, value);
	}

	/**
	 * 删除
	 * 
	 * @param ids
	 */
	@ErrorMessage(DELETE_FAILURE)
	public void delete(PK ids[]) {
		for (PK id : ids) {
			delete(id);
		}

	}

	/**
	 * 分页查询多个角色
	 * 
	 */
	@ErrorMessage(LIST_FAILURE)
	public EasyUiDataGrid searchForAjax(final Pageable pageable, final List<QueryParam> filters) {
		Page<T> pageEntity = getDao().findAll(pageable, filters);
		return EasyUiUtils.getEasyUiDataGrid(pageEntity);
	}

	/**
	 * 查询单个
	 * 
	 * @param id
	 * @return
	 */
	public T getEntity(PK id) {
		return getDao().findOne(id);
	}

	/**
	 * 分页查询多个
	 * 
	 */
	@ErrorMessage(LIST_FAILURE)
	public Page<T> search(final Pageable pageable, final List<QueryParam> filters) {
		return getDao().findAll(pageable, filters);
	}

	/**
	 * 获取所有实体
	 * 
	 */
	@ErrorMessage(LIST_FAILURE)
	public List<T> findAll() {
		return getDao().findAll();
	}

	/**
	 * 获取所有实体
	 * 
	 */
	@ErrorMessage(LIST_FAILURE)
	public List<T> findAll(Sort sort) {
		return getDao().findAll(sort);
	}

	protected void beforeSave(T entity) {
	}

	/**
	 * 检查是否唯一
	 * 
	 */
	public boolean isEntityUnique(Object... paramsArray) {
		return getDao().isEntityUniqueById(null, paramsArray);
	}

	/**
	 * 检查是否唯一
	 * 
	 */
	public boolean isEntityUniqueById(PK idValue, Object... paramsArray) {
		return getDao().isEntityUniqueById(idValue, paramsArray);
	}

	public boolean exists(PK idValue) {
		return getDao().exists(idValue);
	}

	protected abstract BaseRepository<T, PK> getDao();

	public String getModule() {
		return "";
	}
}
