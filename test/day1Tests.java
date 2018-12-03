import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class day1Tests {

    @Test
    public void parsePositiveLine() {
        int actual = parseLine("+3");
        Assertions.assertEquals(3, actual);
    }

    @Test
    public void parseNegativeLine() {
        int actual = parseLine("-2");
        Assertions.assertEquals(-2, actual);
    }


    private int parseLine(String number) {
        return Integer.parseInt(number);
    }
}
