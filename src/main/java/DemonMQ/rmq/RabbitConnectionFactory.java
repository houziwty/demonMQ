package demonmq.rmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Demon on 2017/8/11.
 */
public class RabbitConnectionFactory {
    private static final Logger logger = LoggerFactory.getLogger(RabbitConnectionFactory.class);

    private RabbitConnectionFactory() throws Exception {
        throw new Exception();
    }

    private static final int defaultConnectionCacheSize = 3;

    private static final int defaultChannelCacheSize = 10;

    public static int connectionCacheSize = defaultConnectionCacheSize;
    public static int channelCacheSize = defaultChannelCacheSize;

    private static Lock connectionLock = new ReentrantLock();

    private static Lock channelLock = new ReentrantLock();

    //连接工厂缓存
    private static Map<RabbitConnectionBean, ConnectionFactory> factoryMap = new HashMap<>();

    private static Map<RabbitConnectionBean, List<RabbitConnection>> connectionMap = new HashMap<>();

    /**
     * 通道缓存
     */
    static final Map<Integer, RabbitChannel> channelCache = new HashMap<>();


    private static AtomicInteger channelId = new AtomicInteger(0);


    private static ConnectionFactory getConnectionFactory(RabbitConnectionBean bean) {
        ConnectionFactory connectionFactory = factoryMap.get(bean);
        if (connectionFactory == null) {
            logger.info("创建新的rabbitmq连接工厂");
            connectionFactory = new ConnectionFactory();
            connectionFactory.setHost(bean.getHost());
            connectionFactory.setPort(bean.getPort());
            connectionFactory.setVirtualHost(bean.getvHost());
            connectionFactory.setUsername(bean.getUsername());
            connectionFactory.setPassword(bean.getPassword());
            factoryMap.put(bean, connectionFactory);
        }
        return connectionFactory;
    }

    public static Connection getNewConnection(RabbitConnectionBean bean) throws IOException, TimeoutException {
        return getConnectionFactory(bean).newConnection();
    }

    public static RabbitConnection getRabbitConnection(RabbitConnectionBean bean) throws IOException, TimeoutException {
        List<RabbitConnection> rabbitConnections = connectionMap.get(bean);
        if (rabbitConnections == null || rabbitConnections.size() == 0) {
            connectionLock.lock();
            RabbitConnection rabbitConnection = null;
            rabbitConnections = new ArrayList<>();
            try {
                Connection connection = getNewConnection(bean);
                rabbitConnection = new RabbitConnection();
                rabbitConnection.setConnection(connection);
                rabbitConnection.setChannelList(new ArrayList<RabbitChannel>());
                rabbitConnections.add(rabbitConnection);
                connectionMap.put(bean, rabbitConnections);
            } finally {
                connectionLock.unlock();
            }
            return rabbitConnection;
        } else if (rabbitConnections.size() < connectionCacheSize) {
            if (connectionLock.tryLock()) {
                try {
                    Connection connection = getNewConnection(bean);
                    RabbitConnection rabbitConnection = new RabbitConnection();
                    rabbitConnection.setConnection(connection);
                    rabbitConnection.setChannelList(new ArrayList<RabbitChannel>());
                    rabbitConnections.add(rabbitConnection);
                } finally {
                    connectionLock.unlock();
                }
            }
        }
        return rabbitConnections.get((int) (Math.random() * rabbitConnections.size()));
    }

    public static class RabbitConnection {
        private Connection connection;
        private List<RabbitChannel> channelList;

        public Connection getConnection() {
            return connection;
        }

        public void setConnection(Connection connection) {
            this.connection = connection;
        }

        public List<RabbitChannel> getChannelList() {
            return channelList;
        }

        public void setChannelList(List<RabbitChannel> channelList) {
            this.channelList = channelList;
        }
    }

    public static class RabbitChannel {
        private int channelId;

        private Channel channel;

        public RabbitChannel() {
        }

        public RabbitChannel(int channelId, Channel channel) {
            this.channelId = channelId;
            this.channel = channel;
        }

        public Channel getChannel() {
            return channel;
        }

        public void setChannel(Channel channel) {
            this.channel = channel;
        }

        public int getChannelId() {
            return channelId;
        }

        public void setChannelId(int channelId) {
            this.channelId = channelId;
        }
    }
}
