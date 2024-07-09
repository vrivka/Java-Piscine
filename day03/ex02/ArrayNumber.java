import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArrayNumber {
    private List<Integer> array;

    public ArrayNumber(int size) {
        array = new ArrayList<>(size);
        Random generator = new Random();

        for (int i = 0; i < size; i++) {
            array.add(generator.nextInt(Program.MAX_VALUE) * (generator.nextBoolean() ? 1 : -1));
        }
    }

    public List<Integer> getArray() {
        return array;
    }

    public int sum() {
        int sum = 0;

        for (Integer number : array) {
            sum += number;
        }
        return sum;
    }
}
