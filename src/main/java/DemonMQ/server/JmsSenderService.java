package DemonMQ.server;


public interface JmsSenderService {
	void init();
	void sendMessage(String msgType,Object obj);

	
}
