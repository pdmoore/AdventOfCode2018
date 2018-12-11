import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Point2D;

public class day11Tests {

    public static final int GRID_SERIAL_NUMBER = 1133;

    @Test
    public void calculatePowerLevel() {
        int x = 3;
        int y = 5;
        int serialNumber = 8;

        int actual = day11.calculatePowerLevel(x, y, serialNumber);

        Assertions.assertEquals(4, actual);

        Assertions.assertEquals(-5, day11.calculatePowerLevel(122, 79, 57));
        Assertions.assertEquals(0, day11.calculatePowerLevel(217, 196, 39));
        Assertions.assertEquals(4, day11.calculatePowerLevel(101, 153, 71));
    }

    @Test
    public void hundredsDigit() {
        Assertions.assertEquals(0, day11.hundredsDigitOf(4));
        Assertions.assertEquals(0, day11.hundredsDigitOf(89));
        Assertions.assertEquals(9, day11.hundredsDigitOf(949));
        Assertions.assertEquals(3, day11.hundredsDigitOf(2345));
        Assertions.assertEquals(6, day11.hundredsDigitOf(12645));
    }

    @Test
    public void calcSum() {
        int[][] input = new int[][] {
                { -2, -4, 4,  4,  4 },
                { -4,  4, 4,  4, -5 },
                {  4,  3, 3,  4, -4 },
                {  1,  1, 2,  4, -3 },
                { -1,  0, 2, -5, -2 }
        };

        int actual = day11.calcSum(input, 1, 1);
        Assertions.assertEquals(29, actual);
    }


    @Test
    public void day11_solution1() {
        int[][] fuelCells = new int[301][301];
        day11.populateFuelCells(fuelCells, GRID_SERIAL_NUMBER);

        Point actual = day11.findLargestTotalPower(fuelCells);
        Assertions.assertEquals(235, actual.getX());
        Assertions.assertEquals(14, actual.getY());
    }

    @Test
    @Disabled("not included in unit suite, really slow")
    public void day11_solution2() {
        int[][] fuelCells = new int[301][301];
        day11.populateFuelCells(fuelCells, GRID_SERIAL_NUMBER);

        day11.findLargestTotalPowerOfAllGrids(fuelCells);
    }
}
