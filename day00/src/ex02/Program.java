package ex02;

import java.util.Scanner;

public class Program {
    static private final int DELIMITER_NUMBER = 42;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int coffeeRequestCount = 0;

        for (int number = scanner.nextInt(); number != DELIMITER_NUMBER; number = scanner.nextInt()) {
            if (isPrime(sumOfDigits(number))) {
                ++coffeeRequestCount;
            }
        }
        scanner.close();
        System.out.println("Count of coffee-request - " + coffeeRequestCount);
    }

    static private int sumOfDigits(int number) {
        int result = 0;

        do {
            result += number % 10;
            number /= 10;
        } while (number != 0);
        return result;
    }

    static private boolean isPrime(int number) {
        if (number % 2 == 0 && number != 2) {
            return false;
        }

        for (int i = 2; i * i <= number; ++i) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
