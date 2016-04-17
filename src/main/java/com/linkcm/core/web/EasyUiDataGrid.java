package com.linkcm.core.web;

import java.util.ArrayList;
import java.util.List;

/**
 * 由于JQUERY_EASYUI需要特殊的JSON
 * @author antking
 *
 */
public class EasyUiDataGrid
{
	//返回的总数量
	private long total = 0;
	
	//返回的列表
	private List<Object> rows = new ArrayList<Object>();

	public long getTotal()
	{
		return total;
	}

	public void setTotal(long total)
	{
		this.total = total;
	}

	public List<Object> getRows()
	{
		return rows;
	}

	public void setRows(List<Object> rows)
	{
		this.rows = rows;
	}	
}
