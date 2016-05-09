package com.vega.mq.consumer;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * 建立消费者链接
 * @author wangyongbing
 *
 */
public class ActiveMQConsumer {

	/**
	 * 链接工厂
	 */
	private static ConnectionFactory connectionFactory ; 
	
	private static Connection connection;
	
	private static Session session;
	
	private static Destination destination;
	
	private static MessageConsumer msgConsumer;
	
	public static void getActiveMQConsumer(String url,MessageListener  listener) throws JMSException{
		connectionFactory = new ActiveMQConnectionFactory(url);
		connection = connectionFactory.createConnection();
		connection.start();
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		destination = session.createTopic("");
		msgConsumer = session.createConsumer(destination);
		msgConsumer.setMessageListener(listener);
	}
}
