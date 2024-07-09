package edu.school21.chat.repositories;

import edu.school21.chat.app.Program;
import edu.school21.chat.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository{
    private final Connection dataSource;

    public UsersRepositoryJdbcImpl(Connection dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<User> findById(Long id) {
        PreparedStatement prepareState;

        try {
            prepareState = dataSource.prepareStatement("SELECT * FROM chat.users WHERE userId = ?");
            prepareState.setLong(1, id);
            ResultSet res = prepareState.executeQuery();

            if (res.next()) {
                return Optional.of(
                        new User(
                                res.getInt("userId"),
                                res.getString("login"),
                                res.getString("password"),
                                null,
                                null
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Program.errExit(e.getMessage());
        }
        return Optional.empty();
    }
}
