package DemonMQ.rmq;

import com.rabbitmq.client.ShutdownListener;
import com.rabbitmq.client.ShutdownSignalException;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class MqBase implements ShutdownListener {

	private Logger LOGGER = LoggerFactory.getLogger(MqBase.class);
	private static Connection connection;

	public MqBase(String host) {
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost(host);
			connection = factory.newConnection();
		} catch (Exception ex) {
			LOGGER.error("加载错误:", ex);
			ex.printStackTrace();
		}
	}

	protected Channel getChannel() throws IOException {
		return connection.createChannel();
	}

	protected void close(Channel channle) {
		try {
			channle.close();
			connection.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void shutdownCompleted(ShutdownSignalException arg0) {

	}

}
