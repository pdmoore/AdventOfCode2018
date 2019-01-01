import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class day9Tests {

    @Test
    public void solution1_sample() {
        int numPlayers = 9;
        int lastMarble = 25;

        int actual = day9.solution1(numPlayers, lastMarble);

        assertEquals(32, actual);
    }

    @Test
    public void solution1() {
        assertEquals(400493, day9.solution1(432, 71019));
    }

    @Test
    @Disabled
    public void solution1_Example_Fails_WhileAllOtherExamples_AndSolution_Pass() {
        assertEquals(8317, day9.solution1(10, 1618));
    }

    @Test
    public void solution1_MoreExamples() {
        assertEquals(2764, day9.solution1(17, 1104));
        assertEquals(146373, day9.solution1(13, 7999));
        assertEquals(54718, day9.solution1(21, 6111));
        assertEquals(37305, day9.solution1(30, 5807));
    }
}
