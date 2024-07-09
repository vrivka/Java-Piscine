package ex04;

import java.util.Scanner;

public class Program {
    static private final int MAX_CHAR = 65535;
    static private final int MAX_CHAR_OCCURRENCE = 999;
    static private final int MAX_HEIGHT = 10;
    static private final int TOP_CHARS_NUMBER = 10;
    static private final int[] charOccurrences = new int[MAX_CHAR];
    static private final char[] topChars = new char[TOP_CHARS_NUMBER];
    static private final int[] topOccurrences = new int[TOP_CHARS_NUMBER];
    static private final char[][] histogram = new char[TOP_CHARS_NUMBER][MAX_HEIGHT + 2];

    public static void main(String[] args) {
        setCharOccurrences(getLine());
        setTop();
        setHistogram();
        printHistogram();
    }

    static private String getLine() {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        scanner.close();
        return line;
    }

    static private void setCharOccurrences(String line) {
        char[] chars = line.toCharArray();

        for (char c : chars) {
            ++charOccurrences[c];
        }
    }

    static private void setTop() {
        int prevMax = MAX_CHAR_OCCURRENCE + 1;
        int max;
        char maxChar;

        for (int i = 0; i < TOP_CHARS_NUMBER; i++) {
            max = 0;
            maxChar = 0;
            for (int j = 0; j < charOccurrences.length; ++j) {
                int c = charOccurrences[j];

                if (c == 0 || c >= prevMax && isPresent((char)j)) {
                    continue;
                }
                if (max < c) {
                    max = c;
                    maxChar = (char)j;
                }
            }
            prevMax = max;
            topOccurrences[i] = max;
            topChars[i] = maxChar;
        }
    }

    static private boolean isPresent(char c) {
        for (char d : topChars) {
            if (c == d) {
                return true;
            }
        }
        return false;
    }

    static private void setHistogram() {
        int maxValue = topOccurrences[0];

        if (maxValue == 0) {
            System.exit(0);
        }
        for (int i = 0; i < TOP_CHARS_NUMBER; ++i) {
            int columnHeight = topOccurrences[i] * MAX_HEIGHT / maxValue;
            int j = 0;

            histogram[i][j] = 'c';
            for (++j; j < columnHeight + 1; ++j) {
                histogram[i][j] = '#';
            }
            histogram[i][j] = 'd';
        }
    }

    static private void printHistogram() {
        for (int i = MAX_HEIGHT + 1; i >= 0; --i) {
            for (int j = 0; j < TOP_CHARS_NUMBER; ++j) {
                if (histogram[j][i] == 'd') {
                    if (topOccurrences[j] != 0) {
                        System.out.printf("%3d ", topOccurrences[j]);
                    }
                } else if (histogram[j][i] == '#') {
                    System.out.printf("%3c ", histogram[j][i]);
                } else if (histogram[j][i] == 'c') {
                    System.out.printf("%3c ", topChars[j]);
                }
            }
            System.out.println();
        }
    }
}
