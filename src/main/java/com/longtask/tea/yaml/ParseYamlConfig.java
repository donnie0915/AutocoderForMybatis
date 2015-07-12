package com.longtask.tea.yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Set;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

/**
 * 加载yaml的配置文件
 * @author longhao
 *
 */

public class ParseYamlConfig {
	private final static Logger logger = LoggerFactory.getLogger(ParseYamlConfig.class);
	private static YamlDomain yamlDomain = new YamlDomain();
	static{
		Yaml yaml = new Yaml();
		Object obj;
		try{
			obj = yaml.load(ParseYamlConfig.class.getResourceAsStream("/baseconfig.yaml"));
		}catch(Exception e){
			String usrDir = System.getProperty("user.dir");
			if(usrDir.endsWith("/lib")){
				usrDir = usrDir.substring(0, usrDir.length()-5);
			}
			try {
				obj = yaml.load(new FileInputStream(new File(usrDir+"/baseconfig.yaml")));
			} catch (FileNotFoundException e1) {
				logger.error("baseconfig.yaml is no found!");
				throw new RuntimeException();
			}
		}
		JSONObject json = JSONObject.fromObject(obj);
		logger.info("Yaml config is :" + json);
		
		String name = json.getString("name");
		String version = json.getString("version");
		yamlDomain.setName(name);
		yamlDomain.setVersion(version);
		//user parameter
		Set<?> set = json.keySet();
		for(Object key : set){
			if(json.get(key) instanceof String){
				String value = json.getString((String)key);
				yamlDomain.setUserMap((String)key, value);
			}else{
				
			}
		}
		//package parameter
		JSONObject packageObj = json.getJSONArray("package").getJSONObject(0);
		Set<?> packageSet = packageObj.keySet();
		for(Object key : packageSet){
			if(key instanceof String){
				String value = packageObj.getString((String)key);
				yamlDomain.setCodePackageMap((String)key, value);
			}
		}
		//sql parameter
		JSONObject sqlObj = json.getJSONArray("sql").getJSONObject(0);
		Set<?> sqlSet = sqlObj.keySet();
		for(Object key : sqlSet){
			if(key instanceof String){
				String value = sqlObj.getString((String)key);
				yamlDomain.setSqlMap((String)key, value);
			}
		}
	}
	
	public static YamlDomain parseYaml(){
		return yamlDomain;
	}
}
