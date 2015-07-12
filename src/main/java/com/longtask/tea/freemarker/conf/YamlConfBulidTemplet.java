package com.longtask.tea.freemarker.conf;

import com.longtask.tea.db.DbSchemaProvider;
import com.longtask.tea.freemarker.AbstractBulidTemplet;
import com.longtask.tea.freemarker.BulidTemplet;
import com.longtask.tea.yaml.PackageConfigUtil;

import java.util.Map;

public class YamlConfBulidTemplet extends AbstractBulidTemplet {

	@Override
	public boolean buildSourceFile(String tableName) {
		String configPath = PackageConfigUtil.getKey("dao",true);
		String outputDir = super.getBaseDir() + getMoudleDir("dao")+ BulidTemplet.TEST_CONF + configPath;
		String templateFileName = "yamlTest.ftl";
		String className = DbSchemaProvider.convertTableName(tableName);
		
		Map<String,Object> paramMap = super.getParamMap();
		paramMap.put("columns", DbSchemaProvider.getTableColumnNames(tableName));
		paramMap.put("package", PackageConfigUtil.getKey("domain",false));
		paramMap.put("className", className);
		
		String domainPackage = PackageConfigUtil.getKey("domain",false);
		paramMap.put("domain", domainPackage + "." + className);
		
		String fileName = className + "DaoTest.yaml" ;
		
		return super.buildJavaFile(templateFileName, paramMap, outputDir, fileName);
	}

}
