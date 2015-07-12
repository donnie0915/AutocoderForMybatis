package com.longtask.tea.freemarker.main.biz;

import com.longtask.tea.db.DbSchemaProvider;
import com.longtask.tea.domain.Column;
import com.longtask.tea.freemarker.AbstractBulidTemplet;
import com.longtask.tea.yaml.PackageConfigUtil;

import java.util.List;
import java.util.Map;

public class QueryBizTemplate extends AbstractBulidTemplet {

	@Override
	public boolean buildSourceFile(String tableName) {
		String outputDir = PackageConfigUtil.getTargetDir() + getMoudleDir("service");
		String templateFileName = "biz/queryBiz.ftl";
		String className = DbSchemaProvider.convertTableName(tableName);
		
		Map<String,Object> paramMap = super.getParamMap();
		List<Column> columns = DbSchemaProvider.getTableColumnNames(tableName);
		paramMap.put("columns", columns);
		if (columns != null && columns.size() > 0) {
	         paramMap.put("keyColumn", DbSchemaProvider.getPrimaryColumn(columns));
	    }
		
		paramMap.put("package", PackageConfigUtil.getKey("service", false));
		paramMap.put("domainPackage", PackageConfigUtil.getKey("domain", false));
		paramMap.put("className", className);

		String fileName = className  + "QueryBiz.java";
		
		return super.buildJavaFile(templateFileName, paramMap, outputDir, fileName);
	}

}
