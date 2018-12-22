import java.awt.*;

public class day22 {

    public static final char ROCKY  = '.';
    public static final char WET    = '=';
    public static final char NARROW = '|';

    
    public static int calculateRiskLevel(char[][] caveSystem, Point target) {
        int riskLevel = 0;
        for (int y = 0; y <= target.y; y++) {
            for (int x = 0; x <= target.x; x++) {
                switch (caveSystem[x][y]) {
                    case WET:    riskLevel += 1; break;
                    case NARROW: riskLevel += 2; break;
                }
            }
        }

        return riskLevel;
    }
}
