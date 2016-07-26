package demonmq.util;

import java.io.IOException;
import java.io.InputStream;

import java.util.Properties;

public class MQConfig {

	private static Properties property;
	private static String user;
	private static String passWord;
	private static String address;
	private static int port;
	private static String subject;
	private static boolean destination;
	private static boolean delivery;

	static {
		property = new Properties();
		InputStream in = Object.class
				.getResourceAsStream("/mqconfig.properties");
		try {
			property.load(in);
			user = property.getProperty("mqproxy.user").trim();
			passWord = property.getProperty("mqproxy.password").trim();
			address = property.getProperty("mqproxy.address").trim();
			port = Integer
					.parseInt(property.getProperty("mqproxy.port").trim());
			//subject = property.getProperty("mqproxy.subject").trim();
			destination = Boolean.parseBoolean(property.getProperty(
					"mqproxy.destination").trim());
			delivery = Boolean.parseBoolean(property.getProperty(
					"mqproxy.delivery").trim());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static String getUser() {
		return user;
	}

	public static String getPassWord() {
		return passWord;
	}

	public static int getPort() {
		return port;
	}

	public static String getSubject() {
		return subject;
	}

	public static boolean isDestination() {
		return destination;
	}

	public static boolean isDelivery() {
		return delivery;
	}

	public static String getAddress() {
		return address;
	}

}
