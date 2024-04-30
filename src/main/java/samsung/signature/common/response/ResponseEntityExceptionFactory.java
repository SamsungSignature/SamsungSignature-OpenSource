package samsung.signature.common.response;

import org.springframework.http.ResponseEntity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import samsung.signature.common.exception.ErrorCode;
import samsung.signature.common.exception.SignatureException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseEntityExceptionFactory {
	public static ResponseEntity<ExceptionCode> exception(ErrorCode errorCode) {
		return ResponseEntity.status(errorCode.getStatusCode())
			.body(ExceptionCode.from(errorCode));
	}

	public static ResponseEntity<ExceptionCode> exception(SignatureException e) {
		return ResponseEntity.status(e.getStatusCode())
			.body(ExceptionCode.from(e));
	}
}
