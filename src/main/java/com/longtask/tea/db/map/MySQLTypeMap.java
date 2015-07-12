package com.longtask.tea.db.map;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MySQLTypeMap implements TypeMap{
	
	public Map<String, Class<?>> javaTypeMap(){
		return java;
	}
	public Map<String, Integer> sqlTypeMap(){
		return sql;
	}
	
	static Map<String, Class<?>> java = new HashMap<String, Class<?>>() {
		private static final long serialVersionUID = -2101070752077610108L;

		{
			this.put("VARCHAR", String.class);
			this.put("CHAR", String.class);
			this.put("TEXT", String.class);
			this.put("MEDIUMTEXT", String.class);
			this.put("TINYINT", Integer.class);
			this.put("SMALLINT", Integer.class);
			this.put("MEDIUMINT", Integer.class);
			this.put("INT", Integer.class);
			this.put("INTEGER", Integer.class);
			this.put("BIGINT", Long.class);
			this.put("INTEGER UNSIGNED", Long.class);
			this.put("INT UNSIGNED", Long.class);
			this.put("FLOAT", Float.class);
			this.put("DOUBLE", Double.class);
			this.put("DECIMAL", BigDecimal.class);
			this.put("DEC", BigDecimal.class);
			this.put("DATE", Date.class);
			this.put("TIMESTAMP", Date.class);
			this.put("DATETIME", Date.class);
			this.put("ENUM", String.class);
//			this.put("DATE", java.sql.Date.class);
//			this.put("TIMESTAMP", java.sql.Timestamp.class);
//			this.put("DATETIME", java.sql.Timestamp.class);
			this.put("BIT", Boolean.class);
		}
	};
	
	static Map<String, Integer> sql = new HashMap<String, Integer>() {
		private static final long serialVersionUID = -2101070752077610108L;

		{
			this.put("VARCHAR", Types.VARCHAR);
			this.put("CHAR", Types.VARCHAR);
			this.put("TEXT", Types.VARCHAR);
			this.put("MEDIUMTEXT", Types.VARCHAR);
			this.put("TINYINT", Types.INTEGER);
			this.put("SMALLINT", Types.INTEGER);
			this.put("MEDIUMINT", Types.INTEGER);
			this.put("INT", Types.INTEGER);
			this.put("INTEGER", Types.INTEGER);
			this.put("BIGINT", Types.BIGINT);
			this.put("INTEGER UNSIGNED", Types.BIGINT);
			this.put("INT UNSIGNED", Types.BIGINT);
			this.put("FLOAT", Types.FLOAT);
			this.put("DOUBLE", Types.DOUBLE);
			this.put("DECIMAL", Types.NUMERIC);
			this.put("DEC", Types.NUMERIC);
			this.put("DATE", Types.DATE);
			this.put("ENUM", Types.OTHER);
			this.put("TIMESTAMP", Types.TIMESTAMP);
			this.put("DATETIME", Types.TIMESTAMP);
			this.put("BIT", Types.BOOLEAN);
		}
	};
	
}
