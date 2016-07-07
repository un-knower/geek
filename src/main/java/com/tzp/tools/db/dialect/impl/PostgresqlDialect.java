package com.tzp.tools.db.dialect.impl;

import com.tzp.tools.db.sql.Wrapper;


/**
 * Postgree方言
 * @author i
 *
 */
public class PostgresqlDialect extends AnsiSqlDialect{
	public PostgresqlDialect() {
		wrapper = new Wrapper('"');
	}

}
