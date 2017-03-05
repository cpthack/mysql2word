package com.jiuwei.commons.mysql2word.mysql;

import java.util.ArrayList;
import java.util.List;

public class MysqlTable {

	private String title;// 表名称

	private String tableName;// 数据库表名

	private int fieldCount = MysqlConfig.fieldCount;

	private String[] fieldValues = null;// 表字段值
	private List<String[]> fieldList = new ArrayList<String[]>();

	public MysqlTable(String title) {
		this.title = title;
	}

	// 添加单个字段内容
	int cursor = 0;

	public void addSingleField(String fieldValue) {
		if (cursor == fieldCount) {
			addRowField(fieldValues);
			fieldValues = new String[fieldCount];
			cursor = 0;
		}
		if (fieldValues == null || fieldValues.length <= 0) {
			fieldValues = new String[fieldCount];
			fieldValues[cursor] = fieldValue;
			cursor += 1;
		} else {
			fieldValues[cursor] = fieldValue;
			cursor += 1;
		}
	}

	// 添加一行字段的内容
	public void addRowField(String[] rowField) {
		fieldList.add(rowField);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getFieldCount() {
		return fieldCount;
	}

	public void setFieldCount(int fieldCount) {
		this.fieldCount = fieldCount;
	}

	public String[] getFieldValues() {
		return fieldValues;
	}

	public void setFieldValues(String[] fieldValues) {
		this.fieldValues = fieldValues;
	}

	public List<String[]> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<String[]> fieldList) {
		this.fieldList = fieldList;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public int getCursor() {
		return cursor;
	}

	public void setCursor(int cursor) {
		this.cursor = cursor;
	}

}
