package edu.school21.sockets.app;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    private static final String ARG_PREFIX_PORT = "--server-port=";
    private static final String SUCCESSFUL_MESSAGE = "Successful!";
    private static final String HOST = "localhost";

    public static void main(String[] args) {
        if (args.length != 1 && !args[0].startsWith(ARG_PREFIX_PORT)) {
            errExit();
        }
        int port = Integer.parseInt(args[0].substring(ARG_PREFIX_PORT.length()));
        Scanner scanner = new Scanner(System.in);
        String input;

        try (Socket socket = new Socket(HOST, port);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream())) {

            while (!socket.isOutputShutdown()) {
                input = in.readUTF();
                System.out.println(input);

                if (input.equals(SUCCESSFUL_MESSAGE)) {
                    break ;
                }
                if (scanner.hasNextLine()) {
                    String message = scanner.nextLine();

                    out.writeUTF(message);
                    out.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        scanner.close();
    }

    private static void errExit() {
        System.err.println("Invalid arguments");
        System.exit(1);
    }
}
