package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class Program {
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/chat";
    public static final String USER = "vrivka";
    public static final String PASSWORD = "";
    public static final String SCHEMA_PATH = "src/main/resources/schema.sql";
    public static final String DATA_PATH = "src/main/resources/data.sql";

    public static void main(String[] args) {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(DB_URL);
        config.setUsername(USER);
        config.setPassword(PASSWORD);

        HikariDataSource dataSource = new HikariDataSource(config);

        try {
            executeQueryFromFile(dataSource.getConnection(), SCHEMA_PATH);
            executeQueryFromFile(dataSource.getConnection(), DATA_PATH);

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Enter a message ID");

                if (!scanner.hasNextLine()) {
                    break;
                }
                String line = scanner.nextLine();
                long id;

                try {
                    id = Long.parseLong(line);
                } catch (NumberFormatException e) {
                    break;
                }

                Optional<Message> messageOpt = new MessagesRepositoryJdbcImpl(dataSource.getConnection()).findById(id);

                if (messageOpt.isPresent()) {
                    System.out.println(messageOpt.get());
                } else {
                    System.out.println("Message with id = " + id + " not exist");
                }
            }
            dataSource.close();
        } catch (SQLException e) {
            errExit(e.getMessage());
        }
    }

    public static void executeQueryFromFile(Connection connection, String path) {
        try (FileInputStream schemaIS = new FileInputStream(path)) {
            Scanner scanner = new Scanner(schemaIS);

            scanner.useDelimiter(";");

            while (scanner.hasNextLine()) {
                connection.createStatement().execute(scanner.next());
            }
            scanner.close();
        } catch (IOException | SQLException e) {
            errExit(e.getMessage());
        }
    }

    public static void errExit(String massage) {
        System.err.println(massage);
        System.exit(1);
    }
}
