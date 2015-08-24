package DemonMQ.common;

import javax.jms.MapMessage;

import org.springframework.jms.core.JmsTemplate;

public abstract class MQBase {

	protected JmsTemplate jmsTemplate;

	// getter and setter
	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	protected String key="key";
	
	public String getKey() {
		return key;
	}

	public abstract <T> T handleMessage();

}
