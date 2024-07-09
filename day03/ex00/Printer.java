public abstract class Printer implements Runnable {
    protected String massage;

    @Override
    public void run() {
        for (int i = 0; i < Program.count; i++) {
            System.out.println(massage);
        }
    }
}
