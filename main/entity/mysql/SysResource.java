package com.ericsson.core.entity.sys;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ericsson.core.entity.AbstractEntity;
import com.ericsson.core.entity.type.ResourceType;
import com.ericsson.core.util.CoreUtils;
/**
 * <p>
 * <b>T_SYS_PERMISSION</b> 是
 * </p>
 * 
 * @since 2012-0-6
 * @author zml
 * @version $Id:$
 * 
 */
@Entity
@Table(name="T_SYS_PERMISSION")
public class SysResource extends AbstractEntity{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "PERMISSION_ID")
	private Long id ;
	
	@Column(name = "PERMISSION_CODE")
	private String permissionCode;

	@Column(name = "TITLE",nullable =true ,length  =100)
	/**权限名称*/
	private String title ;
	
	/**权限类型*/
	@Column(name = "PERMISSION_TYPE") 
	private Integer permissionType ;
	
	/**父id*/
	@Column(name="PARENT_ID")
	private Long _parentId;
	
	private String url;
	@Column(name = "SYSTEM_ID")
	private String systemId;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public String getPermissionCode() {
		return permissionCode;
	}

	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}

	public Integer getPermissionType() {
		return permissionType;
	}

	public void setPermissionType(Integer permissionType) {
		this.permissionType = permissionType;
	}
	
	

	public Long get_parentId() {
		return _parentId;
	}

	public void set_parentId(Long _parentId) {
		this._parentId = _parentId;
	}

	/**
	* @return the title
	*/
	public  String getTitle(){
 		 return this.title;
	}

	public  void setTitle(String title){
 		 this.title=title;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * 获得父类资源名称
	 * @return
	 */
	public String getParentTitle() {
		return CoreUtils.code2value(_parentId, SysResource.class, "id", "title");
	}
	
	/**
	 * 获得资源类型名
	 * @return
	 */
	public String getResourceType() {
		return ResourceType.getDesc(permissionType);
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

}
