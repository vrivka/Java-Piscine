public class Program {
    public static int count;

    public static void main(String[] args) {
        if (args.length == 1 && args[0].matches("--count=[+]?\\d+")) {
            count = Integer.parseInt(args[0].substring(8));
        } else {
            errExit("Invalid argument");
        }

        Thread eggThread = new Thread(new EggPrinter());
        Thread henThread = new Thread(new HenPrinter());
        HumanPrinter humanPrinter = new HumanPrinter();

        eggThread.start();
        henThread.start();

        try {
            eggThread.join();
            henThread.join();
        } catch (InterruptedException err) {
            errExit(err.getMessage());
        }

        humanPrinter.run();
    }

    private static void errExit(String massage) {
        System.err.println(massage);
        System.exit(1);
    }
}
