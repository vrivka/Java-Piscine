package edu.school21.chat.repositories;

import edu.school21.chat.app.Program;
import edu.school21.chat.models.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private final Connection dataSource;

    public MessagesRepositoryJdbcImpl(Connection dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) {
        PreparedStatement prepareState;

        try {
            prepareState = dataSource.prepareStatement("SELECT * FROM chat.massages WHERE massageId = ?");
            prepareState.setLong(1, id);
            ResultSet res = prepareState.executeQuery();

            if (res.next()) {
                return Optional.of(
                        new Message(
                                res.getInt("massageId"),
                                new UsersRepositoryJdbcImpl(dataSource).findById(res.getLong("authorId")).orElse(null),
                                new ChatroomsRepositoryJdbcImpl(dataSource).findById(res.getLong("chatRoomId")).orElse(null),
                                res.getString("text"),
                                res.getTimestamp("date").toLocalDateTime()
                        )
                );
            }
        } catch (SQLException e) {
            Program.errExit(e.getMessage());
        }
        return Optional.empty();
    }
}
