package edu.school21.printer.app;

import edu.school21.printer.logic.ImagePrinter;

public class Program {
    public static char whiteCharacter;
    public static char blackCharacter;

    public static void main(String[] args) {
        if (args.length == 2 &&
                args[0].matches("--whiteCharacter=.") &&
                args[1].matches("--blackCharacter=.")) {
            whiteCharacter = args[0].charAt(17);
            blackCharacter = args[1].charAt(17);
        } else {
            errExit("Invalid argument");
        }

        try {
            ImagePrinter imagePrinter = new ImagePrinter();

            imagePrinter.printImage();
        } catch (Exception err) {
            errExit(err.getMessage());
        }
    }

    public static void errExit(String massage) {
        System.err.println(massage);
        System.exit(1);
    }
}
