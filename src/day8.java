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
        if (tokens.isEmpty()) return;

        int numChildNodes = Integer.parseInt(tokens.get(0));
        tokens.remove(0);
        int numMetadata = Integer.parseInt(tokens.get(0));
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

    public static int solution2(String inputFile) {
        String input = utilities.fileAsString(inputFile);

        return valueOfNode(input);
    }


    public static int valueOfNode(String input) {
        String[] tokenItems = input.split(" ");
        List<String> tokens = new ArrayList<>(Arrays.asList(tokenItems));

        Node root = buildTree(null, tokens);

//        return valueOfNode(root);
        return root.value;
    }

    private static Node buildTree(Node parent, List<String> tokens) {
        if (tokens.isEmpty()) return null;

        int numChildNodes = Integer.parseInt(tokens.get(0));
        tokens.remove(0);

        int numMetadata = Integer.parseInt(tokens.get(0));
        tokens.remove(0);

        Node node = new Node();
        List<Node> children = new ArrayList<>();
        for (int i = 0; i < numChildNodes; i++) {
            children.add(buildTree(node, tokens));
        }
        node.children = children;

        List<String> metadata = new ArrayList<>();
        for (int i = 0; i < numMetadata; i++) {
            String metadataValue = tokens.get(0);
            tokens.remove(0);
            metadata.add(metadataValue);
        }
        node.metadata = metadata;
        node.computeValue();

        return node;
    }

//    private static int valueOfNode(Node node) {
//        int value = 0;
//
//        for (int i = 0; i < node.metadata.size(); i++) {
//            int childIndex = Integer.parseInt(node.metadata.get(i));
//            if (childIndex < node.children.size()) {
//                value += node.children.get(childIndex - 1).value();
//            }
//        }
//
//        return value;
//    }

    private static class Node {
        Node parent;
        List<String> metadata;
        List<Node> children;
        int value;

//        public int value() {
//            int value = 0;
//            for (int i = 0; i < metadata.size(); i++) {
//                value += Integer.parseInt(metadata.get(i));
//            }
//
//            return value;
//        }
//
        public void computeValue() {
            value = 0;

            if (children.isEmpty()) {
                for (int i = 0; i < metadata.size(); i++) {
                    value += Integer.parseInt(metadata.get(i));
                }
            } else {
                for (int i = 0; i < metadata.size(); i++) {
                    int childIndex = Integer.parseInt(metadata.get(i));
                    if (childIndex <= children.size()) {
                        value += children.get(childIndex - 1).value;
                    }
                }
            }
        }
    }
}
