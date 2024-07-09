package edu.school21.printer.app;

import edu.school21.printer.logic.ImagePrinter;
import java.io.IOException;

public class Program {
    public static char whiteCharacter;
    public static char blackCharacter;
    public static String pathToImage;

    public static void main(String[] args) {
        if (args.length == 3 &&
                args[0].matches("--whiteCharacter=.") &&
                args[1].matches("--blackCharacter=.") &&
                args[2].startsWith("--pathToImage=/")) {
            whiteCharacter = args[0].charAt(17);
            blackCharacter = args[1].charAt(17);
            pathToImage = args[2].substring(14);
        } else {
            errExit("Invalid argument");
        }

        try {
            ImagePrinter imagePrinter = new ImagePrinter();

            imagePrinter.printImage();
        } catch (IOException err) {
            errExit(err.getMessage());
        }
    }

    public static void errExit(String massage) {
        System.err.println(massage);
        System.exit(1);
    }
}
