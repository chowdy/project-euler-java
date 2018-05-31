package solutions;

import com.google.common.collect.Collections2;
import lib.AbstractSolution;
import static lib.PEUtils.*;

import java.util.*;
import java.util.stream.Collectors;

/*
 * We shall say that an n-digit number is pandigital if it makes use of all the digits 1 to n exactly once;
 * for example, the 5-digit number, 15234, is 1 through 5 pandigital.
 *
 * The product 7254 is unusual, as the identity, 39 × 186 = 7254, containing multiplicand, multiplier, and
 * product is 1 through 9 pandigital.
 *
 * Find the sum of all products whose multiplicand/multiplier/product identity can be written as a
 * 1 through 9 pandigital.
 *
 * HINT: Some products can be obtained in more than one way so be sure to only include it once in your sum.
 */
public class Solution032  extends AbstractSolution {
    @Override
    public String run() throws Exception {
        Set<Integer> answers = new HashSet<>();

        Collection<List<String>> allPandigitalEquations =
                Collections2.permutations(Arrays.asList("123456789*=".split("")));

        // Try each pandigital equation. If it's valid, then add its product to the answers hash
        int i = 0;
        int numPermutations = allPandigitalEquations.size();
        for (List<String> equation : allPandigitalEquations) {

            String eqn = equation.stream().reduce("", (a,b) -> a+b);
            int equalsIndex = eqn.indexOf("=");
            int multIndex = eqn.indexOf("*");

            //System.out.println(((float)++i / numPermutations) * 100);

            // Ignore equations where the = comes before the *
            if (equalsIndex < multIndex ||
                    multIndex == 0 ||
                    equalsIndex == 0 ||
                    equalsIndex == eqn.length() - 1)
            {
                continue;
            }

            try {
                int mand = Integer.valueOf(eqn.substring(0, multIndex));
                int mier = Integer.valueOf(eqn.substring(multIndex + 1, equalsIndex));
                int prod = Integer.valueOf(eqn.substring(equalsIndex + 1));

                if (mand * mier == prod) {
                    answers.add(prod);
                }


            } catch (NumberFormatException e) {}
        }

        return answers.stream().mapToInt(Integer::intValue).sum() + "";
    }
}
