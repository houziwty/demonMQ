package DemonMQ;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;


import org.springframework.jms.core.JmsTemplate;

import DemonMQ.common.SpringUtil;

public class ProxyJMSConsumer {


private static Receiver receiver=SpringUtil.getBean("receiver", Receiver.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	        System.out.print(receiver.receiveMessage());
	        
	        System.out.println("初始化消息消费者");

	}

}
