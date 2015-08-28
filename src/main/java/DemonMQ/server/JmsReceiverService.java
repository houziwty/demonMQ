package DemonMQ.server;

public interface JmsReceiverService {
    void init();
	Object receiverMessage(String msgType);

}
