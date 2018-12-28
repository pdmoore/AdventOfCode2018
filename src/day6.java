import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class day6 {
    public static int solution1(List<String> input) {

        // create list of points
        Board board = parseInput(input);

        // for each point in grid, determine closest neighbor


        // calculate the result, ignoring points on infinite edge

        return 0;
    }

    private static Board parseInput(List<String> input) {
        List<Point> points = new ArrayList<>();

        for (String inputLine :
                input) {
            String[] tokens = inputLine.split(",");
            int col = Integer.parseInt(tokens[0].trim());
            int row = Integer.parseInt(tokens[1].trim());

            points.add(new Point(row, col));
        }

        Board board = new Board(points);

        return board;
    }

    private static class Board {

        // TODO likely to change since input exceeds 26 letters, and may want to track an object grid
        char[][] grid;

        public Board(List<Point> points) {

            //TODO - create grid that's just big enough (kill the 20s)
            int maxRow = locateMaxRow(points);
            int maxY = locateMaxY(points);

            grid = new char[20][20];

            fillGrid(grid, points);

            printGrid(grid);
        }

        private void printGrid(char[][] grid) {
            for (int row = 0; row < grid[0].length; row++) {
                for (int col = 0; col < grid.length; col++) {
                    System.out.print(grid[row][col]);
                }
                System.out.println(System.lineSeparator());
            }

        }

        private void fillGrid(char[][] grid, List<Point> points) {
            char c = 'A';
            for (Point point :
                    points) {
                grid[point.x][point.y] = c++;
            }
        }

        private int locateMaxRow(List<Point> points) {
            int max = 0;
            for (Point point :
                    points) {
                if (point.y > max) max = point.y;
            }
            return max;
        }

        private int locateMaxY(List<Point> points) {
            int max = 0;
            for (Point point :
                    points) {
                if (point.y > max) max = point.y;
            }
            return max;
        }
    }
}
