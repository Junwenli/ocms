package com.linkcm.core.entity.sys;

import java.util.Locale;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

@Entity
@Table(name = "T_SYS_ICON")
public class SysIcon {

	@Id
	@SequenceGenerator(name = "iconGenerator", sequenceName = "S_T_SYS_ICON", allocationSize = 1 )
	@GeneratedValue(generator = "iconGenerator", strategy = GenerationType.SEQUENCE)
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
		return LocalizedTextUtil.findDefaultText(title, 
				new Locale(ActionContext.getContext().getLocale().getLanguage()
						+"_"+ActionContext.getContext().getLocale().getCountry()));
//		return title;
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
