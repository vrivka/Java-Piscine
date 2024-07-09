package ex03;

import java.util.Scanner;

public class Program {
    static private final String DELIMITER_STRING = "42";
    static private final int MAX_WEEK_COUNT = 18;
    static private final int MAX_GRADE_COUNT = 5;
    static private final int MAX_GRADE = 9;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int week = 1;
        long result = 0;

        for (; week <= MAX_WEEK_COUNT; ++week) {
            String intput = scanner.nextLine();
            if (intput.equals(DELIMITER_STRING)) {
                break;
            } else if (!intput.equals("Week " + week)) {
                errorExit();
            }
            result *= 10;
            result += getMinGrade(scanner);
        }
        scanner.close();
        printResult(result, week - 1);
    }

    static void printResult(long result, int week) {
        if (week == 0) {
            return;
        }
        printResult(result / 10, week - 1);
        System.out.print("Week " + week + ' ');
        for (long i = result % 10; i > 0; --i) {
            System.out.print("=");
        }
        System.out.println(">");
    }

    static private int getMinGrade(Scanner scanner) {
        int minGrade = MAX_GRADE + 1;

        for (int i = 0; i < MAX_GRADE_COUNT; ++i) {
            int grade = scanner.nextInt();

            if (minGrade > grade) {
                minGrade = grade;
            }
        }
        scanner.nextLine();
        return minGrade;
    }

    static private void errorExit() {
        System.err.println("IllegalArgument");
        System.exit(-1);
    }
}
