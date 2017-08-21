package demonmq.rmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Demon on 2017/8/11.
 */
public class RabbitConnectionFactory {
    private static final Logger logger = LoggerFactory.getLogger(RabbitConnectionFactory.class);

    private RabbitConnectionFactory() throws Exception {
        throw new Exception();
    }
}
