package com.longtask.tea.freemarker.conf;

import com.longtask.tea.freemarker.AbstractBulidTemplet;
import com.longtask.tea.freemarker.BulidTemplet;
import com.longtask.tea.yaml.ParseYamlConfig;

import java.util.Map;

public class JdbcBulidTemplet extends AbstractBulidTemplet {

	@Override
	public boolean buildSourceFile(String tableName) {
		String outputDir = super.getBaseDir()+ getMoudleDir("dao") + BulidTemplet.MAIN_CONF;
		String templateFileName = "jdbc.ftl";
		
		Map<String,Object> paramMap = setParams("driver" , "url" , "username" , "password");
		String fileName = "jdbc.properties";
		
		return super.buildJavaFile(templateFileName, paramMap, outputDir, fileName);
	}

	private Map<String,Object> setParams(String ... objs){
		Map<String,Object> paramMap = super.getParamMap();
		for(String str : objs){
			paramMap.put(str, ParseYamlConfig.parseYaml().getSqlMap(str));
		}
		return paramMap;
	}
}
