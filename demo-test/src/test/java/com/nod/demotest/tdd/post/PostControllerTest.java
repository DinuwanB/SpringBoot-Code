package com.nod.demotest.tdd.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.StringTemplate.STR;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(PostController.class)
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PostRepository postRepository;

    List<Post> postList = new ArrayList<>();

    @BeforeEach
    void setUp() {

        postList = List.of(
                new Post(1, 1, "Hello", "hello body", null),
                new Post(2, 2, "Hello 2", "hello body 2", null)
        );

    }

    @Test
    void shouldFindAllPosts() throws Exception {

        String jsonResponse = """
                [
                    {
                        "id" : 1,
                        "userId": 1,
                        "title" : "Hello",
                        "body":"hello body",
                        "version": null
                    },
                    {
                        "id" : 2,
                        "userId": 2,
                        "title" : "Hello 2",
                        "body":"hello body 2",
                        "version": null
                    }
                ]
                """;

        when(postRepository.findAll()).thenReturn(postList);

        mockMvc.perform(get("/api/v1/posts"))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonResponse));

    }

    @Test
    void shouldFindPostWhenGivenValidId() throws Exception {

        when(postRepository.findById(1)).thenReturn(Optional.of(postList.getFirst()));

        var post = postList.getFirst();
        var json = STR."""
                {
                    "id":\{post.id()},
                    "userId":\{post.userId()},
                    "title":"\{post.title()}",
                    "body":"\{post.body()}",
                    "version": null
                }
        """;

        mockMvc.perform(get("/api/v1/posts/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(json));
    }

    @Test
    void shouldNotFoundPostWhenGivenInvalidId() throws Exception {

        when(postRepository.findById(1000)).thenThrow(PostNotFoundException.class);

        mockMvc.perform(get("/api/v1/posts/1000"))
                .andExpect(status().isNotFound());

    }

    @Test
    void shouldCreateNewPostWithValidJsonObject() throws Exception {

        var post = new Post(3, 1, "New Post", "New post Body", null);
        when(postRepository.save(post)).thenReturn(post);

        var json = STR."""
                {
                    "id":\{post.id()},
                    "userId":\{post.userId()},
                    "title":"\{post.title()}",
                    "body":"\{post.body()}",
                    "version": null
                }
        """;

        mockMvc.perform(post("/api/v1/posts")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldNotCreateNewPostWithInvalidJsonObject() throws Exception {

        var post = new Post(3, 1, "", "", null);
        when(postRepository.save(post)).thenReturn(post);

        var json = STR."""
                {
                    "id":\{post.id()},
                    "userId":\{post.userId()},
                    "title":"\{post.title()}",
                    "body":"\{post.body()}",
                    "version": null
                }
        """;

        mockMvc.perform(post("/api/v1/posts")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldUpdatePostWithValidJsonObject() throws Exception {

        var post = new Post(1, 1, "New updated title", "New updated post body", 1);

        when(postRepository.findById(1)).thenReturn(Optional.of(post));
        when(postRepository.save(post)).thenReturn(post);

        var json = STR."""
                {
                    "id":\{post.id()},
                    "userId":\{post.userId()},
                    "title":"\{post.title()}",
                    "body":"\{post.body()}",
                    "version": null
                }
        """;

        mockMvc.perform(put("/api/v1/posts/1")
                        .contentType("application/json")
                        .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeletePostWithGivenValidId() throws Exception {

        doNothing().when(postRepository).deleteById(1);

        mockMvc.perform(delete("/api/v1/posts/1"))
                .andExpect(status().isNoContent());

        verify(postRepository, times(1)).deleteById(1);
    }

}
