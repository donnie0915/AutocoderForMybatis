package com.longtask.tea.yaml;

import java.util.HashMap;
import java.util.Map;

/**
 * yaml配置信息解析
 * @author longhao
 *
 */
public class YamlDomain {
	private String name;
	private String version;
	private Map<String,String> codePackageMap = new HashMap<String,String>();
	private Map<String,String> sqlMap = new HashMap<String,String>();
	
	private Map<String,String> userMap = new HashMap<String,String>();
	
	
	
	
	@Override
	public String toString() {
		return "YamlDomain [name=" + name + ", version=" + version
				+ ", codePackageMap=" + codePackageMap + ", sqlMap=" + sqlMap
				+ ", userMap=" + userMap + "]";
	}
	public String getCodePackageMap(String key) {
		return codePackageMap.get(key);
	}
	public void setCodePackageMap(String key,String value) {
		codePackageMap.put(key, value);
	}
	public String getSqlMap(String key) {
		return sqlMap.get(key);
	}
	public void setSqlMap(String key,String value) {
		sqlMap.put(key, value);
	}
	
	public String getUserMap(String key) {
		return userMap.get(key);
	}
	public void setUserMap(String key,String value) {
		userMap.put(key, value);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
}
