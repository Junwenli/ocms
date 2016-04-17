package com.linkcm.core.entity.sys;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.linkcm.core.util.I18nUtils;

@Entity
@Table(name = "T_SYS_ICON")
public class SysIcon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ICON_ID")
	private Long id;
	private String title;
	@Column(name = "ICON_URL")
	private String iconUrl;
	private String url;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return I18nUtils.getMassage(title);
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
