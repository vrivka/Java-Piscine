package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import edu.school21.printer.app.Program;
import java.io.IOException;
import java.io.InputStream;

public class ImagePrinter {
    private final BufferedImage image;

    public ImagePrinter() throws IOException {
        InputStream imageFile = Program.class.getClassLoader().getResourceAsStream("resources/it.bmp");

        if (imageFile == null) {
            throw new IllegalArgumentException("Image file it.bmp does not exist");
        }
        image = ImageIO.read(imageFile);
    }

    public void printImage() {
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int color = image.getRGB(x, y);

                if (color == 0xffffffff) {
                    System.out.print(Program.whiteCharacter);
                } else if (color == 0xff000000) {
                    System.out.print(Program.blackCharacter);
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }
}
