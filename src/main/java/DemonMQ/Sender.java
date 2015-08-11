package DemonMQ;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Sender {

	private static final int SEND_NUMBER = 5;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// ConnectionFactory ：连接工厂，JMS 用它创建连接
		ConnectionFactory connectionFactory;

		// Connection ：JMS 客户端到JMS Provider 的连接
		Connection connection = null;

		// Session： 一个发送或接收消息的线程
		Session session;

		// Destination ：消息的目的地;消息发送给谁.
		Destination destination;

		// MessageProducer：消息发送者
		MessageProducer producer;

		connectionFactory = new ActiveMQConnectionFactory(
				ActiveMQConnection.DEFAULT_USER,
				ActiveMQConnection.DEFAULT_PASSWORD, "tcp://127.0.0.1:61616");
		try {
			connection = connectionFactory.createConnection();

			connection.start();

			// 获取操作连接
			session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

			// 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
			destination = session.createQueue("FirstQueue");

			// 得到消息生成者【发送者】
			producer = session.createProducer(destination);

			// 设置不持久化，此处学习，实际根据项目决定
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

			sendMessage(session, producer);

			session.commit();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (null != connection)
					connection.close();
			} catch (Throwable ignore) {
			}
		}

	}

	private static void sendMessage(Session session, MessageProducer producer)
			throws JMSException {
		// TODO Auto-generated method stub
		for (int i = 1; i <= SEND_NUMBER; i++) {
			TextMessage message = session
					.createTextMessage("ActiveMq 发送消息" + i);
			System.out.println("发送消息:" + "ActiveMq 发送消息" + i);
			producer.send(message);
		}
	}
}
