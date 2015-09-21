package DemonMQ.base;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import DemonMQ.util.MQConfig;


public class JmsReceiverBase {
	private String user = MQConfig.getUser();// ActiveMQConnection.DEFAULT_USER;
	private String passWord = MQConfig.getPassWord();// ActiveMQConnection.DEFAULT_PASSWORD;
	private static String url = MQConfig.getAddress() + ":"
			+ MQConfig.getPort();

	private String subject;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	protected Destination dest = null;
	private Connection conn = null;
	protected Session session = null;
	protected MessageConsumer consumer = null;

	// private boolean stop = false;

	// 初始化
	protected void initialize() throws JMSException, Exception {

		// 连接工厂是用户创建连接的对象.
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
				user, passWord, url);
		// 连接工厂创建一个jms connection
		conn = connectionFactory.createConnection();
		conn.start();
		// 是生产和消费的一个单线程上下文。会话用于创建消息的生产者，消费者和消息。会话提供了一个事务性的上下文。
		session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE); // 不支持事务
		// 目的地是客户用来指定他生产消息的目标还有他消费消息的来源的对象.
		dest = session.createQueue(subject);
		// dest = session.createTopic(SUBJECT);
		// 会话创建消息的生产者将消息发送到目的地
		consumer = session.createConsumer(dest);

	}

	protected void start() throws JMSException {
		this.conn.start();
	}

	// 关闭连接
	public void close() throws JMSException {
		if (consumer != null)
			consumer.close();
		if (session != null)
			session.close();
		if (conn != null)
			conn.close();
	}
}
