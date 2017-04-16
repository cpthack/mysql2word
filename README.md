# mysql2word

快速将数据库结构导出成word文档形式的数据字典

## 如何修改配置？
 1、找到MysqlConfig类，将类中的数据库配置修改为你想要导出的数据字典的数据库信息即可</br>
 2、找到WordConfig类，将类中的文件导出地址和表格样式等配置信息修改为你所想的即可

## 如何启动？

找到Mysql2WordKit类，在eclipse下直接运行即可。

## 示例代码

> Mysql2WordKit.java

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