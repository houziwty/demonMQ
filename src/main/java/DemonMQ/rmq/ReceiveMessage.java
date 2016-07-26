package DemonMQ.rmq;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class ReceiveMessage extends MqBase {
	private Logger LOGGER = LoggerFactory.getLogger(ReceiveMessage.class);
	private String exchangeName;
	private String type;

	public ReceiveMessage(String host, String exchangeName, String type) {
		super(host);
		this.exchangeName = exchangeName;
		this.type = type;

	}

	public String receiveMessage() {
		try {
			Channel channel = this.getChannel();
			// channel.exchangeDeclare(exchangeName, type);

			// 创建一个非持久的、唯一的且自动删除的队列
			String queueName = channel.queueDeclare().getQueue();
			// 创建一个非持久的、唯一的且自动删除的队列
			channel.queueBind(queueName, exchangeName, "");
			QueueingConsumer consumer = new QueueingConsumer(channel);

			// 指定接收者，第二个参数为自动应答，无需手动应答
			channel.basicConsume(queueName, true, consumer);

//			while (true) {
				QueueingConsumer.Delivery delivery = consumer.nextDelivery();
				String message = new String(delivery.getBody());
				return message;
//			}

		} catch (Exception e) {
			LOGGER.error("receiveMessage",e);
		}
		return null;
	}

	private final static String EXCHANGE_NAME = "task_queue";

	public static void main(String[] args) {
		try {
			ReceiveMessage r = new ReceiveMessage("123.59.133.185", EXCHANGE_NAME, "fanout");
			while (true) {
				System.out.println("r:" + r.receiveMessage());
			}

			// // 创建连接和频道
			// ConnectionFactory factory = new ConnectionFactory();
			// factory.setHost("123.59.133.185");
			// Connection connection = factory.newConnection();
			// Channel channel = connection.createChannel();
			//
			// // 创建一个非持久的、唯一的且自动删除的队列
			// String queueName = channel.queueDeclare().getQueue();
			// // 为转发器指定队列，设置binding
			// channel.queueBind(queueName, EXCHANGE_NAME, "");
			//
			// System.out.println(" [*] Waiting for messages. To exit press
			// CTRL+C");
			//
			// QueueingConsumer consumer = new QueueingConsumer(channel);
			// // 指定接收者，第二个参数为自动应答，无需手动应答
			// channel.basicConsume(queueName, true, consumer);
			//
			// while (true) {
			// QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			// String message = new String(delivery.getBody());
			// System.out.println(" [x] Received '" + message + "'");
			//
			// }
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
