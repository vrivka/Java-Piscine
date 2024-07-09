package edu.school21.printer.logic;

import com.beust.jcommander.IStringConverter;
import com.diogonunes.jcdp.color.api.Ansi;

public class ColorConverter implements IStringConverter<Ansi.BColor> {
    @Override
    public Ansi.BColor convert(String s) {
        switch (s) {
            case "BLACK": return Ansi.BColor.BLACK;
            case "BLUE": return Ansi.BColor.BLUE;
            case "CYAN": return Ansi.BColor.CYAN;
            case "GREEN": return Ansi.BColor.GREEN;
            case "MAGENTA": return Ansi.BColor.MAGENTA;
            case "NONE": return Ansi.BColor.NONE;
            case "RED": return Ansi.BColor.RED;
            case "WHITE": return Ansi.BColor.WHITE;
            case "YELLOW": return Ansi.BColor.YELLOW;
            default: throw new IllegalArgumentException("Unknown color: " + s);
        }
    }
}
