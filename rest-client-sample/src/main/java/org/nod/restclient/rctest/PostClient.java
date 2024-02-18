package org.nod.restclient.rctest;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class PostClient {

    private final RestClient restClient;

    public PostClient(RestClient.Builder builder){
        this.restClient = builder
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();
    }

    public List<Post> findPosts() {
        return restClient.get()
                .uri("/posts")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}
