import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class day14Tests {

    @Test
    public void example_firstStep() {
        List<Integer> recipeScores = new ArrayList<>();
        recipeScores.add(3);
        recipeScores.add(7);

        List<Integer> expected = Arrays.asList(3, 7, 1, 0);

        List<Integer> actual = day14.createRecipes(recipeScores, 1);

        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void example_secondStep() {
        List<Integer> recipeScores = new ArrayList<>();
        recipeScores.add(3);
        recipeScores.add(7);

        List<Integer> actual = day14.createRecipes(recipeScores, 2);

        List<Integer> expected = Arrays.asList(3, 7, 1, 0, 1, 0);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void example_thirdStep() {
        List<Integer> recipeScores = new ArrayList<>();
        recipeScores.add(3);
        recipeScores.add(7);

        List<Integer> actual = day14.createRecipes(recipeScores,3);

        List<Integer> expected = Arrays.asList(3, 7, 1, 0, 1, 0, 1);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }


    @Test
    public void example_fifthStep() {
        List<Integer> recipeScores = new ArrayList<>();
        recipeScores.add(3);
        recipeScores.add(7);

        List<Integer> actual = day14.createRecipes(recipeScores,5);

        List<Integer> expected = Arrays.asList(3, 7, 1, 0, 1, 0, 1, 2, 4);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void example_fifteenthStep() {
        List<Integer> recipeScores = new ArrayList<>();
        recipeScores.add(3);
        recipeScores.add(7);

        List<Integer> actual = day14.createRecipes(recipeScores,15);

        List<Integer> expected = Arrays.asList(3, 7, 1, 0, 1, 0, 1, 2, 4, 5, 1, 5, 8, 9, 1, 6, 7, 7, 9, 2);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void solution1_steps() {
        List<Integer> recipeScores = new ArrayList<>();
        recipeScores.add(0);
        recipeScores.add(8);
        recipeScores.add(4);
        recipeScores.add(6);
        recipeScores.add(0);
        recipeScores.add(1);

        List<Integer> actual = day14.createRecipes(recipeScores,4);

        List<Integer> expected = Arrays.asList(0, 8, 4, 6, 0, 1, 8, 1, 4, 1, 6, 1, 2);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void example_answer_after_9_iterations() {
        List<Integer> recipeScores = new ArrayList<>();
        recipeScores.add(3);
        recipeScores.add(7);

        List<Integer> resultingScores = day14.createRecipes(recipeScores, 15);
        String actual = day14.answer(resultingScores, 9);

        assertEquals("5158916779", actual);
    }

    @Test
    public void example_answer_after_18_iterations() {
        List<Integer> recipeScores = new ArrayList<>();
        recipeScores.add(3);
        recipeScores.add(7);

        List<Integer> resultingScores = day14.createRecipes(recipeScores, 30);
        String actual = day14.answer(resultingScores, 18);

        assertEquals("9251071085", actual);
    }

    @Test
    public void example_answer_after_2018_iterations() {
        List<Integer> recipeScores = new ArrayList<>();
        recipeScores.add(3);
        recipeScores.add(7);

        List<Integer> resultingScores = day14.createRecipes(recipeScores, 3000);
        String actual = day14.answer(resultingScores, 2018);

        assertEquals("5941429882", actual);
    }

    @Test
    public void solution1() {
        List<Integer> recipeScores = new ArrayList<>();
        recipeScores.add(3);
        recipeScores.add(7);

        List<Integer> resultingScores = day14.createRecipes(recipeScores, 70000);
        assertEquals(90828, resultingScores.size());
        String actual = day14.answer(resultingScores, 84601);

        assertEquals("2688510125", actual);
    }

    @Test
    @Disabled
    public void solution2() {
        List<Integer> recipeScores = new ArrayList<>();
        recipeScores.add(3);
        recipeScores.add(7);

        List<Integer> resultingScores = day14.createRecipes(recipeScores, 70000);
        assertEquals(90828, resultingScores.size());

        int actual = day12.solution2(resultingScores, "084601");

        assertEquals(-99, actual);
    }


    @Test
    public void appendSingleDigitScore() {
        List<Integer> recipeScores = new ArrayList<>();
        recipeScores.add(3);
        recipeScores.add(7);

        int score = 5;
        List<Integer> actual = day14.appendScores(recipeScores, score);

        List<Integer> expected = Arrays.asList(3, 7, 5);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void appendTwoDigitScore() {
        List<Integer> recipeScores = new ArrayList<>();
        recipeScores.add(3);
        recipeScores.add(7);

        int score = 62;
        List<Integer> actual = day14.appendScores(recipeScores, score);

        List<Integer> expected = Arrays.asList(3, 7, 6, 2);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void calcNextIndex() {
        List<Integer> recipeScores = Arrays.asList(3, 7, 1, 0);
        assertEquals(0, day14.calcNextIndex(recipeScores, 0));
        assertEquals(1, day14.calcNextIndex(recipeScores, 1));

        recipeScores = Arrays.asList(3, 7, 1, 0, 1, 0);
        assertEquals(4, day14.calcNextIndex(recipeScores, 0));
        assertEquals(3, day14.calcNextIndex(recipeScores, 1));

        recipeScores = Arrays.asList(3, 7, 1, 0, 1, 0, 1);
        assertEquals(6, day14.calcNextIndex(recipeScores, 4));
        assertEquals(4, day14.calcNextIndex(recipeScores, 3));

        recipeScores = Arrays.asList(3, 7, 1, 0, 1, 0, 1, 2);
        assertEquals(0, day14.calcNextIndex(recipeScores, 6));
        assertEquals(6, day14.calcNextIndex(recipeScores, 4));
    }
}
