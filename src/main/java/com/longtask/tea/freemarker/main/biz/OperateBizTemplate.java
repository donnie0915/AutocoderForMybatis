package com.longtask.tea.freemarker.main.biz;

import com.longtask.tea.db.DbSchemaProvider;
import com.longtask.tea.freemarker.AbstractBulidTemplet;
import com.longtask.tea.yaml.PackageConfigUtil;

import java.util.Map;

public class OperateBizTemplate extends AbstractBulidTemplet {

    @Override
    public boolean buildSourceFile(String tableName) {
        String outputDir = PackageConfigUtil.getTargetDir() + getMoudleDir("service");
        String templateFileName = "biz/operateBiz.ftl";
        String className = DbSchemaProvider.convertTableName(tableName);

        Map<String, Object> paramMap = super.getParamMap();
        paramMap.put("columns", DbSchemaProvider.getTableColumnNames(tableName));
        paramMap.put("package", PackageConfigUtil.getKey("service", false));

        paramMap.put("domainPackage", PackageConfigUtil.getKey("domain", false));
        paramMap.put("className", className);

        String fileName = className + "OperateBiz.java";

        return super.buildJavaFile(templateFileName, paramMap, outputDir, fileName);
    }

}
