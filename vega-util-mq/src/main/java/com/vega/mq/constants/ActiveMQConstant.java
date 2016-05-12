package com.vega.mq.constants;

public interface ActiveMQConstant {
	/**
	 * 判断数字的常量1
	 */
	public static final int DEFONE = 1;

	/**
	 * 判断数字的常量0
	 */
	public static final int DEFZERO = 0;

	/**
	 * 线程池默认核心数
	 */
	public static final int DEFCOREPOOLSIZE = 1;

	/**
	 * 线程池默认最大数
	 */
	public static final int DEFMAXIMUMPOOLSIZE = 5;

	/**
	 * 线程池空闲线程默认存活时间
	 */
	public static final int DEFKEEPALIVETIME = 30;

	int MAX_CONNECTIONS = 100;

	int DEFAULT_MAXIMUM_ACTIVE_SESSION_PER_CONNECTION = 300;

	int DEFAULT_IDLETIMEOUT = 0;

}
