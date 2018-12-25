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

        // TODO - decide if to capture output as gold master and compare
        System.out.println(actual.toString());
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


    // figure out attack logic
    // handle a round of attacks

    // determine end state

    // target selection, ordered by decreasing effective power
    // if tied then ordered by initiative
    // attacking group chooses enemy it would deal the most damage to
    // if damage is equal, then pick one with highest effective power, then highest initiative
    // defending group can only be chosen by one attacking group
    // at end fo targeting each group has picked zero or one to attack and is being attacked by zero or one
    
}
