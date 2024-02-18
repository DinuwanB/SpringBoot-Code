package org.nod.jdbcblog.post;

import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;
public interface PostRepository extends ListCrudRepository<Post, String> {

    Optional<Post> findBySlug(String slug);

}
