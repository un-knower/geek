package com.tzp.tools.db.dialect.impl;

import com.tzp.tools.db.sql.Wrapper;

/**
 * SqlLite3方言
 * @author i
 *
 */
public class Sqllite3Dialect extends AnsiSqlDialect{
	public Sqllite3Dialect() {
		wrapper = new Wrapper('[', ']');
	}
}
