package eu.prechtel.workshop.observability;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor
public class DemoScheduler {

    final HttpClient httpClient = HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            .responseTimeout(Duration.ofMillis(5000))
            .doOnConnected(conn ->
                    conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                            .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS)));

    final WebClient webClient = WebClient.builder()
            .clientConnector(new ReactorClientHttpConnector(httpClient))
            .baseUrl("http://localhost:8080")
            .build();

//    @Scheduled(fixedDelay = 5000)
    void triggerCall() {

        webClient
			.get()
			.uri(uriBuilder -> uriBuilder
				.path("/")
//				.queryParam("text", RandomStringUtils.randomAlphabetic(4, 9))
				.build())
			.retrieve()
			.bodyToMono(String.class)
			.block();
    }
}
