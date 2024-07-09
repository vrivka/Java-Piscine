package edu.school21.printer.app;

import com.beust.jcommander.JCommander;
import edu.school21.printer.logic.Args;
import edu.school21.printer.logic.ImagePrinter;

public class Program {

    public static void main(String[] args) {
        Args argv = new Args();

        try {
            JCommander.newBuilder()
                    .addObject(argv)
                    .build()
                    .parse(args);
        } catch (IllegalArgumentException err) {
            errExit(err.getMessage());
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
