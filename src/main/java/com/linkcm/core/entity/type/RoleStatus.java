package com.linkcm.core.entity.type;

import com.linkcm.core.util.CoreUtils;
import com.linkcm.core.util.I18nUtils;

public enum RoleStatus {

	flop(0, "sys_enum_role_block"), normal(1, "sys_enum_role_normal");
	
	public final int value;

	private final String desc;

	public static final Transformer transformer = new AbstractTransformer() {
		public String getDesc(Object type) {
			return RoleStatus.getDesc(CoreUtils.object2int(type));
		}
	};

	private RoleStatus(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public String getDesc() {
		return I18nUtils.getMassage(desc);
	}

	public static String getDesc(int type) {
		for (RoleStatus enumType : RoleStatus.values()) {
			if (enumType.value == type) {
				return enumType.getDesc();
			}
		}
		return "" + type;
	}
}