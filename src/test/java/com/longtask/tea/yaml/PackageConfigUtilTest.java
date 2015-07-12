package com.longtask.tea.yaml;

import junit.framework.Assert;


public class PackageConfigUtilTest{
	
	public void testGetDomain(){
		Assert.assertEquals("/com/longtask/domain",PackageConfigUtil.getKey("domain",true));
		Assert.assertEquals("com.longtask.domain",PackageConfigUtil.getKey("domain",false));
	}
	
}
