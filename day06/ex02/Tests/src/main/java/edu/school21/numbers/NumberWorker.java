package edu.school21.numbers;

public class NumberWorker {

    public static class IllegalNumberException extends RuntimeException {
        public IllegalNumberException(String message) {
            super(message);
        }
    }

    public boolean isPrime(int number) {
        if (number < 2) {
            throw new IllegalNumberException("Number cannot be less then 2");
        }
        if (number % 2 == 0 && number != 2) {
            return false;
        }

        for (long i = 2; i * i <= number; ++i) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public int digitsSum(int number) {
        int result = 0;

        do {
            result += number % 10;
            number /= 10;
        } while (number != 0);
        return result;
    }
}
