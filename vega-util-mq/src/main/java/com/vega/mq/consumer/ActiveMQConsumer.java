package com.vega.mq.consumer;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import com.vega.mq.base.MQSession;

/**
 * 建立消费者链接
 * @author wangyongbing
 *
 */
public class ActiveMQConsumer {

	public static MessageConsumer getActiveMQConsumer(String url, int confirm) throws JMSException {
		Session session = MQSession.getMQSession(url, confirm);
		Destination destination = session.createTopic("");
		return session.createConsumer(destination);
	}
}
