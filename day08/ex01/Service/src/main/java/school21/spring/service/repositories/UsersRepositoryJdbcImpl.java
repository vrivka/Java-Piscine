package school21.spring.service.repositories;

import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    private final DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User findById(Long id) {
        PreparedStatement state;
        ResultSet res;
        User user = null;

        try {
            state = dataSource.getConnection().prepareStatement("SELECT * FROM users WHERE id = ?");
            state.setLong(1, id);
            state.execute();
            res = state.getResultSet();

            if (res.next()) {
                user = new User(
                        res.getInt("id"),
                        res.getString("email")
                );
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        PreparedStatement state;
        ResultSet res;
        List<User> users = new ArrayList<>();

        try {
            state = dataSource.getConnection().prepareStatement("SELECT * FROM users");
            state.execute();
            res = state.getResultSet();

            while (res.next()) {
                users.add(
                        new User(
                                res.getInt("id"),
                                res.getString("email")
                        )
                );
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return users;
    }

    @Override
    public void save(User entity) {
        try {
            dataSource.getConnection()
                    .createStatement()
                    .executeQuery("INSERT INTO users (email) VALUES ('" + entity.getEmail() + "')");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void update(User entity) {
        try {
            dataSource.getConnection()
                    .createStatement()
                    .executeQuery("UPDATE users SET email = '" + entity.getEmail() + "' WHERE id = " + entity.getId());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            dataSource.getConnection().createStatement().executeQuery("DELETE FROM users WHERE id = " + id);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        PreparedStatement state;
        ResultSet res;
        Optional<User> user = Optional.empty();

        try {
            state = dataSource.getConnection().prepareStatement("SELECT * FROM users WHERE email = ?");
            state.setString(1, email);
            state.execute();
            res = state.getResultSet();

            if (res.next()) {
                user = Optional.of(new User(
                        res.getInt("id"),
                        res.getString("email")
                ));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return user;
    }
}
