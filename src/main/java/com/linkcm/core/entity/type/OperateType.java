package com.linkcm.core.entity.type;

public enum OperateType {
	EQ("EQ", "="), NE("NE", "<>"), LE("LE", "<="), LT("LT", "<"), GE("GE", ">="), GT("GT", ">"), LIKE("LIKE", "LIKE"),IN("IN","IN");

	public final String value;

	public final String operater;

	private OperateType(String value, String operater) {
		this.value = value;
		this.operater = operater;
	}

}
