package com.vega.logs.factory;

import org.slf4j.Logger;
import org.slf4j.helpers.Util;

public class LoggerForMQFactory {
	public static Logger getLogger(Class<?> clazz,String clientId) {
		Logger logger = getLogger(clazz.getName());
		
		return logger;
	}
}
