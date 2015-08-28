package DemonMQ.server.Impl;

import java.util.concurrent.locks.ReentrantLock;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.apache.activemq.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import DemonMQ.base.JmsReceiverBase;
import DemonMQ.server.JmsReceiverService;
import DemonMQ.util.MessageType;



public class JmsReceiverServiceImpl extends JmsReceiverBase implements
		JmsReceiverService {

	private static JmsReceiverServiceImpl instance;
	private static ReentrantLock initLock = new ReentrantLock();
	private static final Logger looger = LoggerFactory
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
				looger.info("接收的消息：" + "\n" + message);
			}
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
			looger.debug("receiverMessageStr:" + ex.getStackTrace());
		} 
//		finally {
//			try {
//				super.close();
//			} catch (JMSException e) {
//				looger.debug("receiverMessageStr close:" + e.getStackTrace());
//				e.printStackTrace();
//			}
//		}
		return text;
	}

	public Object receiverMessage(String msgType) {
		Object msg = null;
		try {
			super.start();
			if (MessageType.TEXT.getText().equals(msgType)) {
				msg = receiverMessageStr();
			}
		} catch (Exception ex) {
			looger.debug("receiverMessage:" + ex.getStackTrace());
			ex.printStackTrace();
		} finally {
			try {
				super.close();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				looger.debug("receiverclose:" + e.getStackTrace());
e.printStackTrace();
			}
		}
		// TODO Auto-generated method stub
		return msg;
	}

	public  void init() {
		// TODO Auto-generated method stub
		try {
			
			super.initialize();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
