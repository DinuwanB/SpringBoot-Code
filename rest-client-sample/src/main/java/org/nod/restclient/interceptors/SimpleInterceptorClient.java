package org.nod.restclient.interceptors;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class SimpleInterceptorClient {

    private final RestClient restClient;

    public SimpleInterceptorClient(RestClient.Builder builder, ClientHttpRequestInterceptor myInterceptor) {

        this.restClient = builder
                .baseUrl("https://jsonplaceholder.typicode.com")
                .requestFactory(new JdkClientHttpRequestFactory())
                .requestInterceptor(myInterceptor)
                .build();
    }

    public String findAllTodos() {
        return restClient
                .get()
                .uri("/todos")
                .retrieve()
                .body(String.class);
    }
}
