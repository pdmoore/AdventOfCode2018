import java.util.*;

public class day7 {
    public static String solution1(String filename) {
        // read file as strings

        // build dependencies from string list

        // calculate step order



        return null;
    }

    public static String calculateStepOrder(Map<String, List<String>> rules) {
        String stepOrder = "";

        // while there are rules remaining
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
