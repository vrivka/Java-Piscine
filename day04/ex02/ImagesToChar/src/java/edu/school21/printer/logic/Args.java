package edu.school21.printer.logic;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.diogonunes.jcdp.color.api.Ansi;

@Parameters(separators = "=")
public class Args {
    @Parameter(names = "--white", converter = ColorConverter.class)
    public static Ansi.BColor white;

    @Parameter(names = "--black", converter = ColorConverter.class)
    public static Ansi.BColor black;
}
