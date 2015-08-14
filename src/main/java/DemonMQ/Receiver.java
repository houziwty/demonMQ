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

public class Receiver {

	   private JmsTemplate jmsTemplate;
	    //getter and setter
	    public JmsTemplate getJmsTemplate() {
	        return jmsTemplate;
	    }
	    public void setJmsTemplate(JmsTemplate jmsTemplate) {
	        this.jmsTemplate = jmsTemplate;
	    }
	    
	    /**
	     * 构造函数
	     */
	    public Receiver() {
	    }

	    public String receiveMessage() {
	        String my = "";
	        MapMessage message = (MapMessage) jmsTemplate.receive();
	        try {
	            my = message.getString("lastName");
	        } catch (JMSException e) {
	            throw JmsUtils.convertJmsAccessException(e);
	        }
	        return my;
	    }


}
