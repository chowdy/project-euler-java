package solutions;

import lib.AbstractSolution;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.stream.Collectors;

/*
 * Surprisingly there are only three numbers that can be written as the sum of fourth powers of their digits:
 *
 * 1634 = 1^4 + 6^4 + 3^4 + 4^4
 * 8208 = 8^4 + 2^4 + 0^4 + 8^4
 * 9474 = 9^4 + 4^4 + 7^4 + 4^4
 * As 1 = 1^4 is not a sum it is not included.
 *
 * The sum of these numbers is 1634 + 8208 + 9474 = 19316.
 *
 * Find the sum of all the numbers that can be written as the sum of fifth powers of their digits.
 */
public class Solution030 extends AbstractSolution{
    @Override
    public String run() throws Exception {
        int power = 4;
        BigDecimal answer = BigDecimal.ZERO;
        for (BigDecimal i = new BigDecimal(power);
             i.compareTo(new BigDecimal(29524)) <= 0;
             i = i.add(BigDecimal.ONE))
        {
            System.out.println(i.toString());

            BigDecimal val = Arrays.stream(i.toString().split(""))
                    .map((s) -> new BigDecimal(s).pow(power))
                    .reduce(BigDecimal.ZERO, (a,b) -> a.add(b));

            if (val.compareTo(i) == 0) {
                answer.add(i);
            }
        }

        return answer.toString();
    }
}
