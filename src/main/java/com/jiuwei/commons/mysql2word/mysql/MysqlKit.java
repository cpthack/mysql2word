package com.jiuwei.commons.mysql2word.mysql;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import com.jiuwei.commons.mysql2word.kit.StringKit;
import com.mysql.jdbc.StringUtils;

public class MysqlKit extends MysqlConfig {
	private final static Logger logger = Logger.getLogger(MysqlKit.class);
	private static List<MysqlTable> tableList = null;

	public static List<MysqlTable> getTableInfos() {
		logger.debug("starting to get table info...");
		if (tableList == null) {
			initTableInfos();
		}
		return tableList;
	}

	private static void initTableInfos() {
		tableList = new ArrayList<MysqlTable>();
		try {
			ResultSet tableResultSet = dbmd.getTables(null, "%", "%",
					new String[] { "TABLE" });// 表数据集
			MysqlTable mysqlTable = null;

			while (tableResultSet.next()) {
				String tableName = tableResultSet.getString("TABLE_NAME");
				if (!StringUtils.isNullOrEmpty(tableName)) {
					ResultSet rs = dbmd.getColumns(null, "%", tableName, "%");
					mysqlTable = new MysqlTable(tableName);
					mysqlTable.setTableName(tableName);
					while (rs.next()) {
						for (String fieldName : tableRelation.keySet()) {
							String fieldValue = rs.getString(fieldName);
							if (!StringUtils.isNullOrEmpty(fieldValue)
									&& fieldValue.equals("id")) {
								mysqlTable.setTitle(mysqlTable.getTitle()
										+ rs.getString("REMARKS"));
							}
							mysqlTable.addSingleField(rs.getString(fieldName));
						}

					}
					tableList.add(mysqlTable);
				}// end if

			}// end while

			if (IS_ADD_TABLE_INDEX)
				addIndexInfo();

		} catch (Exception e) {
			logger.error("获取数据库表信息失败>>>", e);
		}
	}

	// 添加索引信息(有BUG，待优化)
	public static void addIndexInfo() throws Exception {
		ResultSet rs = null;
		for (MysqlTable mysqlTable : tableList) {
			rs = dbmd.getIndexInfo(null, null, mysqlTable.getTableName(),
					false, false);
			for (String fieldName : tableIndex.keySet()) {
				List<String[]> fieldList = new ArrayList<String[]>(mysqlTable
						.getFieldList().size());
				Collections.copy(fieldList, mysqlTable.getFieldList());
				for (String[] fieldValues : fieldList) {
					logger.debug(mysqlTable.getCursor());
					mysqlTable.setCursor(mysqlTable.getCursor());
					mysqlTable.setFieldValues(fieldValues);
					while (rs.next()) {
						String columnName = rs.getString("Column_name");
						if (StringKit.isExistInArray(fieldValues, columnName) != null) {
							mysqlTable.addSingleField(rs.getString(fieldName));
						} else {
							mysqlTable.addSingleField("");
						}
					}
					// mysqlTable.getFieldList().remove(fieldValues);
				}
			}

		}
	}

	public static void consoleInfo() {
		// 输出所有表信息
		for (MysqlTable table : tableList) {
			logger.debug("表名：" + table.getTitle());
			for (String fieldTile : tableInfo.values()) {
				logger.debug(fieldTile + "	\n");
			}

			for (String[] fieldValues : table.getFieldList()) {
				for (String fieldValue : fieldValues) {
					logger.debug(fieldValue + " 	\n");
				}
				logger.debug("---");
			}
		}
	}

}
