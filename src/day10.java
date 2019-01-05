import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class day10 {
    public static day10.Something parseLine(String input) {
        int positionStart = input.indexOf("<");
        int positionEnd   = input.indexOf(">");

        int velocityStart = input.indexOf("<", positionEnd);
        int velocityEnd   = input.indexOf(">", velocityStart);

        String position = input.substring(positionStart + 1, positionEnd);
        String velocity = input.substring(velocityStart + 1, velocityEnd);

        String positionTokens[] = position.split(",");
        String velocityTokens[] = velocity.split(",");

        Something something = new Something();

        something.position = new Point(Integer.parseInt(positionTokens[0].trim()), Integer.parseInt(positionTokens[1].trim()));
        something.velocity = new Point(Integer.parseInt(velocityTokens[0].trim()), Integer.parseInt(velocityTokens[1].trim()));

        return something;
    }

    public static char[][] createBoard(String filename) {
        List<String> fileContentsAsStrings = utilities.getFileContentsAsStrings(filename);

        List<Something> somethings = createPoints(fileContentsAsStrings);

        // TODO need to track the smallest/largest x and y values as they are read
        // since they can be negative, need to span smallest neg to largest pos
        int maxColumn = 20;
        int maxRow    = 14;

        char[][] board = new char[maxRow][maxColumn];

        return board;
    }

    private static List<Something> createPoints(List<String> fileContentsAsStrings) {
        List<Something> points = new ArrayList<>();
        for (String inputLine :
                fileContentsAsStrings) {
            Something point = parseLine(inputLine);
            points.add(point);
        }
        return points;
    }

    public static class Something {
        public Point position;
        public Point velocity;
    }
}
