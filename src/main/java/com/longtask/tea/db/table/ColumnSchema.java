package com.longtask.tea.db.table;

import java.util.List;

import com.longtask.tea.domain.Column;

public interface ColumnSchema {
	public List<Column> getTableColumns(String database,String tableName);
}
