package samsung.signature.common.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import samsung.signature.common.exception.ErrorCode;
import samsung.signature.common.exception.SignatureException;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionCode {
	@JsonProperty("message")
	private String message;
	@JsonProperty("code")
	private String code;

	public static ExceptionCode from(ErrorCode errorCode) {
		return new ExceptionCode(errorCode.getMessage(), errorCode.getErrorCode());
	}

	public static ExceptionCode from(SignatureException e) {
		return new ExceptionCode(e.getMessage(), e.getErrorCode());
	}

}
