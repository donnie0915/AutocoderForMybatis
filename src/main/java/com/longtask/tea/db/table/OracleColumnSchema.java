package com.longtask.tea.db.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.longtask.tea.db.DbType;
import com.longtask.tea.domain.Column;

public class OracleColumnSchema implements ColumnSchema {

	@Override
	public List<Column> getTableColumns(String database,String tableName) {
		String sql = "select DATA_TYPE,COLUMN_NAME,COLUMN_COMMENT from information_schema.columns where table_name = ? and table_schema = ?";
		try {
			List<?> rows = new JdbcTemplate(DbType.getDataSource()).queryForList(sql,
					new Object[] { tableName, database});
			List<Column> list = new ArrayList<Column>();
			for(int i = 0;i<rows.size();i++){
				Map<?,?> map = (Map<?,?>)rows.get(i);
				Column column = new Column();
				column.setColumnCommit((String)(map.get("COLUMN_COMMENT")));
				column.setColumnName((String)(map.get("COLUMN_NAME")));
				column.setDateType((String)(map.get("DATA_TYPE")));
				list.add(column);
			}
			return list;
		}catch(Exception e){
			return null;
		}
	}


}
