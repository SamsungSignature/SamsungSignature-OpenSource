package samsung.signature.common.exception;

/**
 * Http Status 500에 해당하는 Exception
 */
public class InternalServerEcpetion extends SignatureException {
	public InternalServerEcpetion(ErrorCode errorCode) {
		super(errorCode);
	}
}
