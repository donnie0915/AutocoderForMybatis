package com.longtask.tea.db.map;

import java.util.Map;

public interface TypeMap {
	public Map<String, Class<?>> javaTypeMap();
	
	public Map<String, Integer> sqlTypeMap();
}
