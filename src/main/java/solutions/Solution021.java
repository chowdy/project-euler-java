package solutions;

import lib.AbstractSolution;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static lib.PEUtils.sumOfDivisors;

/*
 * Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).
 * If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair and each of a and b are
 * called amicable numbers.
 *
 * For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110;
 * therefore d(220) = 284. The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.
 *
 * Evaluate the sum of all the amicable numbers under 10000.
 */
public class Solution021 extends AbstractSolution {

    @Override
    public String run() {

        int max = 10000;

        Set<Long> amicables = new HashSet<>();

        for (int i = 1; i < max; i++) {
            long sumA = sumOfDivisors(i);
            for (long j = i + 1; j < max; j++) {
                long sumB = sumOfDivisors(j);
                if (sumA == j && sumB == i) {
                    amicables.add(new Long(j));
                    amicables.add(new Long(i));
                }
            }
        }
        System.out.println(amicables.size());
        return amicables.stream().reduce(0L,(a,b)->a+b) + "";
    }
}
