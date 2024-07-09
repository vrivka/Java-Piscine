public class Hen implements Runnable {
    private final EggHenPrinter printer;

    public Hen(EggHenPrinter printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        for (int i = 0; i < Program.count; i++) {
            printer.printHen();
        }
    }
}
