package com.linkcm.core.entity.type;

import com.linkcm.core.util.CoreUtils;
import com.linkcm.core.util.I18nUtils;

public enum UserStatus {

	flopnumber(0, "sys_enum_user_deactivity"), average(1, "sys_enum_user_activity");

	public final int value;

	private final String desc;

	public static final Transformer transformer = new AbstractTransformer() {
		public String getDesc(Object type) {
			return UserStatus.getDesc(CoreUtils.object2int(type));
		}
	};

	private UserStatus(int value, String desc) {
		this.value = value;
		this.desc = desc;
	}

	public String getDesc() {
		return I18nUtils.getMassage(desc);
	}

	public static String getDesc(int type) {
		for (UserStatus enumType : UserStatus.values()) {
			if (enumType.value == type) {
				return enumType.getDesc();
			}
		}
		return "" + type;
	}
}