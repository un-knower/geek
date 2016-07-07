package com.tzp.tools.log.dialect;

import java.io.InputStream;
import java.util.logging.LogManager;

import com.tzp.tools.log.Log;
import com.tzp.tools.log.LogFactory;
import com.tzp.tools.util.IoUtil;

/**
 *  <a href="http://java.sun.com/javase/6/docs/technotes/guides/logging/index.html">java.util.logging</a> log.
 * @author i
 *
 */
public class JdkLogFactory extends LogFactory{
	
	public JdkLogFactory() {
		super("JDK Logging");
		readConfig();
	}

	@Override
	public Log getLog(String name) {
		return new JdkLog(name);
	}

	@Override
	public Log getLog(Class<?> clazz) {
		return new JdkLog(clazz);
	}

	/**
	 * 读取ClassPath下的logging.properties配置文件
	 */
	private void readConfig() {
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("logging.properties");
		if(null == in){
			System.err.println("[WARN] Can not find [logging.properties], use [%JRE_HOME%/lib/logging.properties] as default!");
			return;
		}
		
		try {
			LogManager.getLogManager().readConfiguration(in);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				LogManager.getLogManager().readConfiguration();
			} catch (Exception e1) {
				e.printStackTrace();
			}
		} finally {
			IoUtil.close(in);
		}
	}
}
