package demonmq.server;


public interface JmsSenderService {

	void sendMessage(String subject,String msgType,Object obj);

	
}
