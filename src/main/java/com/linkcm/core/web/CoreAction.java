package com.linkcm.core.web;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.linkcm.core.annotation.WebRequest;
import com.linkcm.core.annotation.WebRequest.RequestType;
import com.linkcm.core.entity.sys.QueryParam;
import com.linkcm.core.service.CoreService;
import com.linkcm.core.service.ServiceException;
import com.linkcm.core.util.EasyUiUtils;
import com.linkcm.core.util.JspUtils;
import com.linkcm.core.util.LoggerUtils;
import com.linkcm.core.util.MessageUtils;
import com.linkcm.core.util.ServletUtils;
import com.linkcm.core.util.WebUtils;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public abstract class CoreAction<T, PK extends Serializable> extends ActionSupport implements ModelDriven<T>,
		Preparable {

	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerUtils.getLogger(getClass());

	private T entity;

	private String sort;

	private String order;

	private PK id;

	private String ids;

	private String orParam;

	// 每页显示的页数
	private int rows = 10;

	// 当前页
	private int page = 1;

	// 操作标志,默认是新增
	private boolean operate = true;

	/**
	 * 列表操作(传统式可以在此处增加Page)
	 */
	public String list() {
		return SUCCESS;
	}

	public T getModel() {
		return entity;
	}

	protected void prepareModel() {
		if (getId() == null || "".equals(getId())) {
			entity = getEntityInstance();
		} else {
			entity = getService().getEntity(getId());
			if (entity == null) {
				entity = getEntityInstance();
			}
		}
	}

	/**
	 * 定义在saveByOperate()前执行二次绑定.
	 */
	public void prepareSaveByOperate() {
		prepareModel();
	}

	public T getEntityInstance() {
		T instance = null;
		try {
			instance = getEntityClass().newInstance();
		} catch (Exception e) {
			throw new ServiceException("getEntityInstance_failure", e);
		}
		return instance;
	}

	/**
	 * Ajax列表操作
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@WebRequest(RequestType.AJAX)
	public void listAjax() {
		EasyUiDataGrid list = getService().searchForAjax(getPageable(), fetchPlusOrParams());
		WebUtils.renderJson(list);
	}

	public List<QueryParam> fetchParams() {
		return ServletUtils.getParameters();
	}

	public List<QueryParam> fetchPlusOrParams() {
		if (orParam != null) {
			try {
				orParam = URLDecoder.decode(orParam, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new ServiceException(e.getMessage(), e);
			}
		}
		return QueryParam.plusOrParams(ServletUtils.getParameters(), orParam, getOrParamArray());
	}

	protected String[] getOrParamArray() {
		return null;
	}

	public Pageable getPageable() {
		return new PageRequest(getPage() - 1, getRows(), getSort());
	}

	/**
	 * 获取所有实体
	 * 
	 * @return
	 */
	@WebRequest(RequestType.AJAX)
	public void findAll() {
		WebUtils.renderJson(getService().findAll(getSort()).toArray());
	}

	/**
	 * 获取所有实体
	 * 
	 * @return
	 */
	@WebRequest(RequestType.AJAX)
	public void findAllDataGrid() {
		WebUtils.renderJson(EasyUiUtils.getEasyUiDataGrid(getService().findAll()));
	}

	public Sort getSort() {
		if (sort == null) {
			return getDefaultSort();
		} else {
			if (Direction.ASC.toString().equalsIgnoreCase(order)) {
				return new Sort(Direction.ASC, sort);
			} else {
				return new Sort(Direction.DESC, sort);
			}

		}
	}

	public Sort getDefaultSort() {
		return null;
	}

	/**
	 * 删除多条
	 */
	@WebRequest(RequestType.AJAX)
	public void delete() {
		getService().delete(getIdArray());
		success("delete_success");
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getIds() {
		return ids;
	}

	@SuppressWarnings({ "unchecked" })
	public PK[] getIdArray() {
		if (getId() != null && !"".equals(getId())) {
			ids = getId().toString();
		}
		if (ids != null && !"".equals(ids)) {
			String[] tmpIds = ids.split(",");
			if (getPkClass() == String.class) {
				return (PK[]) tmpIds;
			} else {
				List<Long> list = new LinkedList<Long>();
				for (String tmpId : tmpIds) {
					list.add(Long.parseLong(tmpId));
				}
				return (PK[]) list.toArray(new Long[list.size()]);
			}

		}
		return null;
	}

	public boolean isOperate() {
		return operate;
	}

	public void setOperate(boolean operate) {
		this.operate = operate;
	}

	@Override
	public String input() {
		return INPUT;
	}

	public String inputByOperate() {
		return INPUT;
	}

	@WebRequest(RequestType.AJAX)
	public void saveByOperate() {
		getService().save(entity, operate);
		success("save_success");
	}

	/**
	 * 返回成功消息
	 * 
	 * */
	public void success(String msg) {
		MessageUtils.success(getService().getModule(), msg);
	}

	/**
	 * 返回失败消息
	 * 
	 * */
	public void failure(String msg) {
		MessageUtils.failure(getService().getModule(), msg);
	}

	/**
	 * 返回失败消息
	 * 
	 * */
	public void failure(Exception e) {
		MessageUtils.failure(getService().getModule(), e, this.getClass());
	}

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	/**
	 * Action函数, 默认的action函数, 默认调用list()函数.
	 */
	@Override
	public String execute() {
		return list();
	}

	public abstract CoreService<T, PK> getService();

	/**
	 * 实现空的prepare()函数,屏蔽了所有Action函数都会执行的公共的二次绑定.
	 */
	public void prepare() {
	}

	/**
	 * 定义在input()前执行二次绑定.
	 */
	public void prepareInput() {
		prepareModel();
	}

	/**
	 * 定义在save()前执行二次绑定.
	 */
	public void prepareSave() {
		prepareModel();
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public PK getId() {
		return id;
	}

	public void setId(PK id) {
		this.id = id;
	}

	@SuppressWarnings("unchecked")
	private Class<PK> getPkClass() {
		return (Class<PK>) getGenericClass(1);
	}

	@SuppressWarnings("unchecked")
	private Class<T> getEntityClass() {
		return (Class<T>) getGenericClass(0);
	}

	public Logger getLogger() {
		return logger;
	}

	private Class<?> getGenericClass(int i) {
		Type genType = getClass().getGenericSuperclass();
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		return (Class<?>) params[i];
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Map<Object, Object> getChooseMap(Class<?> clz, String key, String value, String headerValue) {
		return JspUtils.getChooseMap(clz, key, value, headerValue);
	}

	public Map<Object, Object> getChooseMap(Class<?> clz, String key, String value) {
		return JspUtils.getChooseMap(clz, key, value, "common_choose");
	}

	public Map<Object, Object> getTableMap(Class<?> clz, String key, String value) {
		return JspUtils.getTableMap(clz, key, value);
	}

	public Map<Object, Object> getChooseMap(Enum<?>[] enums, String headerValue) {
		return JspUtils.getChooseMap(enums, headerValue);
	}

	public Map<Object, Object> getChooseMap(Enum<?>[] enums) {
		return JspUtils.getChooseMap(enums, "common_choose");
	}

	public String getOrParam() {
		return orParam;
	}

	public void setOrParam(String orParam) {
		this.orParam = orParam;
	}

}
