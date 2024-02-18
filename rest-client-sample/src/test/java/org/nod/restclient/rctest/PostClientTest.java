package org.nod.restclient.rctest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(PostClient.class)
class PostClientTest {

    @Autowired
    MockRestServiceServer serviceServer;

    @Autowired
    PostClient postClient;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void shouldFindAllPosts() throws JsonProcessingException {

        // given
        List<Post> data = List.of(
                new Post(1, 1, "Hello world", "This is post for test"),
                new Post(2, 1, "Testing Rest Client with @RestClientTest", "This is post for RestClientTest")
        );

        // when
        serviceServer.expect(requestTo("https://jsonplaceholder.typicode.com/posts"))
                .andRespond(withSuccess(objectMapper.writeValueAsString(data), MediaType.APPLICATION_JSON));

        // then
        List<Post>  posts = postClient.findPosts();
        assertEquals(2, posts.size());
    }
}