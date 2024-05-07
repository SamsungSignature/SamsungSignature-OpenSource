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
	 * @param <R> the type of the response body.
	 */
	public static <T, R> Mono<R> post(
		final WebClient webClient,
		final String uri,
		final T body,
		final Class<R> returnType
	) {
		return sendPostRequest(webClient, uri, null, null, body, returnType);
	}

	public static <T> Mono<Void> post(
		final WebClient webClient,
		final String uri,
		final T body
	) {
		return sendPostRequest(webClient, uri, null, null, body, Void.class);
	}

	public static <T> Mono<Void> postWithHeader(
		final WebClient webClient,
		final String uri,
		final String accessToken,
		final String UID,
		final T body
	) {
		return sendPostRequest(webClient, uri, accessToken, UID, body, Void.class);
	}

	public static <T, R> Mono<R> postWithHeader(
		final WebClient webClient,
		final String uri,
		final String accessToken,
		final String UID,
		final T body,
		final Class<R> returnType
	) {
		return sendPostRequest(webClient, uri, accessToken, UID, body, returnType);
	}

	private static <T, R> Mono<R> sendPostRequest(
		final WebClient webClient,
		final String uri,
		final String accessToken,
		final String UID,
		final T body,
		final Class<R> returnType
	) {
		return webClient
			.post()
			.uri(uri)
			.bodyValue(body)
			.headers(headers -> {
				if (accessToken != null && UID != null) {
					headers.set("Authorization", accessToken);
					headers.set("UID", UID);
				}
			})
			.retrieve().
			bodyToMono(returnType);
	}

	/**
	 * Sends a GET request and logs the completion.
	 *
	 * @param webClient the WebClient instance used to send the request.
	 * @param uri the URI to send the request to.
	 */
	public static Mono<Void> get(
		final WebClient webClient,
		final String uri
	) {
		return sendGetRequest(webClient, uri, null, null, Void.class);
	}

	/**
	 * Sends a GET request and logs the completion.
	 *
	 * @param webClient the WebClient instance used to send the request.
	 * @param uri the URI to send the request to.
	 * @param returnType the request body.
	 * @param <R> the type of the request body.
	 */
	public static <R> Mono<R> get(
		WebClient webClient,
		String uri,
		Class<R> returnType
	) {
		return sendGetRequest(webClient, uri, null, null, returnType);
	}

	public static Mono<Void> getWithHeader(
		final WebClient webClient,
		final String uri,
		final String accessToken,
		final String UID
	) {
		return sendGetRequest(webClient, uri, accessToken, UID, Void.class);
	}

	public static <T> Mono<T> getWithHeader(
		WebClient webClient,
		String uri,
		final String accessToken,
		final String UID,
		Class<T> returnType
	) {
		return sendGetRequest(webClient, uri, accessToken, UID, returnType);
	}

	private static <T> Mono<T> sendGetRequest(
		final WebClient webClient,
		final String uri,
		final String accessToken,
		final String UID,
		final Class<T> returnType
	) {
		return webClient
			.get()
			.uri(uri)
			.headers(headers -> {
				if (accessToken != null && UID != null) {
					headers.set("Authorization", accessToken);
					headers.set("UID", UID);
				}
			})
			.retrieve().
			bodyToMono(returnType);
	}
}
