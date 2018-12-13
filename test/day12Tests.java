import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class day12Tests {


    @Test
    public void allBlanks_yieldBlank() {
        String input = ".....";
        HashMap<String, String> rules = new HashMap<>();
        rules.put(".....", ".");
        String actual = day12.transformThisCell(input, rules);
        assertEquals(".", actual);
    }

    @Test
    public void allPots_yieldPot() {
        String input = "#####";
        HashMap<String, String> rules = new HashMap<>();
        rules.put("#####", "#");
        String actual = day12.transformThisCell(input, rules);
        assertEquals("#", actual);
    }

    @Test
    public void mix_yieldMix() {
        String input = "###.#";
        HashMap<String, String> rules = new HashMap<>();
        rules.put("###.#", "#");
        String actual = day12.transformThisCell(input, rules);
        assertEquals("#", actual);
    }

    @Test
    public void parseRulesLine() {
        List<String> input = new ArrayList<>();
        input.add("#..#. => .");
        input.add(".#..# => #");
        input.add("..#.# => .");
        input.add("..... => .");

        HashMap<String, String> rules = day12.parseRules(input);
        assertEquals(4, rules.size());
        assertEquals(".", rules.get("#..#."));  // sample one of the input
    }

    @Test
    public void trasnformThisLine() {
        HashMap<String, String> rules = getRulesList();

        String input = "...#..#.#..##......###...###...........";
        String expected = "...#...#....#.....#..#..#..#...........";


        StringBuilder nextLine = new StringBuilder();
        nextLine.append("..");
        for (int pot = 0; pot <= input.length() - 5; pot++) {
            String note = input.substring(pot, pot + 5);
            nextLine.append(day12.transformThisCell(note, rules));
        }
        nextLine.append("..");

        String actual = nextLine.toString();
        assertEquals(expected, actual);
    }

    private HashMap<String, String> getRulesList() {
        List<String> input = new ArrayList<>();

        input.add("...## => #");
        input.add("..#.. => #");
        input.add(".#... => #");
        input.add(".#.#. => #");
        input.add(".#.## => #");
        input.add(".##.. => #");
        input.add(".#### => #");
        input.add("#.#.# => #");
        input.add("#.### => #");
        input.add("##.#. => #");
        input.add("##.## => #");
        input.add("###.. => #");
        input.add("###.# => #");
        input.add("####. => #");

        return day12.parseRules(input);
    }


    @Test
    public void potSum() {
        String input = ".#....##....#####...#######....#.#..##.";
        int zeroindex = 3;
        int actual = day12.potSum(input, zeroindex);
        assertEquals(325, actual);
    }

    @Test
    public void potSum_live() {
        String input = "......#..#.###....#......#.....#....#...........#....#..#..#......##..#....#........#..###.#....##....#....#....#..#......#....#........................................................................";
        int zeroindex = 50;
        int actual = day12.potSum(input, zeroindex);
        assertEquals(472, actual);
    }

    @Test
    public void potSum_withPrefix() {
        String prefix = ".....";
        String input = prefix + ".#....##....#####...#######....#.#..##.";
        int zeroindex = 3 + prefix.length();
        int actual = day12.potSum(input, zeroindex);
        assertEquals(325, actual);
    }

    @Test
    public void parseRulesFromFile() {
        String filename = "data/aoc18.12.txt";
        HashMap<String, String> rules = day12.parseRulesFromFile(filename);
        assertEquals(32, rules.size());
    }

    @Test
    public void day12_solution1() {
        String filename = "data/aoc18.12.txt";

        int result = day12.solution1(filename);
        assertEquals(1672, result);
    }

    @Test
    public void day12_solution1_sample() {
        String filename = "data/aoc18.12a.txt";

        int result = day12.solution1(filename);
        assertEquals(325, result);
    }
}
