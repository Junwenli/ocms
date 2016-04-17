package ${packageName}.entity.type;
<%@ taglib prefix="s" uri="/struts-tags" %>
import com.linkcm.core.entity.type.AbstractTransformer;
import com.linkcm.core.entity.type.Transformer;
import com.linkcm.core.util.CoreUtils;
import com.linkcm.core.util.I18nUtils;

public enum ${name}${enumName} {
	<s:iterator value="enums" id="col" status="offset">
	<s:property escape='false' value="#col.key"/>(<s:property value="#col.value"/>)<s:if test="#offset.last">;</s:if><s:else>,</s:else></s:iterator>

	public final ${enumType} value;

	public static final Transformer transformer = new AbstractTransformer() {
		public String getDesc(Object type) {
			<s:if test="enumType==String">return ${name}${enumName}.getDesc(type.toString());</s:if><s:else>return ${name}${enumName}.getDesc(CoreUtils.object2int(type));</s:else>
		}
	};

	private ${name}${enumName}(${enumType} value) {
		this.value = value;
	}

	public String getDesc() {
		return I18nUtils.getMassage(name());
	}

	public static String getDesc(${enumType} type) {
		for (${name}${enumName} enumType : ${name}${enumName}.values()) {
			if (enumType.value == type) {
				return enumType.getDesc();
			}
		}
		return "" + type;
	}
}
<%@ page contentType="text/html;charset=UTF-8" %>