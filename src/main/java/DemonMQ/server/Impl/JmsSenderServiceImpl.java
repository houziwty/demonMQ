package DemonMQ.server.Impl;


import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import DemonMQ.base.JmsSendBase;
import DemonMQ.server.JmsSenderService;
import DemonMQ.util.AcMessageType;




public class JmsSenderServiceImpl extends JmsSendBase implements
		JmsSenderService {
	private static JmsSenderServiceImpl instance;
	private static ReentrantLock initLock = new ReentrantLock();
	private static final Logger LOGGER = LoggerFactory
			.getLogger(JmsSenderServiceImpl.class);

	public static JmsSenderServiceImpl getInstance() {
		if (instance == null) {
			initLock.lock();
			try {
				if (instance == null) {
					instance = new JmsSenderServiceImpl();
				}

			} catch (Exception ex) {

			} finally {
				initLock.unlock();
			}
		}
		return instance;
	}


	private void sendMessageStr(String textMsg) throws JMSException {
		TextMessage msg = session.createTextMessage();
		msg.setText(textMsg);
		producer.send(msg);
	}

	private void sendMessageMap(Map map) throws JMSException {
		MapMessage msg = session.createMapMessage();
		// for()

		producer.send(msg);
	}

	public void sendMessage(String subject,String msgType, Object obj) {
		try {
			super.setSubject(subject);
			super.initialize();
		//	super.start();
			if (AcMessageType.TEXT.getText().equals(msgType)) {
				sendMessageStr(obj.toString());
				return;
			}
			// 发送Map消息
			if (AcMessageType.MAP.getText().equals(msgType)) {
				// sendMessageMap((Map) obj);
				return;
			}
			// 发送流消息
			if (AcMessageType.STREAM.getText().equals(msgType)) {
				// String streamValue = "ActiveMQ stream Message!";
				// StreamMessage msg = session.createStreamMessage();
				// msg.writeString(streamValue);
				// msg.writeBoolean(false);
				// msg.writeLong(1234567890);
				// producer.send(msg);
			}
			// 发送对象消息
			if (AcMessageType.OBJECT.getText().equals(msgType)) {
				// JmsObjectMessageBean jmsObject = new JmsObjectMessageBean(
				// "ActiveMQ Object Message", 18, false);
				// ObjectMessage msg = session.createObjectMessage();
				// msg.setObject(jmsObject);
				// producer.send(msg);
			}
			// 发送字节消息
			if (AcMessageType.BTYES.getText().equals(msgType)) {
				// String byteValue = "字节消息";
				// BytesMessage msg = session.createBytesMessage();
				// msg.writeBytes(byteValue.getBytes());
				// producer.send(msg);
			}
		} catch (Exception ex) {
			LOGGER.debug("sendMessage:" + ex.getStackTrace());
		} finally {
			try {
				super.close();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
