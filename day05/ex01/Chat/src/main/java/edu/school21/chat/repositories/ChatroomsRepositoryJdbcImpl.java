package edu.school21.chat.repositories;

import edu.school21.chat.app.Program;
import edu.school21.chat.models.Chatroom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class ChatroomsRepositoryJdbcImpl implements ChatroomsRepository {
    private final Connection dataSource;

    public ChatroomsRepositoryJdbcImpl(Connection dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Chatroom> findById(Long id) {
        PreparedStatement prepareState;

        try {
            prepareState = dataSource.prepareStatement("SELECT * FROM chat.rooms WHERE roomId = ?");
            prepareState.setLong(1, id);
            ResultSet res = prepareState.executeQuery();

            if (res.next()) {
                return Optional.of(
                        new Chatroom(
                                res.getInt("roomId"),
                                res.getString("name"),
                                null,
                                null
                        )
                );
            }
        } catch (SQLException e) {
            Program.errExit(e.getMessage());
        }
        return Optional.empty();
    }
}
