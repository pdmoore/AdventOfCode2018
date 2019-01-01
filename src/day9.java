import java.util.HashMap;
import java.util.Map;

public class day9 {
    private static Map<Integer, Integer> players;
    private static Node currentMarble;

    public static int solution1(int numPlayers, int lastMarble) {

        initializePlayers(numPlayers);

        initializeBoard();

        int currentPlayer = 1;

        int thisMarbleValue = 1;
        while (thisMarbleValue <= lastMarble) {
            if (thisMarbleValue % 23 == 0) {
                int currentplayerScore = players.get(currentPlayer);
                currentplayerScore += thisMarbleValue;

                int marble7Clockwise = removeMarble();
                currentplayerScore += marble7Clockwise;

                players.put(currentPlayer, currentplayerScore);
            } else {
                insertMarble(thisMarbleValue);

            }

            currentPlayer++;
            if (currentPlayer > numPlayers) {
                currentPlayer = 1;
            }

            thisMarbleValue++;
        }

        return highestScore();
    }

    private static int highestScore() {
        int highScore = 0;
        for (int i = 1; i < players.size(); i++) {
            int thisScore = players.get(i);
            if (thisScore > highScore) {
                highScore = thisScore;
            }
        }
        return highScore;
    }

    private static int removeMarble() {

        Node marbleToRemove = currentMarble.prev.prev.prev.prev.prev.prev.prev;
        Node newCurrentMarble = marbleToRemove.next;

        int marbleValue = marbleToRemove.marbleValue;

        marbleToRemove.prev.next = marbleToRemove.next;
        marbleToRemove.next.prev = marbleToRemove.prev;

        currentMarble = newCurrentMarble;
        return marbleValue;
    }

    private static void insertMarble(int marbleValue) {

        Node insertAt = currentMarble.next;

        Node newMarble = new Node(marbleValue);

        newMarble.next = insertAt.next;
        newMarble.prev = insertAt;

        insertAt.next = newMarble;
        newMarble.next.prev = newMarble;

        currentMarble = newMarble;
    }

    private static void initializeBoard() {
        Node initialPlacement = new Node(0);
        initialPlacement.next = initialPlacement;
        initialPlacement.prev = initialPlacement;

        currentMarble = initialPlacement;
    }

    private static void initializePlayers(int numPlayers) {

        players = new HashMap<>();
        for (int i = 1; i <= numPlayers; i++) {
            players.put(i, 0);
        }
    }

    static class Node {
        int marbleValue;
        Node next;
        Node prev;

        public Node(int marbleValue) {
            this.marbleValue = marbleValue;
        }
    }
}
