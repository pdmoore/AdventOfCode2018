import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class day24Tests {

    @Test
    public void parseInput_SingleUnit_Weakness_NoImmunity() {
        day24.Group.resetIdCount();
        String input = "17 units each with 5390 hit points (weak to radiation, bludgeoning) with an attack that does 4507 fire damage at initiative 2";

        day24.Group actual = day24.parseInputLine(input);

        assertAll(
                () -> assertEquals(1, actual.Id),
                () -> assertEquals(17, actual.units),
                () -> assertEquals(5390, actual.hpPerUnit),
                () -> assertEquals(4507, actual.attackDamage),
                () -> assertEquals(day24.ATTACK_TYPE.FIRE, actual.attackType),
                () -> assertEquals(2, actual.initiative),
                () -> assertEquals(2, actual.weakness.size()),
                () -> assertTrue(actual.weakness.contains(day24.ATTACK_TYPE.BLUDGEONING), "weakness to bludgeoning"),
                () -> assertTrue(actual.weakness.contains(day24.ATTACK_TYPE.RADIATION), "weakness to radiation"),
                () -> assertTrue(actual.immunity.isEmpty(), "no immunities")
        );
    }

    @Test
    public void parseInput_SingleUnit_NoImmunityOrWeakness() {
        String input = "442 units each with 1918 hit points with an attack that does 35 fire damage at initiative 8";

        day24.Group actual = day24.parseInputLine(input);

        assertAll(
                () -> assertTrue(actual.immunity.isEmpty(), "no immunity"),
                () -> assertTrue(actual.weakness.isEmpty(), "no weakness")
        );
    }

    @Test
    public void parseInput_SingleUnit_BothImmunityAndWeakness() {
        String input = "989 units each with 1274 hit points (immune to fire; weak to bludgeoning, slashing) with an attack that does 25 slashing damage at initiative 3";

        day24.Group actual = day24.parseInputLine(input);

        assertAll(
                () -> assertEquals(1, actual.immunity.size()),
                () -> assertEquals(day24.ATTACK_TYPE.FIRE, actual.immunity.get(0)),

                () -> assertTrue(actual.weakness.contains(day24.ATTACK_TYPE.SLASHING), "weakness to slashing")
        );
    }

    @Test
    public void parseInput_SingleUnit_AttackType_Cold() {
        String input = "18 units each with 729 hit points (weak to fire; immune to cold, slashing)\n" +
                " with an attack that does 8 radiation damage at initiative 10";

        day24.Group actual = day24.parseInputLine(input);

        assertTrue(actual.immunity.contains(day24.ATTACK_TYPE.COLD), "immune to cold");
    }

    @Test
    public void SingleUnit_EffectivePower() {
        String input = "18 units each with 729 hit points (weak to fire; immune to cold, slashing)\n" +
                " with an attack that does 8 radiation damage at initiative 10";

        day24.Group actual = day24.parseInputLine(input);

        assertEquals(144, actual.effectivePower());
    }

    // TODO handle IMMUNE SYSTEM COLLECTION
    // TODO handle INFECTION COLLECTION

    @Test
    public void parseInput_Immune() {
        List<String> input = Arrays.asList(
                "Immune System:",
                "17 units each with 5390 hit points (weak to radiation, bludgeoning) with an attack that does 4507 fire damage at initiative 2",
                "989 units each with 1274 hit points (immune to fire; weak to bludgeoning, slashing) with an attack that does 25 slashing damage at initiative 3"
        );

        day24.Army actual = day24.parseSingleArmyInput(input);

        assertAll(
                () -> assertEquals("Immune System:", actual.name),
                () -> assertEquals(2, actual.groups.size()),
                () -> assertEquals(17 + 989, actual.totalUnits())
        );
    }

    @Test
    public void parseInput_TwoArmies() {
        List<String> input = utilities.getFileContentsAsStrings("data/aoc18.24.txt");

        day24.Simulator actual = day24.parseInput(input);

        assertEquals(2, actual.armies.size());
    }

    @Test
    public void parseInput_Sample() {
        List<String> input = utilities.getFileContentsAsStrings("data/aoc18.24a.txt");

        day24.Simulator actual = day24.parseInput(input);
        String[] actualTokens = actual.toString().split("\\n");

        String expected = "Immune System:\n" +
                "Group 1 contains 17 units\n" +
                "Group 2 contains 989 units\n" +
                "Infection:\n" +
                "Group 1 contains 801 units\n" +
                "Group 2 contains 4485 units\n";
        String[] expectedTokens = expected.split("\\n");

        assertArrayEquals(expectedTokens, actualTokens);
    }

    @Test
    public void targetSelection_Sample() {
        List<String> input = utilities.getFileContentsAsStrings("data/aoc18.24a.txt");

        day24.Simulator actual = day24.parseInput(input);

        String targetSelection = actual.targetSelection();
        String[] actualTokens = targetSelection.toString().split("\\n");

        String expected = "Infection group 1 would deal defending group 1 185832 damage\n" +
                "Infection group 1 would deal defending group 2 185832 damage\n" +
                "Infection group 2 would deal defending group 2 107640 damage\n" +
                "Immune System group 1 would deal defending group 1 76619 damage\n" +
                "Immune System group 1 would deal defending group 2 153238 damage\n" +
                "Immune System group 2 would deal defending group 1 24725 damage\n";
        String[] expectedTokens = expected.split("\\n");

        assertArrayEquals(expectedTokens, actualTokens);
    }

    @Test
    public void computeResult() {
        List<String> input = Arrays.asList(
                "Immune System:",
                "17 units each with 5390 hit points (weak to radiation, bludgeoning) with an attack that does 4507 fire damage at initiative 2",
                "989 units each with 1274 hit points (immune to fire; weak to bludgeoning, slashing) with an attack that does 25 slashing damage at initiative 3"
        );

        day24.Army actual = day24.parseSingleArmyInput(input);

        assertEquals(17 + 989, actual.totalUnits());
    }


    // done - figure out attack logic Target Selection (for round, no special tie breaker logci)
    // TODO NEXT handle a round of attacks
    // how to take a group out of play?

    // determine end state

    // target selection, ordered by decreasing effective power
    // if tied then ordered by initiative
    // attacking group chooses enemy it would deal the most damage to
    // if damage is equal, then pick one with highest effective power, then highest initiative
    // defending group can only be chosen by one attacking group
    // at end fo targeting each group has picked zero or one to attack and is being attacked by zero or one

}
