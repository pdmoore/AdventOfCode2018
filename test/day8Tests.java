import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class day8Tests {

    @Test
    public void solution1_example() {
        String input = "2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2";

        int actual = day8.sumMetadata(input);
        assertEquals(138, actual);
    }

    @Test
    public void solution1() {
        String inputFile = "data/aoc18.8.txt";
        int actual = day8.solution1(inputFile);
        assertEquals(48496, actual);
    }
}
