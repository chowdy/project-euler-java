package solutions;

import lib.AbstractSolution;

import java.math.BigInteger;
import java.util.Arrays;

/*
 * 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
 * What is the sum of the digits of the number 2^1000?
 */
public class Solution016 extends AbstractSolution {
    @Override
    public String run() {
        int pow = 1000;
        BigInteger num = BigInteger.ONE;
        final BigInteger TWO = new BigInteger("2");

        // Raise 2 to the `pow`th power
        for (int i = 0; i < pow; i++) {
            num = num.multiply(TWO);
        }

        // Sum the digits
        long sum = Arrays.stream(num.toString().split(""))
                .mapToLong(Long::parseLong)
                .sum();

        return String.valueOf(sum);
    }
}
