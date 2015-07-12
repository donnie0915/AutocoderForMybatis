package com.longtask.tea.freemarker.main;

import org.junit.Before;
import org.junit.Test;

import com.longtask.tea.freemarker.BulidTemplet;


public class DaoBulidTempletTest{
	private BulidTemplet bulidTemplet;
	@Before
	public void setUp(){
		bulidTemplet = new DaoFileBulidTemplet();
	}
	
	@Test
	public void testBuildSourceFile(){
		bulidTemplet.buildSourceFile("app");
	}
}
