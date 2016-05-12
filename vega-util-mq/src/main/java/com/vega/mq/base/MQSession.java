package com.vega.mq.base;

import java.util.HashMap;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnection;
import org.apache.activemq.pool.PooledConnectionFactory;

import com.vega.mq.constants.ActiveMQConstant;

public class MQSession {

	/**
	 * 全局唯一的工厂控制
	 */
	private static Map<String, PooledConnection> factorys = new HashMap<String, PooledConnection>();

	/**
	 * 链接工厂
	 */
	private static ActiveMQConnectionFactory connectionFactory;

	private static PooledConnection connection;

	/**
	 * 通过url获取Session
	 * 
	 * @param url 
	 * @param confirm 消息确认类型（Session.ACK_MODE ）
	 * @return
	 * @throws JMSException
	 */
	public static Session getMQSession(String url, String clientId,int confirm) throws JMSException {
		connection = ((PooledConnection) getConnection(url, clientId));
		connection.start();
		return connection.createSession(false, confirm);
	}

	/**
	 * 通过url获取连接工厂
	 * @param url
	 * @return
	 * @throws JMSException 
	 */
	private static PooledConnection getConnection(String url,String clientId) throws JMSException {
		if (factorys.containsKey(url)) {
			return factorys.get(url);
		}
		connectionFactory = new ActiveMQConnectionFactory(url);
		PooledConnectionFactory pooledFactory = new PooledConnectionFactory(connectionFactory);
		pooledFactory.setMaxConnections(ActiveMQConstant.MAX_CONNECTIONS);
		pooledFactory.setMaximumActiveSessionPerConnection(ActiveMQConstant.DEFAULT_MAXIMUM_ACTIVE_SESSION_PER_CONNECTION);
		pooledFactory.setIdleTimeout(ActiveMQConstant.DEFAULT_IDLETIMEOUT);
		pooledFactory.createConnection();
		connection.setClientID(clientId);
		connection.start();
		factorys.put(url, connection);
		return connection;
	}
}
