package com.tzp.tools.log.dialect;

import com.tzp.tools.log.Log;
import com.tzp.tools.log.LogFactory;

/**
 * 利用System.out.println()打印日志
 * @author i
 *
 */
public class ConsoleLogFactory extends LogFactory {
	
	public ConsoleLogFactory() {
		super("Hutool Console Logging");
	}

	@Override
	public Log getLog(String name) {
		return new ConsoleLog(name);
	}

	@Override
	public Log getLog(Class<?> clazz) {
		return new ConsoleLog(clazz);
	}

}
