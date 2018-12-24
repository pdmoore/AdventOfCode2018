import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.jar.Attributes;

public class day24 {


    public static Group parseInputLine(String input) {

        String[] tokens = input.split(" ");
        int units = Integer.parseInt(tokens[0]);
        int hitPoints = Integer.parseInt(tokens[4]);

        int initiative = Integer.parseInt(tokens[tokens.length - 1]);
        int attackDamage = Integer.parseInt(tokens[tokens.length - 6]);

        ATTACK_TYPE attackType = parseAttackType(tokens[tokens.length - 5]);

        List<ATTACK_TYPE> weaknesses = parseWeakness(input);
        List<ATTACK_TYPE> immunities  = parseImmunities(input);

        return new Group(units, hitPoints, initiative, attackDamage, attackType, weaknesses, immunities);
    }

    private static List<ATTACK_TYPE> getAttackTypes(String[] tokens) {
        List<ATTACK_TYPE> immunities = new ArrayList<>();
        for (String immunity :
                tokens) {
            immunities.add(parseAttackType(immunity));
        }
        return immunities;
    }

    private static List<ATTACK_TYPE> parseImmunities(String input) {
        // POSSIBLE TO HAVE NO "(", return empty
        int firstParen = input.indexOf('(');
        int secondParen = input.indexOf(')');
        int semicolon = input.indexOf(';');

        boolean hasImmunities = input.contains("immune to");
        if (hasImmunities) {
            int rightHand = (semicolon == -1) ? secondParen : semicolon;
            String attackStrings = input.substring(firstParen + "immune to".length() + 2, rightHand);
            String[] tokens = attackStrings.split(",");

            return getAttackTypes(tokens);
        }

        return Collections.EMPTY_LIST;
    }

    private static List<ATTACK_TYPE> parseWeakness(String input) {
        int firstParen = input.indexOf('(');
        int secondParen = input.indexOf(')');
        int semicolon = input.indexOf(';');
        boolean hasWeakness = input.contains("weak to");
        if (hasWeakness) {
            int leftHand  = (semicolon == -1) ? firstParen  : semicolon;
            int rightHand = secondParen;

            String attackStrings = input.substring(leftHand + "weak to".length() + 2, rightHand);

            String[] tokens = attackStrings.split(",");

            return getAttackTypes(tokens);
        }

        return Collections.emptyList();
    }

    private static ATTACK_TYPE parseAttackType(String token) {
        switch (token.trim()) {
            case "fire"        : return ATTACK_TYPE.FIRE;
            case "radiation"   : return ATTACK_TYPE.RADIATION;
            case "bludgeoning" : return ATTACK_TYPE.BLUDGEONING;
            case "slashing"    : return ATTACK_TYPE.SLASHING;
        }
        return ATTACK_TYPE.FIRE;
    }

    public enum ATTACK_TYPE { BLUDGEONING, FIRE, RADIATION, SLASHING }

    public static class Group {
        private static int IdCount = 1;
        public int Id;
        public int units;
        public int hpPerUnit;
        public int initiative;
        public int attackDamage;
        public ATTACK_TYPE attackType;
        public List<ATTACK_TYPE> weakness;
        public List<ATTACK_TYPE> immunity;

        public Group(int units, int hitPoints, int initiative, int damage, ATTACK_TYPE attackType, List<ATTACK_TYPE> weaknesses, List<ATTACK_TYPE> immunities) {
            Id = IdCount++;

            this.units = units;
            this.hpPerUnit = hitPoints;
            this.initiative = initiative;
            this.attackDamage = damage;
            this.attackType = attackType;
            this.weakness = weaknesses;

            this.immunity = immunities;
        }
    }
}
