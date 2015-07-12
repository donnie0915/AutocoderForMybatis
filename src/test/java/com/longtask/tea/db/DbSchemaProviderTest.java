package com.longtask.tea.db;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.longtask.tea.domain.Column;

public class DbSchemaProviderTest{
	@Before
	public void setUp(){
		
	}
	@After
	public void tearDown(){
		
	}
	@Test
	public void testIsTablesExsit(){
		Set<String> tablesName = new HashSet<String>();
		tablesName.add("app");
		Assert.assertEquals(true,DbSchemaProvider.isTablesExsit(tablesName));
		tablesName.add("app1");
		Assert.assertEquals(false,DbSchemaProvider.isTablesExsit(tablesName));
	}
	@Test
	public void testGetTableColumnNames(){
		List<Column> list = DbSchemaProvider.getTableColumnNames("app");
		for(Column column : list){
			System.out.println(column.toString());
		}
	}
}
