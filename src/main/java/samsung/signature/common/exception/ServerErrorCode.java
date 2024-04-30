package samsung.signature.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ServerErrorCode implements ErrorCode{
	BAD_REQUEST(400,"SERVER_001","잘못된 요청입니다."),
	INTERNAL_SERVER_ERROR(500,"SERVER_002","내부 서버 오류입니다."),
	;
	private final int statusCode;
	private final String message;
	private final String errorCode;

	@Override
	public int getStatusCode() {
		return statusCode;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public String getErrorCode() {
		return errorCode;
	}
}
