package com.longtask.tea.freemarker.conf;

import com.longtask.tea.db.DbSchemaProvider;
import com.longtask.tea.freemarker.AbstractBulidTemplet;
import com.longtask.tea.freemarker.BulidTemplet;
import com.longtask.tea.yaml.PackageConfigUtil;
import com.longtask.tea.yaml.ParseYamlConfig;

import java.util.Map;

public class SpringConfBulidTemplet extends AbstractBulidTemplet {

	@Override
	public boolean buildSourceFile(String tableName) {
		String outputDir = super.getBaseDir()+ getMoudleDir("dao") + BulidTemplet.MAIN_CONF;
		String templateFileName = "springConf.ftl";
		String className = DbSchemaProvider.convertTableName(tableName);
		
		Map<String,Object> paramMap = super.getParamMap();
		paramMap.put("className", className);
		paramMap.put("daoConf", ParseYamlConfig.parseYaml().getUserMap("ibatis_config_name"));
		paramMap.put("daoImplPackage", PackageConfigUtil.getKey("daoImpl",false));
		paramMap.put("daoImpl", PackageConfigUtil.getKey("daoImpl",false) + "." + className + "DaoImpl");
		
		String fileName = ParseYamlConfig.parseYaml().getUserMap("spring_config_name");
		
		return super.buildJavaFile(templateFileName, paramMap, outputDir, fileName);
	}

}
