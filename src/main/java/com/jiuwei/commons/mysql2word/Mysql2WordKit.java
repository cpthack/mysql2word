package com.jiuwei.commons.mysql2word;

import java.util.List;

import org.apache.log4j.Logger;

import com.jiuwei.commons.mysql2word.mysql.MysqlKit;
import com.jiuwei.commons.mysql2word.mysql.MysqlTable;
import com.jiuwei.commons.mysql2word.word.WordKit;

public class Mysql2WordKit {
	private final static Logger logger = Logger.getLogger(Mysql2WordKit.class);
	private final static boolean isDebug = false;

	public static void main(String[] arg) throws Exception {
		long startTime = System.currentTimeMillis();
		logger.info("starting to export mysql table info....");
		List<MysqlTable> tableList = MysqlKit.getTableInfos();
		if (isDebug) {
			MysqlKit.consoleInfo();// 输出表格信息
		}
		logger.info("获取mysql 表格结构成功...");
		logger.info("开始将mysql表结果信息写入word文档中....");
		WordKit wordKit = new WordKit();
		wordKit.writeTableToWord(tableList);
		logger.info("export successfully!....");
		logger.debug("本次导出总耗时：" + (System.currentTimeMillis() - startTime)
				/ 1000 + " s");
	}

}
