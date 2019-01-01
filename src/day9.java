import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class day9 {
    private static Map<Integer, BigInteger> soln2_players;
    private static Node currentMarble;

    public static String solution1(int numPlayers, int lastMarble) {

        initializePlayers(numPlayers);

        initializeBoard();

        int currentPlayer = 1;

        int thisMarbleValue = 1;
        while (thisMarbleValue <= lastMarble) {
            if (thisMarbleValue % 23 == 0) {
                playerScores(currentPlayer, thisMarbleValue);

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

    private static void playerScores(int currentPlayer, int thisMarbleValue) {
        BigInteger curBigInt = soln2_players.get(currentPlayer);

        curBigInt = curBigInt.add(BigInteger.valueOf(thisMarbleValue));

        int marble7Clockwise = removeMarble();
        curBigInt = curBigInt.add(BigInteger.valueOf(marble7Clockwise));

        soln2_players.put(currentPlayer, curBigInt);
    }

    private static String highestScore() {
        BigInteger highScoreBigInteger = new BigInteger("0");

        for (int i = 1; i <= soln2_players.size(); i++) {
            BigInteger thisBigIntScore = soln2_players.get(i);
            if (thisBigIntScore.compareTo(highScoreBigInteger) > 0) {
                highScoreBigInteger = thisBigIntScore;
            }
        }

        return highScoreBigInteger.toString();
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

        soln2_players = new HashMap<>();
        for (int i = 1; i <= numPlayers; i++) {
            soln2_players.put(i, new BigInteger("0"));
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
