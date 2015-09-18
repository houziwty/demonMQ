package DemonMQ.server;

import DemonMQ.common.model.ResultModel;

public interface JmsReceiverService {
	
	ResultModel receiverMessage(String msgType,String subject);

}
