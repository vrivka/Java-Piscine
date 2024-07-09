public class Program {
    public static final int MAX_VALUE = 1000;
    public static final int MAX_ARRAY_SIZE = 2_000_000;
    private static int arraySize;
    private static int threadsCount;
    public static int sumThead = 0;

    public static void main(String[] args) {
        if (args.length == 2 && args[0].matches("--arraySize=[+]?\\d+") && args[1].matches("--threadsCount=[+]?\\d+")) {
            arraySize = Integer.parseInt(args[0].substring(12));
            threadsCount = Integer.parseInt(args[1].substring(15));
        } else {
            errExit("Invalid argument");
        }

        if (arraySize > MAX_ARRAY_SIZE) {
            errExit("Array size cannot be greater then 2 000 000");
        } else if (arraySize < threadsCount) {
            errExit("Threads count cannot be greater then array size");
        }

        ArrayNumber numbers = new ArrayNumber(arraySize);
        Thread[] threads = new Thread[threadsCount];
        int interval = (int) Math.ceil((double)arraySize / threadsCount);
        int fromIndex;
        int toIndex;

        for (int i = 0; i < threadsCount; i++) {
            fromIndex = i * interval;
            toIndex = (i + 1) * interval;
            threads[i] = new Thread(new ArrayAdder(numbers.getArray(), fromIndex, toIndex, i + 1));
        }

        System.out.println("Sum: " + numbers.sum());

        for (Thread thread : threads) {
            thread.start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException err) {
            errExit(err.getMessage());
        }

        System.out.println("Sum by threads: " + sumThead);
    }

    public static void errExit(String massage) {
        System.err.println(massage);
        System.exit(1);
    }
}
