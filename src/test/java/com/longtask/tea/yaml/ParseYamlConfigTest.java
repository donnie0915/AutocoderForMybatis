package com.longtask.tea.yaml;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ParseYamlConfigTest{
	@Before
	public void setUp(){
		
	}
	@After
	public void tearDown(){
		
	}
	@Test
	public void testParseYaml(){
		YamlDomain yamlDomain = ParseYamlConfig.parseYaml();
		Assert.assertEquals("1.0.0",yamlDomain.getVersion());
		Assert.assertEquals("dao",yamlDomain.getCodePackageMap("dao"));
	}
}
