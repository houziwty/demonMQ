package DemonMQ.common;

import javax.jms.MapMessage;

import org.springframework.jms.core.JmsTemplate;

public class ReceiverBase {

	protected JmsTemplate jmsTemplate;

	// getter and setter
	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	public <T>T receiverMessage(){
		T  message = (T) jmsTemplate.receive();
		try{
			if(message!=null){
				
			}
		}catch(Exception ex){
			
		}
		return message;
	}

}
