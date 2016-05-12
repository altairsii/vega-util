package com.vega.mq.producer;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

import com.vega.mq.base.MQSession;

/**
 * 建立生产者链接
 * @author wangyongbing
 *
 */
public class ActiveMQProducer {

	public static MessageProducer getActiveMQProducer(String url, int confirm) throws JMSException {
		Session session = MQSession.MQSession(url, confirm);
		Destination destination = session.createTopic("");
		return session.createProducer(destination);
	}

}
