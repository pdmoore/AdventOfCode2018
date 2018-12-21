import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class day8 {
    private static int sumOfMetadata;

    public static int sumMetadata(String input) {
        String[] tokenItems = input.split(" ");
        List<String> tokens = new ArrayList<>(Arrays.asList(tokenItems));

        sumOfMetadata = 0;
        parseInput(tokens);


        return sumOfMetadata;
    }

    private static void parseInput(List<String> tokens) {
        // while there are elements left
        if (tokens.isEmpty()) return;

        int numChildNodes = Integer.parseInt(tokens.get(0));
        tokens.remove(0);
        int numMetadata  = Integer.parseInt(tokens.get(0));
        tokens.remove(0);

        for (int i = 0; i < numChildNodes; i++) {
            parseInput(tokens);
        }
        processMetadataForNode(tokens, numMetadata);
    }

    private static void processMetadataForNode(List<String> tokens, int numMetadata) {
        for (int i = 0; i < numMetadata; i++) {
            int metadataValue = Integer.parseInt(tokens.get(0));
            tokens.remove(0);
            sumOfMetadata += metadataValue;
        }
    }

    public static int solution1(String inputFile) {
        String input = utilities.fileAsString(inputFile);

        return sumMetadata(input);
    }
}
