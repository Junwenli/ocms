package com.linkcm.core.entity.sys;

import java.util.LinkedList;
import java.util.List;


public class Index {
	private String indexName;

	private List<Column> columns = new LinkedList<Column>();

	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

}
