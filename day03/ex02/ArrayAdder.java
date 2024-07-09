import java.util.List;

public class ArrayAdder implements Runnable {
    private final List<Integer> toSumArray;
    private final int threadIndex;
    private final int fromIndex;
    private final int toIndex;

    public ArrayAdder(List<Integer> numberArray, int fromIndex, int toIndex, int threadIndex) {
        this.toIndex = Math.min(numberArray.size(), toIndex);
        this.fromIndex = fromIndex;
        this.toSumArray = numberArray.subList(this.fromIndex, this.toIndex);
        this.threadIndex = threadIndex;
    }

    @Override
    public void run() {
        int sum = 0;

        for (Integer number : toSumArray) {
            sum += number;
        }
        System.out.println("Thread " + threadIndex + ": from " + fromIndex + " to " + (toIndex - 1) + " sum is " + sum);
        Program.sumThead += sum;
    }
}
