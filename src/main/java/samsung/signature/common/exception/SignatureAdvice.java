package samsung.signature.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import samsung.signature.common.response.ExceptionCode;
import samsung.signature.common.response.ResponseEntityExceptionFactory;

@Slf4j
@RestControllerAdvice
public class SignatureAdvice {

	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ExceptionCode> handleInternalServerException(RuntimeException e) {
		log.error("""

                        Exception Message  : {},
                        Exception Trace    : {},
                        """,
			e.getMessage(),
			e.getStackTrace()[0]
		);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body(ExceptionCode.from(ServerErrorCode.INTERNAL_SERVER_ERROR));
	}

	@ExceptionHandler(SignatureException.class)
	public ResponseEntity<ExceptionCode> handleSignatureException(SignatureException e) {
		return ResponseEntityExceptionFactory.exception(e);
	}
}
