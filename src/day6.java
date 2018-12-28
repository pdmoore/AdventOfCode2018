import java.awt.*;
import java.util.*;
import java.util.List;

public class day6 {
    public static int solution1(List<String> input) {

        // create list of points
        Board board = parseInput(input);


        // parse input does everything - maybe break that up?

        // for each point in grid, determine closest neighbor

        board.fillClosestCoordinates();


        board.printGrid();

        // calculate the result, ignoring points on infinite edge
        return board.countOfLargestArea();
    }

    private static Board parseInput(List<String> input) {
        List<Point> points = new ArrayList<>();

        for (String inputLine :
                input) {
            String[] tokens = inputLine.split(",");
            int row = Integer.parseInt(tokens[0].trim());
            int col = Integer.parseInt(tokens[1].trim());

            points.add(new Point(col, row));
        }

        Board board = new Board(points);

        return board;
    }

    public static int manhattanDistanceBetween(Point p1, Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    private static class Board {

        private final List<Point> initialCoordinates;
        // TODO likely to change since input exceeds 26 letters, and may want to track an object grid
        char[][] grid;
        private int colMinimum;
        private int colMaximum;
        private int rowMinimum;
        private int rowMaximum;

        public Board(List<Point> points) {

            initialCoordinates = points;

            int maxRow = locateMaxRow(points);
            int maxCol = locateMaxCol(points);

            grid = new char[maxRow + 2][maxCol + 2];

            fillStartingGrid(grid, points);

            locateGridBoundaries(points);
        }

        private void fillStartingGrid(char[][] grid, List<Point> points) {
            char c = 'A';
            for (Point point :
                    points) {
                grid[point.x][point.y] = c++;
            }
        }

        private void locateGridBoundaries(List<Point> points) {
            colMinimum = Integer.MAX_VALUE;
            colMaximum = Integer.MIN_VALUE;
            rowMinimum = Integer.MAX_VALUE;
            rowMaximum = Integer.MIN_VALUE;

            for (Point point :
                    points) {

                if (point.y < colMinimum) colMinimum = point.y;
                if (point.y > colMaximum) colMaximum = point.y;

                if (point.x < rowMinimum) rowMinimum = point.x;
                if (point.x > rowMaximum) rowMaximum = point.x;
            }
        }

        public void fillClosestCoordinates() {
            fillClosestCoordinates(grid, initialCoordinates);
        }

        void fillClosestCoordinates(char[][] grid, List<Point> points) {

            //Brute force (ok for small grid and few points)
            for (int row = 0; row < numRows(grid); row++) {
                for (int col = 0; col < numCol(grid); col++) {

                    // if this cell is a coordinate, skip it
                    if (points.contains(new Point(row, col))) continue;

                    char gridChar = '.';
                    int shortestDistance = Integer.MAX_VALUE;
                    for (Point coordinate :
                            points) {
                        Point target = new Point(row, col);
                        if (points.contains(target)) continue;

                        int distance = manhattanDistanceBetween(coordinate, target);
                        if (distance < shortestDistance) {
                            shortestDistance = distance;
                            gridChar = Character.toLowerCase(grid[coordinate.x][coordinate.y]);
                        } else if (distance == shortestDistance) {
                            gridChar = '.';
                        }
                    }

                    grid[row][col] = gridChar;
                }
            }
        }

        void printGrid() {
            for (int row = 0; row < numRows(grid); row++) {
                for (int col = 0; col < numCol(grid); col++) {
                    System.out.print(grid[row][col]);
                }
                System.out.println(System.lineSeparator());
            }

        }

        private int numCol(char[][] grid) {
            return grid[0].length;
        }

        private int numRows(char[][] grid) {
            return grid.length;
        }

        private int locateMaxCol(List<Point> points) {
            int max = 0;
            for (Point point :
                    points) {
                if (point.y > max) max = point.y;
            }
            return max;
        }

        private int locateMaxRow(List<Point> points) {
            int max = 0;
            for (Point point :
                    points) {
                if (point.x > max) max = point.x;
            }
            return max;
        }

        public int countOfLargestArea() {

            Map<Character, Integer> cellCounts = new HashMap<>();

            for (int row = rowMinimum; row < rowMaximum; row++) {
                for (int col = colMinimum; col < colMaximum; col++) {

                    Character gridValue = grid[row][col];
                    if (gridValue.equals('.')) continue;
                    if (Character.isUpperCase(gridValue)) continue;

                    if (cellCounts.containsKey(gridValue)) {
                        Integer count = cellCounts.get(gridValue);
                        count++;
                        cellCounts.put(gridValue, count);
                    } else {
                        cellCounts.put(gridValue, 1);
                    }
                }
            }

            int max = Collections.max(cellCounts.values());

            return max + 1;
        }
    }
}
