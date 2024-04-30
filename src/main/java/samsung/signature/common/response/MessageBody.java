package samsung.signature.common.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@AllArgsConstructor(access = lombok.AccessLevel.PRIVATE)
@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class MessageBody<T> {
	@JsonProperty("message")
	private String message;
	@JsonProperty("body")
	private T body;

	public static <T> MessageBody<T> of(String message, T body) {
		return new MessageBody<>(message, body);
	}

}
