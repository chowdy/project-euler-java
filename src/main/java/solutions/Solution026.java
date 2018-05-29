package solutions;

import lib.AbstractSolution;

import java.math.BigDecimal;
import java.math.RoundingMode;

/*
 * A unit fraction contains 1 in the numerator. The decimal representation of the unit fractions with
 * denominators 2 to 10 are given:
 *
 * 1/2	= 	0.5
 * 1/3	= 	0.(3)
 * 1/4	= 	0.25
 * 1/5	= 	0.2
 * 1/6	= 	0.1(6)
 * 1/7	= 	0.(142857)
 * 1/8	= 	0.125
 * 1/9	= 	0.(1)
 * 1/10	= 	0.1
 *
 * Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can be seen that 1/7 has a 6-digit
 * recurring cycle.
 *
 * Find the value of d < 1000 for which 1/d contains the longest recurring cycle in its decimal fraction part.
 */
public class Solution026 extends AbstractSolution {
    @Override
    public String run() throws Exception {

        int max = 1000;
        int maxCycleNum = 0;
        int maxCycleLength = 0;

        for (int i = 2; i <= max; i++) {
            String s = BigDecimal.ONE.divide(new BigDecimal(i), 1000, RoundingMode.HALF_UP).toString();
            s = s.substring(2);
            for (int j = 1; j < s.length() - 2; j++) {
                String cycle = s.substring(0,j);
                String remainder = s.replaceAll(cycle, "");
                if (remainder.equals("") ||
                        (remainder.length() < cycle.length() &&
                        cycle.substring(0,remainder.length()).equals(remainder))) {
                    if (j > maxCycleLength) {
                        maxCycleLength = j;
                        maxCycleNum = i;
                        //System.out.println("New Max: " + j + " for 1/" + i + " = 0." + s);
                    }
                    break;
                }
            }
        }

        return maxCycleNum + "";
    }
}
