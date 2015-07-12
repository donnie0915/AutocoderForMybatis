package com.longtask.tea.db.table;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.longtask.tea.db.DbType;
import com.longtask.tea.domain.Column;

public class MysqlColumnSchema implements ColumnSchema {

	@Override
	public List<Column> getTableColumns(String database,String tableName) {
		String sql = "select DATA_TYPE,COLUMN_NAME,COLUMN_COMMENT,COLUMN_DEFAULT,COLUMN_KEY,CHARACTER_MAXIMUM_LENGTH,IS_NULLABLE from information_schema.columns where table_name = ? and table_schema = ?";
		try {
			List<?> rows = new JdbcTemplate(DbType.getDataSource()).queryForList(sql,
					new Object[] { tableName, database});
			List<Column> list = new ArrayList<Column>();
			for(int i = 0;i<rows.size();i++){
                Map<?, ?> map = (Map<?, ?>) rows.get(i);
                Column column = new Column();
                column.setColumnCommit((String) (map.get("COLUMN_COMMENT")));
                column.setColumnName((String) (map.get("COLUMN_NAME")));
                column.setDateType((String) (map.get("DATA_TYPE")));
                column.setDefaultValue((map.get("COLUMN_DEFAULT")));
                column.setColumnKey((String) map.get("COLUMN_KEY"));
                
                // add by wufan 2014-04-04 start
                column.setIsNullAble((String) map.get("IS_NULLABLE"));
                if(null != map.get("CHARACTER_MAXIMUM_LENGTH")){
                    BigInteger MAXIMUM_LENGTH =  (BigInteger)map.get("CHARACTER_MAXIMUM_LENGTH") ;
                    column.setColumnSize(Integer.valueOf(MAXIMUM_LENGTH.toString()));
                }
                // add by wufan end

				list.add(column);
			}
			return list;
		}catch(Exception e){
			return null;
		}
	}


}
