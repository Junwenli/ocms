package com.linkcm.core.util;

import java.util.List;

import org.springframework.data.domain.Page;

import com.linkcm.core.entity.VoEntity;
import com.linkcm.core.web.EasyUiDataGrid;

public final class EasyUiUtils {

	private EasyUiUtils() {

	}

	/**
	 * 获得EasyUiDataGrid
	 * 
	 * @param pageEntity
	 *            整个页面实体集
	 * */
	public static EasyUiDataGrid getEasyUiDataGrid(Page<?> pageEntity) {
		EasyUiDataGrid dataGrid = getDataGrid(pageEntity);
		List<?> content = pageEntity.getContent();
		setData(dataGrid, content);
		return dataGrid;
	}

	/**
	 * 把页面实体集转成DataGrid形式
	 * 
	 * @param pageEntity
	 * @return
	 */
	private static EasyUiDataGrid getDataGrid(Page<?> pageEntity) {
		EasyUiDataGrid dataGrid = new EasyUiDataGrid();
		dataGrid.setTotal(pageEntity.getTotalElements());
		return dataGrid;
	}

	/**
	 * 获得EasyUiDataGrid
	 * 
	 * @param list
	 *            需要转成EasyUiDataGrid的实体
	 * */
	public static EasyUiDataGrid getEasyUiDataGrid(List<?> list) {
		EasyUiDataGrid dataGrid = new EasyUiDataGrid();
		setData(dataGrid, list);
		dataGrid.setTotal(list.size());
		return dataGrid;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void setData(EasyUiDataGrid dataGrid, List content) {
		if (!content.isEmpty() && content.get(0) instanceof VoEntity) {
			List<Object> rows = dataGrid.getRows();
			for (VoEntity entity : (List<VoEntity>) content) {
				rows.add(entity.returnVo());
			}
		} else {
			dataGrid.setRows(content);
		}
	}

	/**
	 * 获得EasyUiDataGrid
	 * 
	 * @param list
	 *            需要转换的集合
	 * @param total
	 *            总数量
	 * */
	public static EasyUiDataGrid getEasyUiDataGrid(List<?> list, int total) {
		EasyUiDataGrid dataGrid = new EasyUiDataGrid();
		setData(dataGrid, list);
		dataGrid.setTotal(total);
		return dataGrid;
	}

}
