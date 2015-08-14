package DemonMQ;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;

import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import DemonMQ.common.SpringUtil;

public class TestSender {

	private static Sender sender = SpringUtil.getBean("sender", Sender.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		sender.sendInfo();
	}

}
