package demonmq.base;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import demonmq.util.MQConfig;




public class JmsSendBase {

	private String subject;
	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubject() {
		return subject;
	}
	

	private String user =MQConfig.getUser();//ActiveMQConnection.DEFAULT_USER;
	private String passWord = MQConfig.getPassWord();//ActiveMQConnection.DEFAULT_PASSWORD;
	private String url = MQConfig.getAddress() + ":" + MQConfig.getPort();// ActiveMQConnection.DEFAULT_BROKER_URL;

	private Connection conn = null;
	protected Session session = null;

	// 消费者，消息接收者
	protected MessageProducer producer = null;
	// Destination ：消息的目的地;消息发送给谁.
	static Destination destination = null;// MQConfig.isDestination();

	private boolean stop = false;

	// 初始化
	protected void initialize() throws JMSException, Exception {
		
		// 连接工厂
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				user, passWord, url);
		conn = connectionFactory.createConnection();
		conn.start();
		// 事务性会话，自动确认消息
		session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 消息的目的地（Queue/Topic）
		if (MQConfig.isDestination()) {
			destination = session.createQueue(subject);
		} else {
			destination = session.createTopic(subject);
		}

		// 消息的提供者（生产者）
		producer = session.createProducer(destination);

		if (MQConfig.isDelivery()) {
			// 不持久化消息
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		} else {
			//持久化
			producer.setDeliveryMode(DeliveryMode.PERSISTENT);
		}

	}

	protected void start() throws JMSException {

		conn.start();

	}

	// 关闭连接
	public void close() throws JMSException {
		if (producer != null)
			producer.close();
		if (session != null)
			session.close();
		if (conn != null)
			conn.close();
	}

}
