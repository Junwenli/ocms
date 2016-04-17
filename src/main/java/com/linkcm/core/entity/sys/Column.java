package com.linkcm.core.entity.sys;

import org.apache.commons.lang.StringUtils;

public class Column {

	private String table;

	private String name;

	private String fieldName;

	private String desc;

	private String type;

	private long length;

	private boolean nullable;

	private boolean id;

	private boolean unique;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isUnique() {
		return unique;
	}

	public void setUnique(boolean unique) {
		this.unique = unique;
	}

	public String getTable() {
		return table;
	}

	public boolean isId() {
		return id;
	}

	public long getMin() {
		return 0;
	}

	public long getMax() {
		return Long.MAX_VALUE;
	}

	public void setId(boolean id) {
		this.id = id;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getDesc() {
		if (!StringUtils.isEmpty(desc)) {
			if (this.isEnum()) {
				String[] values = getSplitDesc();
				if (split(values[0]).length > 1) {
					return getMethodName();
				} else {
					return values[0];
				}
			} else {
				return desc;
			}
		} else {
			return getFieldName();
		}
	}

	public String[] split(String value) {
		String[] values = value.split("：");
		if (values.length == 1) {
			values = value.split(":");
		}
		return values;
	}

	public String[] getSplitDesc() {
		if (StringUtils.isEmpty(desc)) {
			return new String[] { "" };
		}
		String[] values = desc.split("\r\n");
		if (values.length == 1) {
			values = desc.split("\n");
		}
		return values;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getFieldName() {
		if (isId()) {
			return "id";
		} else {
			return fieldName;
		}
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getType() {
		if (isEnum()) {
			return type.equals("String") ? "String" : "int";
		} else {
			return type;
		}

	}

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}

	public boolean isRadio() {
		if (isEnum()) {
			String[] enums = getSplitDesc();
			String[] enumEntry = split(enums[0]);
			if (enumEntry.length == 1) {
				return enums.length == 3;
			} else {
				return enums.length == 2;
			}
		} else {
			return false;
		}
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMethodName() {
		return getFieldName().substring(0, 1).toUpperCase() + getFieldName().substring(1);
	}
	
	public String getEnumFieldName(){
		return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}

	public boolean isEnum() {
		if (StringUtils.isEmpty(desc)) {
			return false;
		} else {
			return getSplitDesc().length > 1 && split(getSplitDesc()[1]).length > 1;
		}
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public String getValidate() {
		String msg = "";
		if (!isNullable()) {
			msg = "required='true'";
			msg = String.format(msg, getDesc());
		}
		if (getType().equals("String")) {
			long min = getMin() > 0 ? getMin() : 0;
			long max = getLength();
			msg += " class='easyui-validatebox' validType='length[%d,%d]''";
			msg = String.format(msg, min, max, getDesc(), min, max);
		} else if (getType().equals("Long") || getType().equals("Integer") || getType().equals("int")) {
			long min = getMin();
			long max = getMax();
			msg += " class='easyui-numberbox' min='%d' max='%d'";
			msg = String.format(msg, min, max, getDesc(), min, max);
		}

		return msg;
	}
}
