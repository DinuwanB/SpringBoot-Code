package org.nod.virtualthreadmain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
public class Controller {

    private static final Logger log = LoggerFactory.getLogger(Controller.class);
    private final RestClient restClient;

    public Controller(RestClient.Builder builder) {
        this.restClient = builder.baseUrl("http://localhost:8086").build();
    }


    @GetMapping("/block/{seconds}")
    public String home(@PathVariable Integer seconds)  {

        ResponseEntity<Void> result = restClient.get()
                .uri("/block/" + seconds)
                .retrieve()
                .toBodilessEntity();

        log.info(" {} on {} ", result.getStatusCode(), Thread.currentThread());

        return Thread.currentThread().toString();
    }
}
