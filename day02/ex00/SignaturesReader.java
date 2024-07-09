import java.io.FileInputStream;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class SignaturesReader {
    private final Map<List<Integer>, String> signatures = new HashMap<>();

    public SignaturesReader(String path) {
        List<String> buffer = new ArrayList<>();

        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            Scanner scanner = new Scanner(fileInputStream);

            while (scanner.hasNextLine()) {
                buffer.add(scanner.nextLine());
            }
            scanner.close();
        } catch (Exception err) {
            System.err.println(err.getMessage());
            System.exit(1);
        }

        for (String s : buffer) {
            String[] splitLine = s.split("[, ]");
            List<Integer> tmp = new ArrayList<>();

            for (int i = 1; i < splitLine.length; ++i) {
                if (splitLine[i].isEmpty()) {
                    continue ;
                }
                tmp.add(Integer.parseInt(splitLine[i], 16));
            }
            signatures.put(tmp, splitLine[0]);
        }
    }

    public Map<List<Integer>, String> getSignaturesMap() {
        return signatures;
    }
}
