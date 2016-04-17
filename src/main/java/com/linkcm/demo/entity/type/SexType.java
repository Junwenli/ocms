package com.linkcm.demo.entity.type;

import com.linkcm.core.entity.type.AbstractTransformer;
import com.linkcm.core.entity.type.Transformer;
import com.linkcm.core.util.CoreUtils;
import com.linkcm.core.util.I18nUtils;

public enum SexType {
	male(0, "男"), female(1, "女");

	public final int value;

	private final String desc;

	public static final Transformer transformer = new AbstractTransformer() {
		public String getDesc(Object type) {
			return SexType.getDesc(CoreUtils.object2int(type));
		}
	};

	private SexType(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public String getDesc() {
		return I18nUtils.getMassage(desc);
	}

	public static String getDesc(int type) {
		for (SexType enumType : SexType.values()) {
			if (enumType.value == type) {
				return enumType.getDesc();
			}
		}
		return "" + type;
	}

}
