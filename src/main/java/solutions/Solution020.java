package solutions;

import lib.AbstractSolution;

import java.math.BigInteger;
import java.util.Arrays;

import static lib.PEUtils.factorial;

/*
 * n! means n × (n − 1) × ... × 3 × 2 × 1
 *
 * For example, 10! = 10 × 9 × ... × 3 × 2 × 1 = 3628800,
 * and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.
 *
 * Find the sum of the digits in the number 100!
 */
public class Solution020 extends AbstractSolution {
    @Override
    public String run() {
        BigInteger num = factorial(new BigInteger("100"));
        int sum = Arrays.stream(num.toString().split(""))
                .map(Integer::parseInt)
                .reduce(0, (a,b) -> a + b);
        return String.valueOf(sum);
    }
}
