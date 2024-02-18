package org.nod.restclient.rctest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RestClientTest
class PostClientTest {

    @Autowired
    MockRestServiceServer serviceServer;

    @Autowired
    PostClient postClient;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void findAllPosts() {

        // given
        List<Post> data = List.of(new Post(1, 1, "Hello world", ))
        // when

    }
}