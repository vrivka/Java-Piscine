public class EggHenPrinter {
    private boolean queue = true;

    public synchronized void printEgg() {
        try {
            if (!queue) {
                wait();
            }
        } catch (InterruptedException err) {
            Program.errExit(err.getMessage());
        }

        System.out.println("Egg");
        queue = false;
        notify();
    }

    public synchronized void printHen() {
        try {
            if (queue) {
                wait();
            }
        } catch (InterruptedException err) {
            Program.errExit(err.getMessage());
        }

        System.out.println("Hen");
        queue = true;
        notify();
    }
}
