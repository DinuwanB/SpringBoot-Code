package org.nod.multipledatasourcedemo;

import org.nod.multipledatasourcedemo.posts.PostService;
import org.nod.multipledatasourcedemo.subscriber.SubscriberService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.simple.JdbcClient;

import javax.sql.DataSource;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	JdbcClient blogJdbcClient(@Qualifier("blogDataSource") DataSource dataSource){
		return JdbcClient.create(dataSource);
	}

	@Bean
	JdbcClient subscriberJdbcClient(@Qualifier("subscriberDataSource") DataSource dataSource){
		return JdbcClient.create(dataSource);
	}

	@Bean
	CommandLineRunner commandLineRunner(PostService service, SubscriberService subscriberService){
		return args -> {
			var posts = service.findAll();
			System.out.println(posts);

			var subscribers = subscriberService.findAll();
			System.out.println(subscribers);
		};
	}

	@Bean
	CommandLineRunner dsCommandLineRunner(@Qualifier("blogDataSource") DataSource blogDS,
										  @Qualifier("subscriberDataSource") DataSource subDS){
		return args -> {
			System.out.println(blogDS.getConnection().getMetaData().getURL());
			System.out.println(subDS.getConnection().getMetaData().getURL());
		};
	}

}
