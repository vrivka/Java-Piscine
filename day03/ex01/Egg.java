public class Egg implements Runnable {
    private final EggHenPrinter printer;

    public Egg(EggHenPrinter printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        for (int i = 0; i < Program.count; i++) {
            printer.printEgg();
        }
    }
}
