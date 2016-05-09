package com.vega.mq.base;

import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.sql.PooledConnection;

public class SupperProducerSession {
	private PooledConnection connection;
	private MessageProducer producer;
	private Session session;

	public PooledConnection getConnection() {
		return connection;
	}

	public void setConnection(PooledConnection connection) {
		this.connection = connection;
	}

	public MessageProducer getProducer() {
		return producer;
	}

	public void setProducer(MessageProducer producer) {
		this.producer = producer;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
