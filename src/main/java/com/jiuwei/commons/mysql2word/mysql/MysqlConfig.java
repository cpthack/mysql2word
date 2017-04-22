package com.jiuwei.commons.mysql2word.mysql;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;

public abstract class MysqlConfig {
	private final static Logger logger = Logger.getLogger(MysqlConfig.class);

	@SuppressWarnings("serial")
	public static LinkedHashMap<String, String> tableRelation = new // 表结构信息
	LinkedHashMap<String, String>() {
		{
			this.put("COLUMN_NAME", "字段名称");
			this.put("TYPE_NAME", "字段类型");
			this.put("COLUMN_DEF", "默认值");
			this.put("COLUMN_SIZE", "字段长度");
			this.put("REMARKS", "备注信息");
		}
	};

	@SuppressWarnings("serial")
	public static LinkedHashMap<String, String> tableIndex = new // 表索引信息
	LinkedHashMap<String, String>() {
		{
			this.put("INDEX_NAME", "索引名称");
			this.put("non_unique", "是否唯一");
		}
	};
	public final static boolean IS_ADD_TABLE_INDEX = false;// 是否输出索引信息

	@SuppressWarnings("serial")
	public static LinkedHashMap<String, String> tableInfo = new // 总表信息
	LinkedHashMap<String, String>() {
		{
			this.putAll(tableRelation);
			if (IS_ADD_TABLE_INDEX)
				this.putAll(tableIndex);
		}
	};

	public static int fieldCount = tableInfo.keySet().size();// 当前字段数量

	private static final String HOST = "192.168.1.117";// 需要修改此处
	private static final String PORT = "3306";// 需要修改此处
	private static final String DATABASE = "jzmao";// 需要修改此处
	private static final String USER = "root";// 需要修改此处
	private static final String PWD = "";// 需要修改此处

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/"
			+ DATABASE + "?user=" + USER + "&password=" + PWD
			+ "&useUnicode=true&characterEncoding=UTF-8";

	protected static Connection dbConnection = null;
	protected static DatabaseMetaData dbmd = null;
	static {
		logger.debug("starting to 初始化数据库连接信息...");
		dbConnection = getConnection();
		try {
			dbmd = dbConnection.getMetaData();
			logger.debug("初始化数据库成功....");
		} catch (SQLException e) {
			logger.error("初始化数据库连接失败>>>", e);
		}
	}

	private static Connection getConnection() {
		try {
			Class.forName(DRIVER);
			dbConnection = DriverManager.getConnection(URL);
			logger.debug("mysql url is >>>" + URL);
		} catch (Exception e) {
			logger.error("获取数据库连接失败>>>", e);
		}
		return dbConnection;
	}
}
