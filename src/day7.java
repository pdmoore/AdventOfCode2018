import java.util.*;

public class day7 {
    public static String solution1(String filename) {
        List<String> inputLines = utilities.getFileContentsAsStrings(filename);

        Map<String, List<String>> rules = parseInput(inputLines);

        return calculateStepOrder(rules);
    }

    private static Map<String, List<String>> parseInput(List<String> inputLines) {
        Map<String, List<String>> rules = new HashMap<>();

        for (String inputLine :
                inputLines) {
            String dependency = String.valueOf(inputLine.charAt(5));
            String key = String.valueOf(inputLine.charAt(36));

            if (rules.containsKey(key)) {
                List<String> depends = new ArrayList<>(rules.get(key));
                depends.add(dependency);
                rules.replace(key, depends);
            } else {
                List<String> depends = new ArrayList<>();
                depends.add(dependency);
                rules.put(key, depends);

                if (!rules.containsKey(dependency)) {
                    rules.put(dependency, new ArrayList<>());
                }
            }
        }

        return rules;
    }

    public static String calculateStepOrder(Map<String, List<String>> rules) {
        String stepOrder = "";

        while (!rules.keySet().isEmpty()) {
            String readyRule = findKeyWithNoDependency(rules);

            stepOrder += readyRule;

            rules.remove(readyRule);

            removeDependencyFromRules(rules, readyRule);
        }

        return stepOrder;
    }

    private static void removeDependencyFromRules(Map<String, List<String>> rules, String readyRule) {
        Set<String> keys = rules.keySet();
        for (String key :
                keys) {
            List<String> depends = new ArrayList<String>(rules.get(key));
            depends.remove(readyRule);
            rules.replace(key, depends);
        }
    }

    private static String findKeyWithNoDependency(Map<String, List<String>> rules) {
        for (String key :
                rules.keySet()) {
            if (rules.get(key).isEmpty()) return key;
        }

        return null;
    }
}
