package DemonMQ.server.Impl;

import java.util.concurrent.locks.ReentrantLock;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.apache.activemq.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import DemonMQ.base.JmsReceiverBase;
import DemonMQ.common.model.ResultModel;
import DemonMQ.common.model.ReturnCode;
import DemonMQ.server.JmsReceiverService;
import DemonMQ.util.AcMessageType;

public class JmsReceiverServiceImpl extends JmsReceiverBase implements
		JmsReceiverService {

	private static JmsReceiverServiceImpl instance;
	private static ReentrantLock initLock = new ReentrantLock();
	private static final Logger LOGGER = LoggerFactory
			.getLogger(JmsReceiverServiceImpl.class);

	public static JmsReceiverServiceImpl getInstance() {
		if (instance == null) {
			initLock.lock();
			try {
				if (instance == null) {
					instance = new JmsReceiverServiceImpl();
				}

			} catch (Exception ex) {

			} finally {
				initLock.unlock();
			}
		}
		return instance;
	}
public JmsReceiverServiceImpl(){
	
}
	private String receiverMessageStr() {
		String text = null;
		// TODO Auto-generated method stub
		try {

			Message message = (Message) consumer.receive(1000);
			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;

				text = textMessage.getText();

				// looger.info("接收的消息：" + "\n" + text);
			} else {
				LOGGER.info("接收的消息：" + "\n" + message);
			}
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			LOGGER.debug("receiverMessageStr:" + ex.getStackTrace());
		}
		// finally {
		// try {
		// super.close();
		// } catch (JMSException e) {
		// looger.debug("receiverMessageStr close:" + e.getStackTrace());
		// e.printStackTrace();
		// }
		// }
		return text;
	}

	public ResultModel receiverMessage(String msgType,String subject) {
		ResultModel result=new ResultModel();
		
		Object msg = null;
		try {
			super.setSubject(subject);
			super.initialize();
		//	super.start();
			if (AcMessageType.TEXT.getText().equals(msgType)) {
				msg = receiverMessageStr();
				result.setReturnCode(ReturnCode.SUCCESS.initValue());
				result.setBody(msg);
			}
		} catch (Exception ex) {
			LOGGER.debug("receiverMessage:" + ex.getStackTrace());
			ex.printStackTrace();
			result.setReturnCode(ReturnCode.FAIL.initValue());
			result.setBody(null);
		} finally {
			try {
				super.close();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				LOGGER.debug("receiverclose:" + e.getStackTrace());
				e.printStackTrace();
			}
		}
		// TODO Auto-generated method stub
		return result;
	}





}
