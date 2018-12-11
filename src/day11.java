import java.awt.geom.Point2D;

public class day11 {


    public static int calculatePowerLevel(int x, int y, int serialNumber) {
        int rackId = x + 10;
        int powerLevel = ((rackId * y) + serialNumber) * rackId;

        int hundredsDigit = hundredsDigitOf(powerLevel);

        return hundredsDigit - 5;
    }

    public static int hundredsDigitOf(int powerLevel) {
        if (powerLevel <= 999) {
            return (powerLevel - (powerLevel % 100)) / 100;
        } else {
            String number = String.format("%d", powerLevel);
            String justHundreds = number.substring(number.length() - 3);
            int hundreds = Integer.parseInt(justHundreds);
            return (hundreds - (hundreds % 100)) / 100;
        }
    }

    public static void populateFuelCells(int[][] fuelCells, int serialNumber) {

        for (int y = 1; y < fuelCells.length; y++) {
            for (int x = 1; x < fuelCells[0].length; x++) {

                int powerLevel = calculatePowerLevel(x, y, serialNumber);
                fuelCells[x][y] = powerLevel;
            }
        }
    }

    public static Point2D findLargestTotalPower(int[][] fuelCells) {
        int sum3x3 = Integer.MIN_VALUE;
        int upperLeft_x = 0;
        int upperLeft_y = 0;

        for (int y = 1; y < fuelCells.length - 3; y++) {
            for (int x = 1; x < fuelCells[0].length - 3; x++) {

                int currentSum = calcSum(fuelCells, x, y);
                if (currentSum > sum3x3) {
                    sum3x3 = currentSum;
                    upperLeft_x = x;
                    upperLeft_y = y;
                }
            }
        }

        System.out.println("x: " + upperLeft_x + "     y: " + upperLeft_y );
        return null;
    }

    public static int calcSum(int[][] fuelCells, int start_x, int start_y) {
        int totalPower = 0;
        for (int y = start_y; y < start_y + 3; y++) {
            for (int x = start_x; x < start_x + 3; x++) {
                totalPower += fuelCells[x][y];
            }
        }

        return totalPower;
    }
}
