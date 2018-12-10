import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class utilities {
    static List<String> getFileContentsAsStrings(String filename) {
        List<String> lines = new ArrayList<>();
        try {
            File f = new File(filename);
            Scanner scanner = new Scanner(f);

            while (scanner.hasNext()) {
                lines.add(scanner.nextLine());
            }
        } catch(Exception err){
            err.printStackTrace();
        }
        return lines;
    }
}
