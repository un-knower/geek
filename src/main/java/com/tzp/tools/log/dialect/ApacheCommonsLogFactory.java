package com.tzp.tools.log.dialect;

import com.tzp.tools.log.Log;
import com.tzp.tools.log.LogFactory;

/**
 *  Apache Commons Logging
 * @author i
 *
 */
public class ApacheCommonsLogFactory extends LogFactory{
	
	public ApacheCommonsLogFactory() {
		super("Apache Common Logging");
	}

	@Override
	public Log getLog(String name) {
		return new ApacheCommonsLog(name);
	}

	@Override
	public Log getLog(Class<?> clazz) {
		return new ApacheCommonsLog(clazz);
	}

}
