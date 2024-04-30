package samsung.signature.common.exception;

import lombok.Getter;

@Getter
public class SignatureException extends RuntimeException{
	private final int statusCode;
	private final String message;
	private final String errorCode;

	public SignatureException(ErrorCode errorCode) {
		this.statusCode = errorCode.getStatusCode();
		this.message = errorCode.getMessage();
		this.errorCode = errorCode.getErrorCode();
	}
}
