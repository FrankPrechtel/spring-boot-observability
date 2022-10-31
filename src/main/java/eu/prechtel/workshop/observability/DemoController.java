package eu.prechtel.workshop.observability;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@AllArgsConstructor
public class DemoController {

    private DemoService demoService;

//    @Timed // see application property metrics.web.server.request.autotime
    @GetMapping(path = "/", produces = "application/json")
    public String home(@RequestParam(required = false) String text) {
        log.info("called home({})", text);
        return "Hello " + demoService.createTextForMessage(text);
    }
}
