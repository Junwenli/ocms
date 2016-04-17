package com.linkcm.core.entity.type;

import com.linkcm.core.util.CoreUtils;
import com.linkcm.core.util.I18nUtils;

public enum ResourceType {
	SYSTEM(0, "sys_enum_resource_system"), MENU(1, "sys_enum_resource_menu"), FUNCTION(2, "sys_enum_resource_function");

	public final int value;

	private final String desc;

	public static final Transformer transformer = new AbstractTransformer() {
		public String getDesc(Object type) {
			return ResourceType.getDesc(CoreUtils.object2int(type));
		}
	};

	private ResourceType(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public String getDesc() {
		return I18nUtils.getMassage(desc);
	}

	public static String getDesc(int type) {
		for (ResourceType enumType : ResourceType.values()) {
			if (enumType.value == type) {
				return enumType.getDesc();
			}
		}
		return "" + type;
	}
}