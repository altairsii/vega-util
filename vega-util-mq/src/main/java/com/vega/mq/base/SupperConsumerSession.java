package com.vega.mq.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.sql.PooledConnection;

import com.vega.base.constants.BaseConstants;
import com.vega.mq.constants.ActiveMQConstant;

public abstract class SupperConsumerSession implements MessageListener {


	int corePoolSize ;// 池中所保存的线程数，包括空闲线程
	int maximumPoolSize;// 池中允许的最大线程数。
	long keepAliveTime;// 当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间

	// 线程池
	private ExecutorService pool = null;
	public PooledConnection connection;
	private Session session;
	private MessageConsumer consumer;
	public MessageConsumer getConsumer() {
		return consumer;
	}

	public void setConsumer(MessageConsumer consumer) {
		this.consumer = consumer;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public PooledConnection getConnection() {
		return connection;
	}

	public void setConnection(PooledConnection connection) {
		this.connection = connection;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SupperConsumerSession() {

	}

	public SupperConsumerSession(int corePoolSize, int maximumPoolSize, long keepAliveTime, String replyType) {
		if (BaseConstants.ANC.equals(replyType)) {// 异步
			this.corePoolSize = corePoolSize < ActiveMQConstant.DEFONE? ActiveMQConstant.DEFCOREPOOLSIZE : corePoolSize;
			this.maximumPoolSize = maximumPoolSize < ActiveMQConstant.DEFONE ? ActiveMQConstant.DEFMAXIMUMPOOLSIZE : maximumPoolSize;
			this.keepAliveTime = keepAliveTime < ActiveMQConstant.DEFONE ? ActiveMQConstant.DEFKEEPALIVETIME : keepAliveTime;
			createThread(this.corePoolSize, this.maximumPoolSize, this.keepAliveTime);
		}
	}

	private void createThread(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
		if (null != pool) {
			try {
				pool.shutdown();
			} catch (Exception e) {
			}
		}
		pool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(),
				new ThreadPoolExecutor.CallerRunsPolicy());
	}

	public void onMessage(Message message) {
		if (null != pool) {//异步
			final Message syncMessage = message;
			pool.execute(new Runnable() {
				@Override
				public void run() {
					onMessageDo(syncMessage);
				}
			});
		}else{//同步
			onMessageDo(message);
		}
	}

	public abstract void onMessageDo(Message message) ;

	/**
	 * 关闭线程池
	 */
	public void stopExecutorService() {
		if (null == pool) {
			return;
		}
		try {
			pool.shutdownNow();
		} catch (Exception e) {
		}
	}

}
