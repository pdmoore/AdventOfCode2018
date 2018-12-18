import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class day17Tests {

    @Test
    public void Open_to_Tree_rule() {
        char[][] map = new char[][] {
                {'.', '|', '.'},
                {'|', '.', '|'},
                {'.', '.', '.'}
        };

        char[][] expected = new char[][] {
                {'.', '|', '.'},
                {'|', '|', '|'},
                {'.', '.', '.'}
        };

        char[][] actual = day17.generateNextMap(map);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void Tree_to_Lumberyard_rule() {
        char[][] map = new char[][] {
                {'|', '#', '.'},
                {'#', '|', '#'},
                {'.', '.', '.'}
        };

        char[][] expected = new char[][] {
                {'|', '#', '.'},
                {'#', '#', '#'},
                {'.', '.', '.'}
        };

        char[][] actual = day17.generateNextMap(map);

        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void Lumberyard_to_Lumberyard_rule() {
        char[][] map = new char[][] {
                {'|', '#', '.'},
                {'#', '.', '|'},
                {'#', '.', '#'}
        };

        char[][] expected = new char[][] {
                {'|', '#', '.'},
                {'#', '.', '|'},
                {'.', '.', '.'}
        };

        char[][] actual = day17.generateNextMap(map);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void CalculateResult() {
        char[][] map = new char[][] {
                { '.', '|', '|', '#', '#', '.', '.', '.', '.', '.' },
                { '|', '|', '#', '#', '#', '.', '.', '.', '.', '.' },
                { '|', '|', '#', '#', '.', '.', '.', '.', '.', '.' },
                { '|', '#', '#', '.', '.', '.', '.', '.', '#', '#' },
                { '|', '#', '#', '.', '.', '.', '.', '.', '#', '#' },
                { '|', '#', '#', '.', '.', '.', '.', '#', '#', '|' },
                { '|', '|', '#', '#', '.', '#', '#', '#', '#', '|' },
                { '|', '|', '#', '#', '#', '#', '#', '|', '|', '|' },
                { '|', '|', '|', '|', '#', '|', '|', '|', '|', '|' },
                { '|', '|', '|', '|', '|', '|', '|', '|', '|', '|' },

        };

        int expected = 37 * 31;
        int actual = day17.result(map);
        assertEquals(expected, actual);
    }

    @Test
    public void solution1_example() {
        // pass in example file name
        String filename = "data/aoc18.17a.txt";

        int actual = day17.solution1(filename, 10);

        int expected = 37 * 31;
        assertEquals(expected, actual);
    }

    @Test
    public void solution1() {
        // pass in example file name
        String filename = "data/aoc18.17.txt";

        int actual = day17.solution1(filename, 10);

        int expected = 543312;
        assertEquals(expected, actual);
    }

    @Test
    @Disabled
    public void solution2() {
        // There is a cycle starting at rep 439 and runs 28 cycles (467 repeats the next cycle).
        // used excel to get the right answer - need to clean up the code to generate the result for soln2


        String filename = "data/aoc18.17.txt";

        int actual = day17.solution2(filename, 2000);

        int expected = 199064;
        assertEquals(expected, actual);
    }
}
