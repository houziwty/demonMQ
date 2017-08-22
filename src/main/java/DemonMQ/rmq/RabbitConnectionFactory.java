package demonmq.rmq;

import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    private  static Lock connectionLock=new ReentrantLock();

    private static Lock channelLock=new ReentrantLock();

    //连接工厂缓存
    private static Map<RabbitConnectionBean,ConnectionFactory>factoryMap=new HashMap<>();

    private static  Map<RabbitConnectionBean, List<RabbitConnection>>connectionMap=new HashMap<>();

    private static AtomicInteger channelId=new AtomicInteger(0);

    public static  class RabbitConnection{

    }

    public static class RabbitChannel {

    }
}
