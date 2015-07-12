package com.longtask.tea.freemarker.main;

import com.longtask.tea.db.DbSchemaProvider;
import com.longtask.tea.freemarker.AbstractBulidTemplet;
import com.longtask.tea.yaml.PackageConfigUtil;

import java.util.Map;
import java.util.UUID;

public class DomainFileBulidTemplet extends AbstractBulidTemplet {

	@Override
	public boolean buildSourceFile(String tableName) {

		String outputDir = PackageConfigUtil.getTargetDir() + getMoudleDir("domain") ;

		String templateFileName = "domain.ftl";
		String className = DbSchemaProvider.convertTableName(tableName);
		
		Map<String,Object> paramMap = super.getParamMap();
		paramMap.put("columns", DbSchemaProvider.getTableColumnNames(tableName));
		paramMap.put("package", PackageConfigUtil.getKey("domain",false));
		paramMap.put("className", className);
		paramMap.put("UUID", String.valueOf(UUID.randomUUID().getLeastSignificantBits()));
		
		String fileName = className  + ".java";
		
		return super.buildJavaFile(templateFileName, paramMap, outputDir, fileName);
	}

}
