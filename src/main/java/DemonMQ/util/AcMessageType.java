package demonmq.util;



public enum AcMessageType {
	TEXT(1, "text"), MAP(2, "map"), STREAM(3, "stream"), OBJECT(4, "object"), BTYES(
			5, "bytes");

	private int value;
	private String text;

	AcMessageType(int value, String text) {
		this.value = value;
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public static AcMessageType valueof(String value) {
		return valueOf(Integer.valueOf(value));
	}

	public static AcMessageType valueOf(int i) {
		for (AcMessageType r : AcMessageType.values()) {
			if (r.value == i)
				return r;

		}
		return null;
	}

	public int initValue() {
		return value;
	}
}
