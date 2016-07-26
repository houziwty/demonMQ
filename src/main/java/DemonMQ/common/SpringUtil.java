package demonmq.common;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;





public class SpringUtil {
	private static ApplicationContext ctx = null;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SpringUtil.class);
	
	static{
		try{	
			ctx=new ClassPathXmlApplicationContext("/beans.xml");
			//ctx=new ClassPathXmlApplicationContext("classpath:/*.xml");
			
		}catch(Exception ex){
			ex.printStackTrace();
			LOGGER.error(ex.getMessage());
		}
	}
	public static Object getBean(String beanName){
		return ctx.getBean(beanName);
	}
	
	public static<T>T getBean(Class<T>classz){
		return ctx.getBean(classz);
	}
	public static <T>T getBean(String beanName,Class<T>classz){
		return ctx.getBean(beanName,classz);
	}
}
