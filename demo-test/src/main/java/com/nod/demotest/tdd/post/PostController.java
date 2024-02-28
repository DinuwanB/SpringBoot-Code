package com.nod.demotest.tdd.post;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/posts")
class PostController {

    private final PostRepository postRepository;

    PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping
    List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Post> findPostById(@PathVariable Integer id) {
        return Optional.ofNullable(postRepository.findById(id).orElseThrow(PostNotFoundException::new));
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    Post savePost(@RequestBody @Valid Post post) {
        return postRepository.save(post);
    }

    @PutMapping("/{id}")
    Post updatePost(@PathVariable Integer id, @RequestBody @Valid Post post) {
        Optional<Post> existingPost = postRepository.findById(id);
        if (existingPost.isPresent()) {
            Post update = new Post(
                    existingPost.get().id(),
                    existingPost.get().userId(),
                    post.title(),
                    post.body(),
                    existingPost.get().version()
            );

            return postRepository.save(update);
        } else {
            throw new PostNotFoundException();
        }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id){
        postRepository.deleteById(id);
    }
}
