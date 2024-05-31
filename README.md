# Signature Common

![GitHub last commit (branch)](https://img.shields.io/github/last-commit/SamsungSignature/signature-common/main)
![](https://github.com/SamsungSignature/signature-common/actions/workflows/signature-common.yml/badge.svg)
![GitHub License](https://img.shields.io/badge/license-MIT-blue)
[![](https://jitpack.io/v/SamsungSignature/signature-common.svg)](https://jitpack.io/#SamsungSignature/signature-common)

해당 라이브러리는 `Signature` 서비스에서 사용되는 공통 모듈을 모은 라이브러리입니다.

## Quick Starter

### 1. Add Dependency

---

**build.gradle**

```groovy
repositories {
    mavenCentral()
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.SamsungSignature:signature-common:0.0.6'
}
```

### 2. RestControllerAdvice

---

공통으로 사용하는 에러 처리용 `RestControllerAdvice`입니다.

**SignatureConfig.java**

```java

@Configuration
public class SignatureConfig {
    @Bean
    public SignatureAdvice signatureAdvice() {
        return new SignatureAdvice();
    }
}
```

### 3. ResponseEntityFactory

---

반복되는 `ResponseEntity<MessageBody<Void>>` 와 같은 클래스를 생성해주는 유틸입니다.

예시는 아래와 같습니다.

**ResponseEntityFactory.java**

```java

@GetMapping("/v1/signature/response-example")
public ResponseEntity<MessageBody<String>> signatureResponseExample() {
    return ResponseEntityFactory.ok("Hello", "Signature GetMethod!");
}

@PostMapping("/v1/signature/response-example")
public ResponseEntity<MessageBody<String>> signatureResponseExample() {
	return ResponseEntityFactory.ok("Hello", "Signature PostMethod!");
}
```

```json
{
	"message": "Hello",
	"data": "Signature GetMethod!"
}
```

이 외에도 헤더를 추가하여 전송할 수 있는 `ResponseEntityFactoryWithHeaders`와 예외 처리를 담당하는 `ResponseEntityExceptionFactory`가 있습니다.

### 4. RedisUtils

redis에 data를 저장하고 불러오는 유틸입니다.

**application.yml**
```yaml
spring:
  redis:
    host: {redis.host}
    port: {redis.port}

```
**RedisConfig.java**
```java
public class RedisConfig {
	private final RedisProperties redisProperties;

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper;
	}

	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		return new LettuceConnectionFactory(redisProperties.getHost(), redisProperties.getPort());
	}

	@Bean
	public RedisTemplate<byte[], byte[]> redisTemplate() {
		RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		return redisTemplate;
	}
}
```

**RedisUtils.java**
```java
@RequiredArgsConstructor
public class redisService{
	private final RedisTemplate<byte[], byte[]> redisTemplate;
	private final ObjectMapper objectMapper;
	private static final int data = 1;
	private static String signatureKey = "Hello, Signature!";
	
	public String insert(){
		
		return RedisUtils.put(
			redisTemplate,
            		objectMapper,
			data,
			"key-",signatureKey);
    }

	public String insertInExpiredTime(){
		return RedisUtils.putWithExpiredTime(
			redisTemplate,
			objectMapper,
			1000,
			data,
			"key-",signatureKey);
	}

	public int select(){
		return RedisUtils.get(
			redisTemplate,
			objectMapper,
			Integer.class,
			"key",signatureKey);
	}
}
```

### 5. ApiUtils

서버간의 Api 비동기 통신을 할 수 있는 유틸입니다.

**ApiConfig.java**
```java
public class ApiConfig{
	@Bean
	public WebClient webClient(){
		return WebClient.builder()
            .baseUrl("http://localhost:8080")
            .build();
	}
}
```

**ApiUtils.java**

```java
@RequiredArgsConstructor
public class ApiService {
    private final WebClient webClient;
	
	public void getMethod() {
		Mono<MessageBody> response = ApiUtils.get(
			webClient,
			"/v1/signature/response-example",
			MessageBody.class);
	}

	public void postMethod() {
		Mono<MessageBody> response = ApiUtils.post(
			webClient,
			"/v1/signature/response-example",
			MessageBody.class);
	}
}
```

## Dependencies

해당 프로젝트에서 사용하는 라이브러리의 모음입니다.

- [Jackson Databind 2.15.3](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind/2.15.3)

> `2.15.3`을 사용하는 이유는 현재(2024.03.13) Spring Boot에서 사용하는 버전이 2.15.3이기 때문입니다.

- [Lombok 1.18.30](https://mvnrepository.com/artifact/org.projectlombok/lombok/1.18.30)
- [Spring WebMvc 6.1.5](https://mvnrepository.com/artifact/org.springframework/spring-webmvc/6.1.5)
- [Spring Web 6.1.5](https://mvnrepository.com/artifact/org.springframework/spring-web/6.1.5)
- [Slf4j Api 2.0.7](https://mvnrepository.com/artifact/org.slf4j/slf4j-api/2.0.7)
- [Spring WebFlux 6.1.5](https://mvnrepository.com/artifact/org.springframework/spring-webflux/6.1.5)
- [spring Data Redis 3.2.2](https://mvnrepository.com/artifact/org.springframework.data/spring-data-redis/3.2.2)
