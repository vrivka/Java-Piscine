package edu.school21.sockets.app;

import edu.school21.sockets.server.Server;

public class Main {
    private static final String ARG_PREFIX_PORT = "--port=";
    private static final String INVALID_ARGUMENT_ERROR = "Invalid Argument";

    public static void main(String[] args) {
        if (args.length != 1 && !args[0].startsWith(ARG_PREFIX_PORT)) {
            System.err.println(INVALID_ARGUMENT_ERROR);
            System.exit(1);
        }
        int port = Integer.parseInt(args[0].substring(ARG_PREFIX_PORT.length()));

        Server server = new Server();
        server.launch(port);
    }
}
