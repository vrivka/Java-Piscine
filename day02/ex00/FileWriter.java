import java.io.FileOutputStream;
import java.io.IOException;

public class FileWriter {
    private String result = "";

    public void addResult(String additionalString) {
        result += additionalString;
    }

    public void writeResult(String path) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(path)) {
            fileOutputStream.write(result.getBytes());
        } catch (IOException err) {
            System.err.println(err.getMessage());
            System.exit(1);
        }
    }
}
