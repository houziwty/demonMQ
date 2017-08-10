package demonmq.rmq;


import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.QueueingConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;



/**
 * Created by Demon on 2017/8/10.
 */
public class RabbitClient {
    private static final Logger logger = LoggerFactory.getLogger(RabbitClient.class);
    private static final  RabbitClient rabbitClient=new RabbitClient();
    private RabbitClient() {
    }
    public static RabbitClient getInstance() {
        return rabbitClient;
    }
    private ConcurrentHashMap<String,Consumer>consumerCache=new ConcurrentHashMap<>();

    private ConcurrentHashMap<String,QueueingConsumer>pullConsumerCache = new ConcurrentHashMap<>();
}
