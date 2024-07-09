import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileReader {
    List<Integer> fileContent = new ArrayList<>();
    StringBuilder result = new StringBuilder();

    public FileReader(String path) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            for (int i = fileInputStream.read(); i != -1; i = fileInputStream.read()) {
                fileContent.add(i);
            }
        }
    }

    public void setResult(Map<List<Integer>, String> signatures) {
        StringBuilder foundedSignature = new StringBuilder();

        signatures.forEach((signature, signatureName) -> {
            if (fileContent.size() < signature.size()) {
                return;
            }
            if (fileContent.subList(0, signature.size()).containsAll(signature)) {
                foundedSignature.append(signatureName).append('\n');
            }
        });

        if (foundedSignature.toString().isEmpty()) {
            foundedSignature.append("UNDEFINED\n");
        }
        result.append(foundedSignature);
        System.out.println("PROCESSED");
    }

    public String getResult() {
        return result.toString();
    }
}
