import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.*;

public class day7Tests {

    // parse a line to get a rule

    // process rules

    // print result


    @Test
    @Disabled
    public void solution1_example() {
        String filename = "data/aoc18.7a.txt";

        String actual = day7.solution1(filename);

        Assertions.assertEquals("CABDFE", actual);
    }

    @Test
    public void calculateStepOrder() {
        Map<String, List<String>> rules = new HashMap<>();
        List<String> a_depends = Arrays.asList("C");
        rules.put("A", a_depends);
        List<String> b_depends = Arrays.asList("A");
        rules.put("B", b_depends);
//        List<String> c_depends = Arrays.asList(null);
        rules.put("C", Collections.EMPTY_LIST);
        List<String> d_depends = Arrays.asList("A");
        rules.put("D", d_depends);
        List<String> e_depends = Arrays.asList("B", "D", "F");
        rules.put("E", e_depends);
        List<String> f_depends = Arrays.asList("C");
        rules.put("F", f_depends);

        String actual = day7.calculateStepOrder(rules);

        Assertions.assertEquals("CABDFE", actual);
    }

}
