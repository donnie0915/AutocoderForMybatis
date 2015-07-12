package com.longtask.tea.db;

import com.longtask.tea.db.map.TypeMap;
import com.longtask.tea.db.table.ColumnSchema;
import com.longtask.tea.domain.Column;
import com.longtask.tea.yaml.PackageConfigUtil;
import com.longtask.tea.yaml.ParseYamlConfig;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.unitils.core.dbsupport.DbSupport;

import java.util.List;
import java.util.Set;
/**
 * 
 * @author longhao
 * @since 
 * @version 
 *
 */
public class DbSchemaProvider {
    private static final Logger LOGGER = LoggerFactory.getLogger(DbSchemaProvider.class);
	private static DbSupport dbSupport = DbType.getInstance().getDbSupport();
	private static ColumnSchema columnSchema = DbType.getInstance().getColumnSchema();
	private static TypeMap typeMap = DbType.getInstance().getTypeMap();
    private static String ignoreSuffix = PackageConfigUtil.getIgnoreSuffix();
    private static String ignorePrefix = PackageConfigUtil.getIgnorePrefix();

	public static boolean isTablesExsit(Set<String> tablesName){
		Set<String> set = dbSupport.getTableNames();
		return CollectionUtils.isSubCollection(tablesName, set);
	}
	
	public static List<Column> getTableColumnNames(String tableName){
		String database = ParseYamlConfig.parseYaml().getSqlMap("database");
        LOGGER.info("query table metainfo database:" + database + " tableName:"+ tableName);
		List<Column> list = columnSchema.getTableColumns(database, tableName);
		for(Column column : list){

			String columnName = column.getColumnName();
			column.setJavaName(convertJavaName(columnName));
			String dataType = column.getDateType().toUpperCase();

			String javaType = typeMap.javaTypeMap().get(dataType).getName().replace("java.lang.", "");

			if(columnName.toLowerCase().endsWith("id") && javaType.equals("Integer")){
				javaType = "Long";
			}
			column.setJavaType(javaType);
			Object value = column.getDefaultValue();
			if(value == null){
				if("String".equals(javaType)){
					value = "";
				}
				if("Integer".equals(javaType)){
					value = 0;
				}
				if("Long".equals(javaType)){
					value = 0L;
				}
				if("java.util.Date".equals(javaType)){
					value = new java.util.Date();
				}
			}
		}
		return list;
	}
    
    public static Column getPrimaryColumn(List<Column> columns){
        for (Column column : columns) {
            if (column.isPrimaryKey()) {
                return column;
            }
        }

        return null;
    }
	
	public static String convertJavaName(String name){
		name = name.toLowerCase();
		int index = name.indexOf("_");
		while(index != -1){
			name = name.substring(0, index) 
					+ Character.toUpperCase(name.charAt(index+1)) 
					+ name.substring(index+2);
			index = name.indexOf("_");
		}
		
		return name;
	}
	
	public static String convertTableNameIgnorePrefix(String tableName) {
        tableName = ignoreSuffix(tableName);
        tableName = ignorePrefix(tableName);
        return  tableName.substring(1);
    }
	
	public static String convertTableName(String tableName) {
        tableName = ignoreSuffix(tableName);
        tableName = ignorePrefix(tableName);
        tableName = convertJavaName(tableName);
        return tableName.substring(0, 1).toUpperCase() + tableName.substring(1);
    }

    private static String ignoreSuffix(String source) {
        if (source.endsWith(ignoreSuffix)) {
            return source.substring(0, source.length() - ignoreSuffix.length());
        }
        return source;
    }

    private static String ignorePrefix(String source) {
        if (source.startsWith(ignorePrefix)) {
            return source.substring(ignorePrefix.length() - 1);
        }

        return source;
    }

}
