package com.tzp.tools.lang;

import com.tzp.tools.util.StrUtil;

/**
 * 命令行工具方法类
 * @author i
 *
 */
public class Console {
	
	/**
	 * 同 System.out.println()方法，打印控制台日志
	 * @param template 文本模板，被替换的部分用 {} 表示
	 * @param values 值
	 */
	public static void log(String template, Object... values){
		System.out.println(StrUtil.format(template, values));
	}
	
	/**
	 * 同 System.err.println()方法，打印控制台日志
	 * @param template 文本模板，被替换的部分用 {} 表示
	 * @param values 值
	 */
	public static void error(String template, Object... values){
		System.err.println(StrUtil.format(template, values));
	}
}
