package com.linkcm.core.web.sys;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.linkcm.core.annotation.WebRequest;
import com.linkcm.core.annotation.WebRequest.RequestType;
import com.linkcm.core.dao.OracleCodeJdbcDao;
import com.linkcm.core.entity.sys.Column;
import com.linkcm.core.entity.sys.Index;
import com.linkcm.core.entity.sys.SysIcon;
import com.linkcm.core.service.CoreService;
import com.linkcm.core.service.sys.CodeService;
import com.linkcm.core.util.ServletUtils;
import com.linkcm.core.util.WebUtils;
import com.linkcm.core.web.CoreAction;
import com.linkcm.core.web.EasyUiDataGrid;

@Namespace("/code")
public class CodeAction extends CoreAction<SysIcon, Long> {

	private static final long serialVersionUID = 1L;

	private static final String PATH = "d:/sonic/";

	private static final String DOT = "\\.";

	@Autowired
	private CodeService codeService;

	private String data;

	private String file;

	private String folder;

	private String name;

	private String desc;

	private String packageName;

	private String module;

	private String table;

	private List<Map<String, Object>> enums;

	private String type;

	private String enumName;

	private String enumType;

	private List<Index> indexes;

	private boolean beforeSave;

	@Override
	public CoreService<SysIcon, Long> getService() {
		return null;
	}

	public String generate() {
		String folderName = PATH + getPackageName().replaceAll(DOT, "/") + "/" + folder + "/" + module;
		writeFile(folderName, getName() + file, data);
		return null;
	}

	public String generateEntity() {
		String folderName = PATH + getPackageName().replaceAll(DOT, "/") + "/" + folder;
		writeFile(folderName, getName() + file, data);
		return null;
	}

	public String generateDaoImpl() {
		String folderName = PATH + getPackageName().replaceAll(DOT, "/") + "/" + folder + "/" + module + "/impl";
		writeFile(folderName, getName() + file, data);
		return null;
	}

	public String generateEnum() {
		String folderName = PATH + getPackageName().replaceAll(DOT, "/") + "/" + folder;
		writeFile(folderName, getName() + enumName + file, data);
		return null;
	}

	public String generateJs() {
		String folderName = PATH + getLowerCaseName();
		writeFile(folderName, getRequestPath() + file, data);
		return null;
	}

	public String generateSql() {
		String folderName = PATH + getLowerCaseName();
		writeFile(folderName, getLowerCaseName() + file, data);
		return null;
	}

	public String sql() {
		return "sql";
	}

	public String generateJsp() {
		String folderName = PATH + getLowerCaseName();
		writeFile(folderName, getRequestPath() + file,
				"<%@ page contentType=\"text/html;charset=UTF-8\" %>\r\n<%@ include file=\"/common/taglibs.jsp\" %>\r\n"
						+ data.replaceAll("\\\\", ""));
		return null;
	}

	private void writeFile(String folder, String file, String data) {
		try {
			File fileFolder = new File(folder);
			if (!fileFolder.exists()) {
				fileFolder.mkdirs();
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(folder + "/" + file));
			writer.write(data);
			writer.close();
		} catch (Exception e) {
			getLogger().error(e.getMessage(), e);
		}
	}

	/**
	 * Ajax列表操作
	 * 
	 * @return
	 */
	@WebRequest(RequestType.AJAX)
	public void listAjax() {
		EasyUiDataGrid entity = codeService.searchForAjax(getPageable(), ServletUtils.getParameters());
		WebUtils.renderJson(entity);
	}

	public String service() {
		List<Column> cols = getEntityCols();
		indexes = codeService.findIndex(table, cols);
		return "service";
	}

	public String listJs() {
		return "list_js";
	}

	public String datagridJs() {
		return "datagrid_js";
	}

	public String action() {
		return "action";
	}

	public String findEnums() {
		List<Column> cols = getEntityCols();
		List<String> enumList = new LinkedList<String>();
		for (Column col : cols) {
			if (col.isEnum()) {
				enumList.add(col.getMethodName());
			}
		}
		WebUtils.renderJson(enumList);
		return null;
	}

	public String type() {
		List<Column> cols = getEntityCols();
		boolean flag = false;
		for (Column col : cols) {
			if (col.isEnum() && enumName.equals(col.getMethodName())) {
				enumType = col.getType();
				flag = true;
				enums = new LinkedList<Map<String, Object>>();
				String[] types = col.getSplitDesc();
				for (String typeString : types) {
					String[] typeArray = col.split(typeString);
					if (typeArray.length > 1) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("key", typeArray[1]);
						map.put("value", (typeArray[0] + "").trim());
						enums.add(map);
					}
				}
			}
		}
		if (flag) {
			return "type";
		} else {
			success("");
			return null;
		}

	}

	public String test() {
		List<Column> cols = getEntityCols();
		indexes = codeService.findIndex(table, cols);
		return "test";
	}

	public String entity() {
		return "entity";
	}

	public String listJsp() {
		return "list_jsp";
	}

	public String inputJs() {
		return "input_js";
	}

	public String inputJsp() {
		return "input_jsp";
	}

	public String dao() {
		return "dao";
	}

	public String daoImpl() {
		return "dao_impl";
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public String getData() {
		return data;
	}

	public String getIdType() {
		List<Column> cols = getEntityCols();
		for (Column col : cols) {
			if (col.isId()) {
				return col.getType();
			}
		}
		return null;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public String getEnumType() {
		return enumType;
	}

	public void setEnumType(String enumType) {
		this.enumType = enumType;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getRequestPath() {
		Pattern p = Pattern.compile("[A-Z][a-z0-9]+");
		StringBuilder builder = new StringBuilder();
		Matcher mc = p.matcher(name);
		while (mc.find()) {
			builder.append(mc.group().toLowerCase());
			if (!mc.hitEnd()) {
				builder.append("-");
			}
		}
		return builder.toString();
	}

	public String getName1() {
		return getName().substring(0, 1).toLowerCase() + getName().substring(1);
	}

	public String getLowerCaseName() {
		return getName().toLowerCase();
	}

	public String getUpperCaseName() {
		return getName().toUpperCase();
	}

	public List<Column> getEntityCols() {
		return codeService.findColumn(table);
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public List<Map<String, Object>> getEnums() {
		return enums;
	}

	public void setEnums(List<Map<String, Object>> enums) {
		this.enums = enums;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getEnumName() {
		return enumName;
	}

	public void setEnumName(String enumName) {
		this.enumName = enumName;
	}

	public boolean isBeforeSave() {
		for (Index index : indexes) {
			if (index.getColumns().size() > 1) {
				beforeSave = true;
				break;
			} else {
				Column column = index.getColumns().get(0);
				if (!column.isId() || column.getType().equals("String")) {
					beforeSave = true;
					break;
				}
			}
		}
		return beforeSave;
	}

	public List<Index> getIndexes() {
		return indexes;
	}

	public void setBeforeSave(boolean beforeSave) {
		this.beforeSave = beforeSave;
	}

	public boolean isOracle() {
		if (codeService.getCodeJdbcDao() instanceof OracleCodeJdbcDao) {
			return true;
		}
		return false;
	}

}
