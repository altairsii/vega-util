package com.vega.mq.listener;

import javax.jms.Message;
import javax.jms.MessageListener;

public class ActiveMQListener implements MessageListener  {

	@Override
	public void onMessage(Message arg0) {
		System.out.println("hello,activemq");
	}

}
