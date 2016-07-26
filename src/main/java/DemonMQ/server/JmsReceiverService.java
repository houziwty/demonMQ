package demonmq.server;

import demonmq.common.model.ResultModel;

public interface JmsReceiverService {
	
	ResultModel receiverMessage(String msgType,String subject);

}
