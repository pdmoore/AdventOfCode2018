import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;

public class day22Tests {



    @Test
    public void calculateRiskLevel() {
        String filename = "data/aoc18.22a.txt";
        List<String> inputAsStrings = utilities.getFileContentsAsStrings(filename);
        char[][] caveSystem = utilities.convertInputToMap(inputAsStrings);

        Point target = new Point(10, 10);
        int actual = day22.calculateRiskLevel(caveSystem, target);

        Assertions.assertEquals(114, actual);
    }
}
