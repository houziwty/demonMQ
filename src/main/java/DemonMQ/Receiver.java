package DemonMQ;

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

import DemonMQ.common.ReceiverBase;

public class Receiver extends ReceiverBase {


	/**
	 * 构造函数
	 */
	public Receiver() {
	}

	public String receiveMessage() {
		String my = "";
		MapMessage message = (MapMessage) jmsTemplate.receive();
		
		try {
			if (message != null)
				my = message.getString("lastName");
		} catch (JMSException e) {
			throw JmsUtils.convertJmsAccessException(e);
		}
		return my;
	}

}
