package com.vega.logs.factory;

import org.slf4j.Logger;

import com.vega.logs.LOG;

public class LOGFactory {

	private static LOG logger;
	
	public static Logger getLogger(Class<?> clazz){
		if(null == logger){
			logger = new LOG();
		}
		return logger.getLOG(clazz);
	}
	
	public static Logger getLogger(Class<?> clazz, String clientId){
		if(null == logger){
			logger = new LOG();
		}
		return logger.getLOG(clazz, clientId);
	}
}
