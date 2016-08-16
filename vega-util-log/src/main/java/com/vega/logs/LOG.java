package com.vega.logs;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vega.logs.logutils.LOGForLogback;
import com.vega.logs.logutils.LOGForMQ;

/**
 * 
 * 统一的日志工具类
 * 后续补充功能
 * 1.支持mq
 * 2.支持logback
 * 
 * @author wangyongbing
 *
 */
public class LOG {

	Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	/**
	 * 通过logback输出日志
	 * @param clazz
	 * @return
	 */
	public Logger getLOG(Class<?> clazz) {
		return LOGForLogback.getLogger(clazz);
	}

	/**
	 * 通过MQ输出日志
	 * 
	 * @param clazz
	 * @param queueName
	 * @return
	 */
	public Logger getLOG(Class<?> clazz, String clientId) {
		return LOGForMQ.getLogger(clazz, clientId);
	}
}
