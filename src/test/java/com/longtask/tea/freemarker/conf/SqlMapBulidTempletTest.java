package com.longtask.tea.freemarker.conf;

import org.junit.Before;
import org.junit.Test;

import com.longtask.tea.freemarker.BulidTemplet;


public class SqlMapBulidTempletTest{
	private BulidTemplet bulidTemplet;
	@Before
	public void setUp(){
		bulidTemplet = new SqlMapBulidTemplet();
	}
	
	@Test
	public void testBuildSourceFile(){
		bulidTemplet.buildSourceFile("app");
	}
}
