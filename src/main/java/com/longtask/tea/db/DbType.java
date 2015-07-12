package com.longtask.tea.db;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.StringUtils;
import org.unitils.core.dbsupport.DbSupport;
import org.unitils.core.dbsupport.DefaultSQLHandler;
import org.unitils.core.dbsupport.DerbyDbSupport;
import org.unitils.core.dbsupport.MsSqlDbSupport;
import org.unitils.core.dbsupport.MySqlDbSupport;
import org.unitils.core.dbsupport.OracleDbSupport;
import org.unitils.core.dbsupport.SQLHandler;

import com.longtask.tea.db.map.MySQLTypeMap;
import com.longtask.tea.db.map.OracleTypeMap;
import com.longtask.tea.db.map.TypeMap;
import com.longtask.tea.db.table.ColumnSchema;
import com.longtask.tea.db.table.MysqlColumnSchema;
import com.longtask.tea.yaml.ParseYamlConfig;

/**
 * 根据不同的数据库来创建连接，读取数据
 * 目前支持mysql ， oracle
 * @author longhao
 *
 */

public enum DbType{
	MYSQL() {
		@Override
		public DbSupport getDbSupport() {
			return new MySqlDbSupport(){
				public SQLHandler getSQLHandler() {
			        return new DefaultSQLHandler(dataSource);
			    }
				public String getSchemaName() {
			        return ParseYamlConfig.parseYaml().getSqlMap("database");
			    }
			};
		}

		@Override
		public String getDbUnitDialect() {
			return "mysql";
		}

		@Override
		public ColumnSchema getColumnSchema() {
			return new MysqlColumnSchema();
		}
		@Override
		public TypeMap getTypeMap() {
			return new MySQLTypeMap();
		}
	},
	ORACLE() {
		@Override
		public DbSupport getDbSupport() {
			return new OracleDbSupport(){
				public SQLHandler getSQLHandler() {
			        return new DefaultSQLHandler(dataSource);
			    }
				public String getSchemaName() {
			        return ParseYamlConfig.parseYaml().getSqlMap("database");
			    }
			};
		}

		@Override
		public String getDbUnitDialect() {
			return "oracle";
		}
		@Override
		public ColumnSchema getColumnSchema() {
			return null;
		}
		@Override
		public TypeMap getTypeMap() {
			return new OracleTypeMap();
		}
	},
	SQLSERVER() {

		@Override
		public DbSupport getDbSupport() {
			return new MsSqlDbSupport(){
				public SQLHandler getSQLHandler() {
			        return new DefaultSQLHandler(dataSource);
			    }
				public String getSchemaName() {
			        return ParseYamlConfig.parseYaml().getSqlMap("database");
			    }
			};
		}

		@Override
		public String getDbUnitDialect() {
			return "mssql";
		}
		@Override
		public ColumnSchema getColumnSchema() {
			return null;
		}
		@Override
		public TypeMap getTypeMap() {
			return new MySQLTypeMap();
		}
	},

	DERBYDB() {
		@Override
		public DbSupport getDbSupport() {
			return new DerbyDbSupport(){
				public SQLHandler getSQLHandler() {
			        return new DefaultSQLHandler(dataSource);
			    }
				public String getSchemaName() {
			        return ParseYamlConfig.parseYaml().getSqlMap("database");
			    }
			};
		}

		@Override
		public String getDbUnitDialect() {
			return "derby";
		}
		@Override
		public ColumnSchema getColumnSchema() {
			return null;
		}
		@Override
		public TypeMap getTypeMap() {
			return new MySQLTypeMap();
		}

	},

	UNSUPPORT() {
		@Override
		public DbSupport getDbSupport() {
			throw new RuntimeException("unsupport database type");
		}

		@Override
		public String getDbUnitDialect() {
			throw new RuntimeException("unsupport database type");
		}
		@Override
		public ColumnSchema getColumnSchema() {
			throw new RuntimeException("unsupport database type");
		}
		@Override
		public TypeMap getTypeMap() {
			throw new RuntimeException("unsupport database type");
		}
	};
	private DbType(){
		
	}
	
	private DbType(String driver,String url,String user,String passwd){
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.passwd = passwd;
	}
	
	private static DbType instance = null;
	
	public static DbType getInstance(){
		if(instance != null){
			return instance;
		}
		String type = ParseYamlConfig.parseYaml().getSqlMap("type");
		for(DbType dbType:DbType.values()){
			if(dbType.getDbUnitDialect().equalsIgnoreCase(type)){
				return dbType;
			}
		}
		return DbType.UNSUPPORT;
	}
	
	
	private String driver;
	private String url;
	private String user;
	private String passwd;
	private static BasicDataSource dataSource;
	
	public static BasicDataSource getDataSource() {
		return dataSource;
	}

	static{
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName(ParseYamlConfig.parseYaml().getSqlMap("driver"));
		dataSource.setUrl(ParseYamlConfig.parseYaml().getSqlMap("url"));
		dataSource.setUsername(ParseYamlConfig.parseYaml().getSqlMap("username"));
		dataSource.setPassword(ParseYamlConfig.parseYaml().getSqlMap("password"));
	}
	
	public String getDriver() {
		return StringUtils.isEmpty(driver) ? ParseYamlConfig.parseYaml().getSqlMap("driver") : this.driver;
	}
	public String getUrl() {
		return StringUtils.isEmpty(url) ? ParseYamlConfig.parseYaml().getSqlMap("url") : this.url;
	}
	public String getUser() {
		return StringUtils.isEmpty(user) ? ParseYamlConfig.parseYaml().getSqlMap("username") : this.user;
	}
	public String getPasswd() {
		return StringUtils.isEmpty(passwd) ? ParseYamlConfig.parseYaml().getSqlMap("password") : this.passwd;
	}
	public abstract String getDbUnitDialect();

	public abstract DbSupport getDbSupport();
	
	public abstract ColumnSchema getColumnSchema();
	
	public abstract TypeMap getTypeMap();
	
}
