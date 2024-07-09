package edu.school21.sockets.repositories;

import edu.school21.sockets.models.User;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UsersRepositoryImpl extends JdbcTemplate implements UsersRepository {
    private final RowMapper<User> rowMapper = (rs, rowNum) -> {
                User user = new User();

                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
                return user;
            };

    @Autowired
    public UsersRepositoryImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public User findById(Long id) {
        return queryForObject("SELECT * FROM users WHERE id = ?", rowMapper, id);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(query("SELECT * FROM users", rowMapper));
    }

    @Override
    public void save(User entity) {
        update("INSERT INTO users (name, password) VALUES (?, ?)", entity.getName(), entity.getPassword());
    }

    @Override
    public void update(User entity) {
        update("UPDATE users SET name = ?, password = ? WHERE id = ?", entity.getName(), entity.getPassword(), entity.getId());
    }

    @Override
    public void delete(Long id) {
        update("DELETE FROM users WHERE id = ?", id);
    }

    @Override
    public Optional<User> findByLogin(String name) {
        try {
            return Optional.ofNullable(query("SELECT * FROM users WHERE name = ?", rowMapper, name).get(0));
        } catch (IndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }
}
