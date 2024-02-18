package org.nod.jdbcblog.post;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final JdbcClient jdbcClient;

    public PostService(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Posts> findAll() {
        return jdbcClient.sql("SELECT * FROM Post")
                .query(Posts.class)
                .list();
    }

    public Optional<Posts> findById(String id) {
        return jdbcClient.sql("SELECT * FROM Post WHERE id = :id")
                .param("id", id)
                .query(Posts.class)
                .optional();
    }

    public int create(Posts post) {
        return jdbcClient.sql("INSERT INTO Post(id,title,slug,date,time_to_read,tags) values(?,?,?,?,?,?)")
                .params(List.of(post.id(), post.title(), post.slug(), post.date(), post.timeToRead(), post.tags()))
                .update();
    }

    public int update(Posts post, String id) {
        return jdbcClient.sql("update Post set title = ?, slug = ?, date = ?, time_to_read = ?, tags = ? where id = ?")
                .params(List.of(post.title(), post.slug(), post.date(), post.timeToRead(), post.tags(), id))
                .update();
    }

    public int delete(String id) {
        return jdbcClient.sql("delete from Post where id = :id")
                .param("id", id)
                .update();
    }


}
