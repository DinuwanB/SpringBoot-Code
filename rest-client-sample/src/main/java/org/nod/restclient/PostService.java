package org.nod.restclient;

import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class PostService {

    private final RestClient restClient;

    public PostService(RestClient.Builder builder) {
        // configure http client
        JdkClientHttpRequestFactory requestFactory = new JdkClientHttpRequestFactory();
        requestFactory.setReadTimeout(10000);

        this.restClient = builder
                .baseUrl("https://jsonplaceholder.typicode.com")
                .requestFactory(requestFactory)
                .build();
    }

    public String findAllPosts() {
        return restClient
                .get()
                .uri("/posts")
                .retrieve()
                .body(String.class);
    }

}
