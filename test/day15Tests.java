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
    // movement logic

    // end of game detection
    // scoring at the end

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


    @Test
    public void Attack_PickWeakestFoe() {
        char[][] map = new char[][] {
                {'#', '#', '#', '#', '#' },
                {'#', 'G', 'E', '.', '#' },
                {'#', 'E', 'G', 'E', '#' },
                {'#', '.', 'E', '.', '#' },
                {'#', '#', '#', '#', '#' },
        };

        Day15 day15 = new Day15(map);
        day15.combat();

        assertEquals(20298, day15.result());
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
            while (keepFighting()) {
                rounds++;

                for (int row = 0; row < map.length; row++) {
                    for (int col = 0; col < map[0].length; col++) {
                        char whoIs = map[row][col];
                        if (whoIs == 'E') {

                            Unit elf = getUnitAt(row, col);

                            Unit goblin = getWeakestAdjacentFoe(elf);
                            if (goblin != null) {
                                attackOn(goblin);
                                if (!keepFighting()) return;
                            } else {

                                // otherwise move towards nearest foe
                            }
                        } else if (whoIs == 'G') {

                            Unit goblin = getUnitAt(row, col);

                            Unit elf = getWeakestAdjacentFoe(goblin);
                            if (elf != null) {
                                attackOn(elf);
                                if (!keepFighting()) return;
                            } else {
                                // otherwise move towards nearest foe
                            }
                        }
                    }
                }
            }
        }

        // @TODO - flesh out all the rules
        // ignore null adjacents
        // affirm adjacent is foe
        // return lowest hp of those guys
        private Unit getWeakestAdjacentFoe(Unit unit) {

            // Puzzle input provides a border on all sides, no need to check for out-of-bounds exceptions

            List<Unit> foes = new ArrayList<>();

            Unit unitAbove = getUnitAt(unit.row - 1, unit.col);
            if (unitAbove != null && unitAbove.isFoeOf(unit)) foes.add(unitAbove);

            Unit unitLeft = getUnitAt(unit.row, unit.col - 1);
            if (unitLeft != null && unitLeft.isFoeOf(unit))  foes.add(unitLeft);

            Unit unitRight = getUnitAt(unit.row, unit.col + 1);
            if (unitRight != null && unitRight.isFoeOf(unit)) foes.add(unitRight);

            Unit unitBelow = getUnitAt(unit.row + 1, unit.col);
            if (unitBelow != null && unitBelow.isFoeOf(unit)) foes.add(unitBelow);

            if (foes.size() == 0) return null;

//            if (unit.isElf()) return unitRight;
//            if (unit.isGoblin()) return unitLeft;

            return weakestFoe(foes);
        }

        private Unit weakestFoe(List<Unit> foes) {
            Unit weakestFoe = null;
            for (Unit foe:
            foes) {
                if (weakestFoe == null) {
                    weakestFoe = foe;
                } else if (foe.hp < weakestFoe.hp) {
                    weakestFoe = foe;
                }
            }

            return weakestFoe;
        }

        private Unit getUnitAt(int row, int col) {
            for (Unit unit :
                    units) {
                if (unit.isLocation(row, col)) return unit;
            }

            return null;
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

            map[unit.row][unit.col] = '.';

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

            public boolean isElf() {
                return type == 'E';
            }

            public boolean isGoblin() {
                return type == 'G';
            }

            public boolean isLocation(int row, int col) {
                return this.row == row && this.col == col;
            }

            public boolean isFoeOf(Unit unit) {
                if (this.isElf()) return unit.isGoblin();

                if (this.isGoblin()) return unit.isElf();

                return false;
            }
        }
    }
}
