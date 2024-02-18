package org.nod.restclient;

import org.nod.restclient.rctest.PostClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RestClientSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestClientSampleApplication.class, args);

    }

/*
    @Bean
    CommandLineRunner commandLineRunner(PostService postService) {
        return args -> {
            String allPosts = postService.findAllPosts();
            System.out.println(allPosts);
        };
    }
*/

    @Bean
    CommandLineRunner commandLineRunner(PostClient postService) {
        return args -> {

        };
    }

}
