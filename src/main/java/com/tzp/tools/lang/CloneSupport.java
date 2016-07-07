package com.tzp.tools.lang;

import java.lang.*;

/**
 * 克隆支持类，提供默认的克隆方法
 * @author i
 *
 * @param <T>
 */
public class CloneSupport<T> implements java.lang.Cloneable {
	
	@SuppressWarnings("unchecked")
	@Override
	public T clone() {
		try {
			return (T) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}
	
}
