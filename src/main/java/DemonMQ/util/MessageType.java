package DemonMQ.util;



public enum MessageType {
	TEXT(1, "text"), MAP(2, "map"), STREAM(3, "stream"), OBJECT(4, "object"), BTYES(
			5, "bytes");

	private int value;
	private String text;

	MessageType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public static MessageType valueof(String value) {
		return valueOf(Integer.valueOf(value));
	}

	public static MessageType valueOf(int i) {
		for (MessageType r : MessageType.values()) {
			if (r.value == i)
				return r;

		}
		return null;
	}

	public int initValue() {
		return value;
	}
}
