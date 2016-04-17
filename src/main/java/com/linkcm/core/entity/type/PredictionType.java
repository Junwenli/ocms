package com.linkcm.core.entity.type;

public enum PredictionType {

	AND("AND"), OR("OR");

	public final String value;

	private PredictionType(String value) {
		this.value = value;
	}

}
