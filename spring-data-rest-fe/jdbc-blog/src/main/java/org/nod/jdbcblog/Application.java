package org.nod.jdbcblog;

import org.nod.jdbcblog.post.Post;
import org.nod.jdbcblog.post.PostRepository;
import org.nod.jdbcblog.post.PostService;
import org.nod.jdbcblog.post.Posts;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

	@Bean
	CommandLineRunner commandLineRunner(PostService service){
		return args -> {
			List<Posts> posts = service.findAll();
			System.out.println(posts);
		};
	}

    @Bean
    CommandLineRunner commandLineRunner2(PostRepository repository) {
        return args -> {
            repository.save(new Post("1234", "Hello World!", "hellow", LocalDate.now(), 5, "spring, java", null));
            List<Post> posts = repository.findAll();
			System.out.println(posts);

            Optional<Post> hello = repository.findBySlug("hellow");
            System.out.println(hello);
        };
    }
}
