package org.nod.jdbcblog.post;

import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcService {

    private final DataSource dataSource;

    public JdbcService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Post> postsFindAll() throws SQLException {

        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Post");
        ResultSet resultSet = preparedStatement.executeQuery();

        List<Post> posts = new ArrayList<>();
        while (resultSet.next()) {
            posts.add(
                    new Post(resultSet.getString("id"),
                            resultSet.getString("title"),
                            resultSet.getString("slug"),
                            resultSet.getDate("date").toLocalDate(),
                            resultSet.getInt("time_to_read"),
                            resultSet.getString("tags"),
                            resultSet.getInt("version"))
            );
        }
        return posts;
    }
}
