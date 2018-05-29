package solutions;

import lib.AbstractSolution;
import lib.PEUtils;

/*
 * The prime factors of 13195 are 5, 7, 13 and 29.
 *
 * What is the largest prime factor of the number 600851475143 ?
 */
public class Solution003 extends AbstractSolution {
    @Override
    public String run() {
        return "" + PEUtils.primeFactors(600851475143L).stream()
                .mapToLong((i) -> i)
                .max()
                .getAsLong();
    }
}
