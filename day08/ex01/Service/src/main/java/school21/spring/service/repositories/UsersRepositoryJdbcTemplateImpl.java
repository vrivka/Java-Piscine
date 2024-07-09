package school21.spring.service.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcTemplateImpl extends JdbcTemplate implements UsersRepository {


    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public User findById(Long id) {
        return queryForObject(
                "select * from users where id = " + id,
                (rs, rowNum) -> {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    return user;
                });
    }

    @Override
    public List<User> findAll() {
        Collection<User> ret = this.query(
                "SELECT * FROM users",
                (rs, rowNum) -> {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    return user;
                });
        return new ArrayList<>(ret);
    }

    @Override
    public void save(User entity) {
        update("INSERT INTO users (email) VALUES (?)", entity.getEmail());
    }

    @Override
    public void update(User entity) {
        update("UPDATE users SET email = ?WHERE id = " + entity.getId(), entity.getEmail());
    }

    @Override
    public void delete(Long id) {
        update("DELETE FROM users WHERE id = " + id);
    }

    @Override
    public Optional<User> findByEmail(String name) {
        return Optional.ofNullable(queryForObject(
                "SELECT * FROM users WHERE email = " + name,
                (rs, rowNum) -> {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setEmail(rs.getString("email"));
                    return user;
                }));
    }
}
