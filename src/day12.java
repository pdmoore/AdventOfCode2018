import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class day12 {


    private static final int RULE_LENGTH = 5;

    public static int potSum(String input, int zeroindex) {
        int potSum = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '#') {
                potSum += (i - zeroindex);
            }
        }
        return potSum;
    }

    public static String transformThisCell(String input, HashMap<String, String> rule) {
        String transform = rule.get(input);
        if (transform == null) return ".";
        return transform;
    }

    public static HashMap<String, String> parseRules(List<String> input) {
        HashMap<String, String> rules = new HashMap<>();
        for (String line :
                input) {
            String pattern = line.substring(0, 5);
            String result  = line.substring(9, 10);
            rules.put(pattern, result);
        }

        return rules;
    }

    public static HashMap<String, String> parseRulesFromFile(String filename) {
        List<String> fileContentsAsStrings = utilities.getFileContentsAsStrings(filename);

        List<String> justRulesFromFile = fileContentsAsStrings.subList(2, fileContentsAsStrings.size());
        return parseRules(justRulesFromFile);
    }

    public static String parseInitialState(String input, String prefix) {
        String state = input.substring("initial state ".length() + 1);
        return prefix + state;
    }

    private static String addCalibraton(String prefix) {
        String padLeft = "";
        for (int i = 0; i < prefix.length(); i++) {
            padLeft += " ";
        }
        padLeft += "0";
        return padLeft;
    }

    public static int solution1(String filename) {
        // read file as list of strings
        List<String> fileContentsAsStrings = utilities.getFileContentsAsStrings(filename);

        // parse initial state
        String prefix = "..................................................";
//        String prefix = "...";
        List<String> replay = new ArrayList<>();
        replay.add(addCalibraton(prefix));

        String currentState = parseInitialState(fileContentsAsStrings.get(0), prefix);
        currentState += prefix;

        // parse rules
        HashMap<String, String> rules = parseRules(fileContentsAsStrings.subList(2, fileContentsAsStrings.size()));

        String previousLine = currentState;

        // do 20 times
        for (int i = 0; i < 20; i++) {
            replay.add(previousLine);

            StringBuilder nextLine = new StringBuilder();
            nextLine.append("..");

            // for each element in current line, apply rule and build next line
            for (int pot = 0; pot <= previousLine.length() - RULE_LENGTH; pot++) {
                nextLine.append(transformThisCell(previousLine.substring(pot, pot + RULE_LENGTH), rules));
            }
            nextLine.append("..");
            previousLine = nextLine.toString();
        }

        for (String line :
                replay) {
            System.out.println(line);
        }

        // after 20 times, potSum of the entire string and the zero index (prefix length)
        return potSum(previousLine, prefix.length());
    }

}
