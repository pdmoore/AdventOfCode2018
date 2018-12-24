import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class day24Tests {

    @Test
    @Disabled
    public void parseInput_Immune() {
        List<String> input = Arrays.asList(
                "Immune System:",
                "17 units each with 5390 hit points (weak to radiation, bludgeoning) with an attack that does 4507 fire damage at initiative 2",
                "989 units each with 1274 hit points (immune to fire; weak to bludgeoning, slashing) with an attack that does 25 slashing damage at initiative 3"
        );
    }

    @Test
    public void parseInput_SingleUnit_Weakness_NoImmunity() {
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
    // TODO - parse immunites
    // TODO - show example with both weakness and immunity
    // TODO - handle SLASHING ATTACK

    // TODO handle IMMNUNE SYSTEM COLLECTION
    // TODO handle INFECTION COLLECTION

    // figure out attack logic
    // handle a round of attacks

    // determine end state

    // show result
}
