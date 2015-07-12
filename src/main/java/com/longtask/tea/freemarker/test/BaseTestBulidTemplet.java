package com.longtask.tea.freemarker.test;

import java.util.Map;

import com.longtask.tea.freemarker.AbstractBulidTemplet;
import com.longtask.tea.freemarker.BulidTemplet;
import com.longtask.tea.yaml.ParseYamlConfig;

public class BaseTestBulidTemplet extends AbstractBulidTemplet {

	@Override
	public boolean buildSourceFile(String tableName) {
		String pg = ParseYamlConfig.parseYaml().getUserMap("abstract_spring_dao_test");
		pg = pg.substring(0 , pg.lastIndexOf('.'));
		String configPath = pg.replace(".", "/");
		String outputDir = super.getBaseDir() + BulidTemplet.TEST_PATH + "/" + configPath;
		String templateFileName = "baseTester.ftl";
		
		Map<String,Object> paramMap = super.getParamMap();
		paramMap.put("package", pg);
		String fileName = "AbstractSpringDaoTest.java";
		
		return super.buildJavaFile(templateFileName, paramMap, outputDir, fileName);
	}

}
