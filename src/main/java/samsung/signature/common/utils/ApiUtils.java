package samsung.signature.common.utils;

import org.springframework.web.reactive.function.client.WebClient;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiUtils {
	/**
	 * Sends a POST request and logs the completion.
	 *
	 * @param webClient the WebClient instance used to send the request.
	 * @param uri the URI to send the request to.
	 * @param body the request body.
	 * @param <T> the type of the request body.
	 */
	public static <T> Mono<Void> post(
		WebClient webClient,
		String uri,
		T body
	) {
		return webClient
			.post()
			.uri(uri)
			.bodyValue(body)
			.retrieve()
			.bodyToMono(Void.class)
			;
	}

	/**
	 * Sends a POST request and logs the completion.
	 *
	 * @param webClient the WebClient instance used to send the request.
	 * @param uri the URI to send the request to.
	 * @param body the request body.
	 * @param <T> the type of the request body.
	 * @param <U> the type of the response body.
	 */
	public static <T, U> Mono<U> post(
		WebClient webClient,
		String uri,
		T body,
		Class<U> returnType
	) {
		return webClient
			.post()
			.uri(uri)
			.bodyValue(body)
			.retrieve()
			.bodyToMono(returnType)
			;
	}

	/**
	 * Sends a GET request and logs the completion.
	 *
	 * @param webClient the WebClient instance used to send the request.
	 * @param uri the URI to send the request to.
	 */
	public static Mono<Void> get(
		WebClient webClient,
		String uri
	) {
		return webClient
			.get()
			.uri(uri)
			.retrieve()
			.bodyToMono(Void.class)
			;
	}

	/**
	 * Sends a GET request and logs the completion.
	 *
	 * @param webClient the WebClient instance used to send the request.
	 * @param uri the URI to send the request to.
	 * @param returnType the request body.
	 * @param <T> the type of the request body.
	 */
	public static <T> Mono<T> get(
		WebClient webClient,
		String uri,
		Class<T> returnType
	) {
		return webClient
			.get()
			.uri(uri)
			.retrieve()
			.bodyToMono(returnType)
			;
	}
}
