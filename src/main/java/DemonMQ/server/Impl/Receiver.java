package demonmq.server.Impl;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.JmsUtils;

import demonmq.common.MQBase;
public class Receiver extends MQBase {


	/**
	 * 构造函数
	 */
	public Receiver() {	
		key="lastName";
	}
	@Override
	public String handleMessage() {
		String my = "";
		MapMessage message = (MapMessage) jmsTemplate.receive();
		try {
			if (message != null)
				my = message.getString(getKey());
		} catch (JMSException e) {
			throw JmsUtils.convertJmsAccessException(e);
		}
		return my;
	}

}
