import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class day24 {

    public enum ATTACK_TYPE { BLUDGEONING, COLD, FIRE, RADIATION, SLASHING }

    public static day24.Army parseSingleArmyInput(List<String> input) {

        Army army = new Army();

        army.name = input.get(0);
        for (int i = 1; i < input.size(); i++) {
            army.addGroup(parseInputLine(input.get(i)));
        }

        return army;
    }

    public static Simulator parseInput(List<String> input) {
        Simulator sim = new Simulator();

        Army army = new Army();
        int index = 0;
        for (String inputLine :
                input) {
            if (inputLine.isEmpty()) {
                sim.addArmy(army);
                index = 0;
                army = new Army();
            } else if (index == 0) {
                army.name = inputLine;
                Group.resetIdCount();
                index++;
            } else {
                index++;
                army.addGroup(parseInputLine(inputLine));
            }
        }
        sim.addArmy(army);

        return sim;
    }

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
        boolean hasImmunities = input.contains("immune to");
        if (hasImmunities) {
            int leftHand = input.indexOf("immune to") + "immune to".length() + 1;

            int secondParen = input.indexOf(')');
            int semicolon = input.indexOf(';');
            int rightHand = (semicolon > leftHand) ? semicolon : secondParen;
            String attackStrings = input.substring(leftHand, rightHand);
            String[] tokens = attackStrings.split(",");

            return getAttackTypes(tokens);
        }

        return Collections.EMPTY_LIST;
    }

    private static List<ATTACK_TYPE> parseWeakness(String input) {
        // THIS MAY BE ASSUMING WEAKNESS IS ALWAYS FIRST
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
            case "cold"        : return ATTACK_TYPE.COLD;
            case "slashing"    : return ATTACK_TYPE.SLASHING;
        }
        return ATTACK_TYPE.FIRE;
    }

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
        public Group isAttacking;
        public Group isAttackedBy;

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

        public static void resetIdCount() {
            IdCount = 1;
        }

        public int effectivePower() {
            return units * attackDamage;
        }

        public String summary() {
            return String.format("Group %d contains %d units", Id, units);
        }

        public int attackMultiplier(ATTACK_TYPE attackType) {
            if (immunity.contains(attackType)) return 0;

            if (weakness.contains(attackType)) return 2;

            return 1;
        }
    }

    public static class Army {
        public String name;
        List<Group> groups;

        public Army() {
            groups = new ArrayList<>();
        }

        public void addGroup(Group group) {
            groups.add(group);
        }

        public int totalUnits() {
            int total = 0;
            for (Group group :
                    groups) {
                total += group.units;
            }
            return total;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(name);
            sb.append(System.lineSeparator());
            for (Group group :
                    groups) {
                sb.append(group.summary());
                sb.append(System.lineSeparator());
            }

            return sb.toString();
        }
    }

    public static class Simulator {
        public List<Army> armies;
        Army infectionArmy;
        Army immunityArmy;

        public Simulator() {
            armies = new ArrayList<>();
        }

        public void addArmy(Army army) {
            armies.add(army);

            if (army.name.equals("Immune System:")) {
                immunityArmy = army;
            } else if (army.name.equals("Infection:")) {
                infectionArmy = army;
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Army army :
                    armies) {
                sb.append(army.toString());
            }
            return sb.toString();
        }

        public String targetSelection() {
            StringBuilder sb = new StringBuilder();

            targetSelection(sb, "Infection", immunityArmy.groups, infectionArmy.groups);

            targetSelection(sb, "Immune System", infectionArmy.groups, immunityArmy.groups);

            return sb.toString();
        }

        private void targetSelection(StringBuilder sb, String attackingArmyName, List<Group> defendingArmy, List<Group> attackingArmy) {
            for (Group defendingGroup :
                    defendingArmy) {
                defendingGroup.isAttackedBy = null;
            }

            // TODO this needs to be in decreasing order of effective power!
            for (Group attackingGroup :
                    attackingArmy) {

                attackingGroup.isAttacking = null;

                for (Group defendingGroup :
                        defendingArmy) {

                    if (defendingGroup.isAttackedBy != null) continue;

                    int damageAmount = attackingGroup.effectivePower() * defendingGroup.attackMultiplier(attackingGroup.attackType);

                    if (attackingGroup.isAttacking == null) {
                        attackingGroup.isAttacking = defendingGroup;
                        defendingGroup.isAttackedBy = attackingGroup;
                    } else {

                        // is the current attack target taking less damage?
                        // eventually figure out ties
                        int currentDamageAmount = attackingGroup.effectivePower() *
                                attackingGroup.isAttacking.attackMultiplier(attackingGroup.attackType);

                        // TODO need to resolve ties
                        if (damageAmount > currentDamageAmount) {
                            attackingGroup.isAttacking.isAttackedBy = null;
                            attackingGroup.isAttacking = defendingGroup;
                            defendingGroup.isAttackedBy = attackingGroup;
                        }
                    }

                    sb.append(attackingArmyName + " group " + attackingGroup.Id + " would deal ");
                    sb.append("defending group " + defendingGroup.Id + " " + damageAmount + " damage");
                    sb.append(System.lineSeparator());
                }
            }
        }
    }
}
