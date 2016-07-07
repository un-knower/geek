package com.tzp.tools.db.dialect.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import com.tzp.tools.db.DbUtil;
import com.tzp.tools.db.Entity;
import com.tzp.tools.db.Page;
import com.tzp.tools.db.sql.Order;
import com.tzp.tools.db.sql.SqlBuilder;
import com.tzp.tools.db.sql.Wrapper;
import com.tzp.tools.db.sql.SqlBuilder.LogicalOperator;
import com.tzp.tools.exceptions.DbRuntimeException;
import com.tzp.tools.util.StrUtil;

/**
 * MySQL方言
 * @author i
 *
 */
public class MysqlDialect extends AnsiSqlDialect{
	
	public MysqlDialect() {
		wrapper = new Wrapper('`');
	}

	@Override
	public PreparedStatement psForPage(Connection conn, Collection<String> fields, Entity where, Page page) throws SQLException {
		//验证
		if(where == null || StrUtil.isBlank(where.getTableName())) {
			throw new DbRuntimeException("Table name is null !");
		}
		
		final SqlBuilder find = SqlBuilder.create(wrapper)
				.select(fields)
				.from(where.getTableName())
				.where(LogicalOperator.AND, DbUtil.buildConditions(where));
		
		final Order[] orders = page.getOrders();
		if(null != orders){
			find.orderBy(orders);
		}
		
		find.append(" LIMIT ").append(page.getStartPosition()).append(", ").append(page.getNumPerPage());
		
		final PreparedStatement ps = conn.prepareStatement(find.build());
		DbUtil.fillParams(ps, find.getParamValueArray());
		return ps;
	}
}
