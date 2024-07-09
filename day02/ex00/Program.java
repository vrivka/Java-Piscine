import java.io.IOException;
import java.util.Scanner;

public class Program {
    private static final String DELIMITER = "42";

    public static void main(String[] args) {
        SignaturesReader signaturesReader = new SignaturesReader("signatures.txt");
        FileWriter resultWriter = new FileWriter();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            try {
                String line = scanner.nextLine();
                if (line.equals(DELIMITER)) {
                    break;
                }
                FileReader fileReader = new FileReader(line);

                fileReader.setResult(signaturesReader.getSignaturesMap());

                resultWriter.addResult(fileReader.getResult());
            } catch (IOException err) {
                System.err.println(err.getMessage());
            }
        }
        scanner.close();
        resultWriter.writeResult("result.txt");
    }
}
