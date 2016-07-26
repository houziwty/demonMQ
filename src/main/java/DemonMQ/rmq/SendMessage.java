package DemonMQ.rmq;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

public class SendMessage extends MqBase {
	private Logger LOGGER = LoggerFactory.getLogger(SendMessage.class);
	private String exchangeName;
	private String type;
	public SendMessage(String host, String exchangeName, String type) {
		super(host);
		this.exchangeName = exchangeName;
		this.type = type;
	}
	public void sendMessage(byte[] message) {
		try {
			Channel channel = this.getChannel();
			channel.exchangeDeclare(exchangeName, type);
			channel.basicPublish(exchangeName, "", null, message);
			this.close(channel);
		} catch (Exception ex) {
			LOGGER.error("sendMessage:",ex);
		}
	}
	private static final String EXCHANGE_NAME = "task_queue";
	public static void main(String[] args) {
		try {
//			// 创建连接和频道
//			ConnectionFactory factory = new ConnectionFactory();
//			factory.setHost("123.59.133.185");
//			Connection connection = factory.newConnection();
//			Channel channel = connection.createChannel();
//			// 声明转发器和类型
//			channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
//
//			String message = new Date().toLocaleString() + " : log something";
//			// 往转发器上发送消息
//			channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
//			System.out.println(" [x] Sent '" + message + "'");
//			channel.close();
//			connection.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
