import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class day6Tests {

    @Test
    public void solution1_Sample() {
        List<String> input = Arrays.asList(
                "1, 1",
                "1, 6",
                "8, 3",
                "3, 4",
                "5, 5",
                "8, 9");

        assertEquals(17, day6.solution1(input));
    }


}
