package com.vega.mq.base;

import java.util.HashMap;
import java.util.Map;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageListener;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.support.FactoryBeanRegistrySupport;

public class MQSession {

	/**
	 * 全局唯一的工厂控制
	 */
	private static Map<String, ConnectionFactory> factorys = new HashMap<String, ConnectionFactory>();

	/**
	 * 链接工厂
	 */
	private static ConnectionFactory connectionFactory;

	private static Connection connection;

	/**
	 * 通过url获取Session
	 * 
	 * @param url 
	 * @param confirm 消息确认类型（Session.ACK_MODE ）
	 * @return
	 * @throws JMSException
	 */
	public static Session MQSession(String url, int confirm) throws JMSException {
		connection = getConnection(url).createConnection();
		connection.start();
		return connection.createSession(false, confirm);
	}

	/**
	 * 通过url获取连接工厂
	 * @param url
	 * @return
	 */
	private static ConnectionFactory getConnection(String url) {
		if (factorys.containsKey(url)) {
			return factorys.get(url);
		}
		connectionFactory = new ActiveMQConnectionFactory(url);
		factorys.put(url, connectionFactory);
		return connectionFactory;
	}
}
