package ex01;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number;

        if (!scanner.hasNextInt()) {
            errorExit();
        }
        number = scanner.nextInt();
        scanner.close();
        if (number < 2) errorExit();
        printIsPrime(number);
    }

    static private void printIsPrime(int number) {
        int iterCount = 1;
        boolean result = true;

        if (number % 2 == 0 && number != 2) {
            result = false;
        } else {
            for (int i = 2; i * i <= number; ++i, ++iterCount) {
                if (number % i == 0) {
                    result = false;
                    break;
                }
            }
        }
        System.out.println(result + " " + iterCount);
    }

    static private void errorExit() {
        System.err.println("IllegalArgument");
        System.exit(-1);
    }
}
