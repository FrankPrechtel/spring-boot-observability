package eu.prechtel.workshop.observability;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@AllArgsConstructor
@Slf4j
public class DemoScheduler {

	private final WebClient webClient;

//    @Scheduled(fixedDelay = 5000)
    void triggerCall() {

        webClient
			.get()
			.uri(uriBuilder -> uriBuilder
				.host("localhost")
				.port(8080)
				.path("/")
				//.queryParam("text", RandomStringUtils.randomAlphabetic(4, 9))
				.build())
			.retrieve()
			.bodyToMono(String.class)
			.block();
	}
}
