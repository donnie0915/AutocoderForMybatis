package com.longtask.tea.freemarker.conf;

import com.longtask.tea.db.DbSchemaProvider;
import com.longtask.tea.domain.Column;
import com.longtask.tea.freemarker.AbstractBulidTemplet;
import com.longtask.tea.yaml.PackageConfigUtil;

import java.util.List;
import java.util.Map;

public class SqlMapBulidTemplet extends AbstractBulidTemplet {

	@Override
	public boolean buildSourceFile(String tableName) {

		String outputDir = PackageConfigUtil.getTargetDir() + getMoudleDir("mybatis") ;
		String templateFileName = "sqlMap.ftl";
		String className = DbSchemaProvider.convertTableName(tableName);
		
		Map<String,Object> paramMap = super.getParamMap();
        List<Column> columns = DbSchemaProvider.getTableColumnNames(tableName);
        paramMap.put("columns", columns);
        if (columns != null && columns.size() > 0) {
            paramMap.put("keyColumn", DbSchemaProvider.getPrimaryColumn(columns));
            for (Column column : columns) {
                if (column.getDateType().equals("datetime") && column.getColumnName().equalsIgnoreCase("gmt_modified")) {
                    paramMap.put("modifiedColumn", column);
                }
            }
        }

		paramMap.put("package", PackageConfigUtil.getKey("dao",false));
		paramMap.put("className", className);
		paramMap.put("tableName", tableName);
		paramMap.put("boxBracket","{");
		
		String domainPackage = PackageConfigUtil.getKey("domain",false);
		paramMap.put("domain", domainPackage + "." + className);
		
		String fileName = className  + "SqlMapping.xml";
		
		return super.buildJavaFile(templateFileName, paramMap, outputDir, fileName);
	}

}
