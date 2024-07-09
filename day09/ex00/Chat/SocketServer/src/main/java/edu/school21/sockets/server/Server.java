package edu.school21.sockets.server;

import edu.school21.sockets.services.UsersService;
import edu.school21.sockets.services.UsersServiceImpl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Server {
    private static final String HELLO_MESSAGE = "Hello from Server!";
    private static final String SIGNUP_ENTRY_MESSAGE = "signUp";
    private static final String ENTER_USERNAME_MESSAGE = "Enter username:";
    private static final String ENTER_PASSWORD_MESSAGE = "Enter password:";
    private static final String SUCCESSFUL_MESSAGE = "Successful!";
    private static final String FAILED_MESSAGE = "User whit this name already exist";
    private static final String UNKNOWN_COMMAND_MESSAGE = "Unknown command. Only signUp command available";

    private final UsersService usersService;

    public Server() {
        ApplicationContext context = new AnnotationConfigApplicationContext("edu.school21.sockets");
        usersService = context.getBean(UsersServiceImpl.class);
    }

    public void launch(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream())) {
            sendMessage(out, HELLO_MESSAGE);
            String entry;

            while (!socket.isClosed()) {
                entry = in.readUTF();

                if (entry.equals(SIGNUP_ENTRY_MESSAGE)) {
                    String username;
                    String password;

                    sendMessage(out, ENTER_USERNAME_MESSAGE);
                    username = in.readUTF();

                    sendMessage(out, ENTER_PASSWORD_MESSAGE);
                    password = in.readUTF();

                    if (!usersService.registration(username, password)) {
                        sendMessage(out, FAILED_MESSAGE);
                    } else {
                        sendMessage(out, SUCCESSFUL_MESSAGE);
                        break;
                    }
                } else {
                    sendMessage(out, UNKNOWN_COMMAND_MESSAGE);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(DataOutputStream out, String message) throws IOException {
        out.writeUTF(message);
        out.flush();
    }
}
