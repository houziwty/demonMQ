package DemonMQ;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;

import DemonMQ.common.MQBase;
import DemonMQ.common.SpringUtil;
import DemonMQ.server.Impl.Receiver;

public class TestReceiver {

	private static MQBase receiver = SpringUtil.getBean("receiver",
			Receiver.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("初始化消息消费者");
		for (;;) {
			String msg=receiver.handleMessage();
		if (!msg.equals(""))
				System.out.println(msg);
		}

	}

}
