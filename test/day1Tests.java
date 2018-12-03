import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class day1Tests {

    @Test
    public void parsePositiveLine() {
        int actual = day1.parseLine("+3");
        Assertions.assertEquals(3, actual);
    }

    @Test
    public void parseNegativeLine() {
        int actual = day1.parseLine("-2");
        Assertions.assertEquals(-2, actual);
    }

    @Test
    public void solution() {
        day1.solution_1();
    }

}
