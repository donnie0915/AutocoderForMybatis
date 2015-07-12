package com.longtask.tea.domain;

import org.apache.commons.lang.builder.ToStringBuilder;

public class Column {
    protected static final String MYSQL_PRIMARY_KEY_SIGN        = "PRI";
    protected static final String MYSQL_COLUMN_IS_NULL_ABLE     = "YES";
    protected static final String MYSQL_COLUMN_IS_NOT_NULL_ABLE = "NO";

    private String                columnName;
    private String                dateType;
    private String                columnCommit;

    private String                javaType;
    private String                javaName;
    private String                commit;
    private String                columnKey;                            //MYSQL时，该值为 PRI 时表示该字段为主键

    private Object                defaultValue;

    private Integer               columnSize;                            // 字段长度
    private String                isNullAble;                            // 字段是否必填,必填=NO 非必填=YES

    @Override
    public String toString() {
        ToStringBuilder.reflectionToString(this);
        final StringBuilder sb = new StringBuilder();
        sb.append("Column");
        sb.append("{columnName='").append(columnName).append('\'');
        sb.append(", dateType='").append(dateType).append('\'');
        sb.append(", columnCommit='").append(columnCommit).append('\'');
        sb.append(", javaType='").append(javaType).append('\'');
        sb.append(", javaName='").append(javaName).append('\'');
        sb.append(", commit='").append(commit).append('\'');
        sb.append(", columnKey='").append(columnKey).append('\'');
        sb.append(", columnSize='").append(columnSize).append('\'');
        sb.append(", isNullAble='").append(isNullAble).append('\'');
        sb.append(", defaultValue=").append(defaultValue);
        sb.append('}');
        return sb.toString();
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(Object defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }

    public String getJavaName() {
        return javaName;
    }

    public void setJavaName(String javaName) {
        this.javaName = javaName;
    }

    public String getCommit() {
        return commit;
    }

    public void setCommit(String commit) {
        this.commit = commit;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDateType() {
        return dateType;
    }

    public void setDateType(String dateType) {
        this.dateType = dateType;
    }

    public String getColumnCommit() {
        return columnCommit;
    }

    public void setColumnCommit(String columnCommit) {
        this.columnCommit = columnCommit;
    }

    public String getColumnKey() {
        return columnKey;
    }

    public void setColumnKey(String columnKey) {
        this.columnKey = columnKey;
    }

    public Integer getColumnSize() {
        return columnSize;
    }

    public void setColumnSize(Integer columnSize) {
        this.columnSize = columnSize;
    }

    public String getIsNullAble() {
        return isNullAble;
    }

    public void setIsNullAble(String isNullAble) {
        this.isNullAble = isNullAble;
    }

    public boolean isPrimaryKey() {
        if (this.getColumnKey() != null && !this.getColumnKey().trim().equals("")
            && this.getColumnKey().equals(MYSQL_PRIMARY_KEY_SIGN)) {
            return true;
        }
        return false;
    }

}
