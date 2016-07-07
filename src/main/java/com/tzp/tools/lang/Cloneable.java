package com.tzp.tools.lang;

/**
 * 克隆支持接口
 * @author i
 *
 * @param <T>
 */
public interface Cloneable<T> extends java.lang.Cloneable{
	T clone();
}
