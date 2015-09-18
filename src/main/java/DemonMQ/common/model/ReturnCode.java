package DemonMQ.common.model;

import DemonMQ.util.EnumInteger;


public enum ReturnCode implements EnumInteger {
	//UNKNOWN(100), // 未知错误
	FAIL(0),
	SUCCESS(1), // 成功
	//OK_CLOSE_COMMENT(201), //
//	CLINET_ERROR(400), // 客户端错误
	UNAUTHORIZED(401), // 未授权
//	MISSING_ARG(402), // 缺少参数
	////SERVER_ERROR(500), // 服务器内部错误
	//SERVER_QUOTA(502), //
//	SERVER_QUOTA_TEXT(503), ;//
	;
	private int value;

	ReturnCode(int value) {
		this.value = value;
	}

	public int initValue() {
		// TODO Auto-generated method stub
		return value;
	}

}
