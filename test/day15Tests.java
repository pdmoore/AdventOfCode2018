import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class day15Tests {


    // read file and set up map, elves, and goblins
    // day 13 used a char[][] structure
    // battle logic
    //  - target selection when more than one
    //  - attack
    // end of game detection
    // scoring at the end
    // movement logic

    // character array # . E G
    // list of goblins
    // list of elves

    // counter of rounds



    @Test
    public void createMap_oneOfEachCharacter() {
        char[][] map = new char[][] {
                {'#', '#', '#', '#', '#' },
                {'#', '.', 'E', 'G', '#' },
                {'#', 'G', '.', 'E', '#' },
                {'#', 'E', '.', '.', '#' },
                {'#', '#', '#', '#', '#' },
        };

        Day15 day15 = new Day15(map);

        assertEquals(3, day15.elfCount());
        assertEquals(2, day15.goblinCount());

    }

    @Test
    public void Attack_LeftRight() {
        char[][] map = new char[][] {
                {'#', '#', '#', '#', '#' },
                {'#', '.', 'E', 'G', '#' },
                {'#', '#', '#', '#', '#' },
        };

        Day15 day15 = new Day15(map);
        day15.combat();

        assertEquals(134, day15.result());

    }

    private class Day15 {
        char[][] map;
        List<Unit> units;
        private int goblinCount;
        private int elfCount;
        private int rounds;

        public Day15(char[][] input) {
            map = new char[input.length][input[0].length];
            units = new ArrayList<>();

            for (int row = 0; row < input.length; row++) {
                for (int col = 0; col < input[0].length; col++) {
                    map[row][col] = input[row][col];

                    if (map[row][col] == 'E') {
                        addAnElf(row, col);
                    } else if(map[row][col] == 'G') {
                        AddGoblin(row, col);
                    }
                }
            }

            rounds = 0;
        }

        private void AddGoblin(int row, int col) {
            Unit goblin = new Unit(row, col, 'G');
            units.add(goblin);
            goblinCount++;
        }

        private void addAnElf(int row, int col) {
            Unit elf = new Unit(row, col, 'E');
            units.add(elf);
            elfCount++;
        }

        public int elfCount() {
            return elfCount;
        }

        public int goblinCount() {
            return goblinCount;
        }

        public void combat() {

//@TODO - do this as nested for loops within the while loop
            // Detect who to beat up on based on who is adjacent to unit

            while (keepFighting()) {
                rounds++;

                // need to scan row by col

                Unit elf = units.get(0);
                Unit goblin = units.get(1);

                attackOn(goblin);
                if (goblinCount == 0) break;

                attackOn(elf);
                if (elfCount == 0) break;

            }
        }

        private void attackOn(Unit unit) {
            unit.takeDamage();
            if (unit.hp <= 0) {
                kill(unit);
            }
        }

        private void kill(Unit unit) {
            if (unit.isGoblin()) {
                goblinCount--;
            } else {
                elfCount--;
            }
            units.remove(unit);
        }

        private boolean keepFighting() {
            return (elfCount > 0) && (goblinCount > 0);
        }

        public int result() {
            int hpSum = 0;
            for (Unit unit :
                    units) {
                hpSum += unit.hp;
            }
            return rounds * hpSum;
        }

        private class Unit {
            public char type;
            public int row;
            public int col;
            public int hp;

            public Unit(int row, int col, char type) {
                this.type = type;
                this.row = row;
                this.col = col;
                this.hp = 200;
            }

            public void takeDamage() {
                hp -= 3;
            }

            public boolean isGoblin() {
                return type == 'G';
            }
        }
    }
}
