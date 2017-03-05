package com.jiuwei.commons.mysql2word.kit;

public class StringKit {

	// 判断数组中是否存在某个字符串
	public static String isExistInArray(String[] str, String source) {
		for (String s : str) {
			if (s.equals(source)) {
				return s;
			} else
				continue;
		}
		return null;
	}
}
