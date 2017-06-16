package demonmq.server.Impl;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import demonmq.common.MQBase;

public class Sender extends MQBase {

	public Sender() {
		key = "lastName";
	}

	public void sendInfo() {
		jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				MapMessage message = session.createMapMessage();
				message.setString("lastName", "ppp");
				System.out.println("发送成功");
				return message;
			}

		});
	}

	@Override
	public Boolean handleMessage() {
		 Boolean result = false;

		try {
			jmsTemplate.send(new MessageCreator() {

				@Override
				public Message createMessage(Session session)
						throws JMSException {
					MapMessage message = session.createMapMessage();
					message.setString(getKey(), "test");

					return message;
				}
			});
			result = true;
		} catch (Exception ex) {
			result = false;
		} finally {

		}
		return result;
	}
}
