package samsung.signature.common.exception;

/**
 * Http status 400 Bad Request를 의미하는 예외
 */
public class BadRequestException extends SignatureException{
	public BadRequestException(ErrorCode errorCode) {
		super(errorCode);
	}
}
