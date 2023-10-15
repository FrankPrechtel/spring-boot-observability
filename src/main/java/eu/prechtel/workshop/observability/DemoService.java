package eu.prechtel.workshop.observability;

import io.micrometer.core.instrument.Metrics;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DemoService {
    public String createTextForMessage(String text) {
        log.info("called createTextForMessage({})", text);
		if ((text == null) || text.isBlank()) {
			Metrics.counter("demo.text.random").increment();
			return RandomStringUtils.randomAlphabetic(8, 17);
		} else {
			Metrics.counter("demo.text.custom").increment();
			return text;
		}
	}
}
