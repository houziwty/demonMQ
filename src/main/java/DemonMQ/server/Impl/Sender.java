package DemonMQ.server.Impl;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class Sender {

	    private JmsTemplate jmsTemplate;
	    //getter and setter
	    public JmsTemplate getJmsTemplate() {
	        return jmsTemplate;
	    }
	    public void setJmsTemplate(JmsTemplate jmsTemplate) {
	        this.jmsTemplate = jmsTemplate;
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
}
