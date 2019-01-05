import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class day10Tests {

    @Test
    public void parseLine() {
        String input = "position=< 9,  1> velocity=< 0,  2>";

        day10.PointOfLight actual = day10.parseLine(input);

        Point expectedPosition = new Point(9, 1);
        assertEquals(expectedPosition, actual.position);

        Point expectedVelocity = new Point(0, 2);
        assertEquals(expectedVelocity, actual.velocity);
    }

    @Test
    public void parseLine_NegativeValue() {
        String negativeValueInput = "position=<10, -3> velocity=<-1,  1>";
        day10.PointOfLight actual = day10.parseLine(negativeValueInput);

        Point expectedPosition = new Point(10, -3);
        assertEquals(expectedPosition, actual.position);

        Point expectedVelocity = new Point(-1, 1);
        assertEquals(expectedVelocity, actual.velocity);
    }


    // read in all sample input
    // store in char[][] array
    // be able to print out char array
    // do a clock tick, compare to 1 second clock tick

    // run clock for a while, look for aligned group of 6 or 7 vertical/horizontal adjacent points


    @Test
    public void solution1_sample_InitialBoard() {
        String inputFile = "data/aoc18.10a.txt";

        day10.Board actual = day10.createBoard(inputFile);

        assertEquals(16, actual.grid.length, "number of rows");
        assertEquals(22, actual.grid[0].length, "number of columns");
    }

    @Test
    public void solution1_sample_3Seconds() {
        String inputFile = "data/aoc18.10a.txt";

        day10.Board actual = day10.createBoard(inputFile);
        actual.tick();
        actual.tick();
        actual.tick();

        // catch the runtime exception, do a board print out
    }

    @Test
    public void solution1() {
        String inputFile = "data/aoc18.10.txt";

        day10.Board actual = day10.createBoard(inputFile);
        actual.animate();
    }
}
