package solutions;

import lib.AbstractSolution;

import java.math.BigInteger;

import static lib.PEUtils.factorial;

/*
 * 145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145.
 *
 * Find the sum of all numbers which are equal to the sum of the factorial of their digits.
 *
 * Note: as 1! = 1 and 2! = 2 are not sums they are not included.
 */
public class Solution034 extends AbstractSolution {
    @Override
    public String run() throws Exception {

        // 9!*6 has 7 digits
        // 9!*7 has 7 digits as well, so let's use 9!*7 as an upper bound
        long ceiling = factorial(9) * 7;

        long answer = 0;
        for (long i = 3; i <= ceiling; i++) {
            // Why does this stream approach hang @ 400?
            /*
            long sum = String.valueOf(i).chars()
                    .mapToLong((c) -> Long.valueOf(c) - 48)
                    .reduce(0, (a,b) -> factorial(a) + factorial(b));
                    */

            long sum = 0;
            for (char c : String.valueOf(i).toCharArray()) {
                long j = Long.valueOf(c) - 48;
                sum += factorial(j);
            }
            if (sum == i) {
                answer += i;
            }
        }

        return answer + "";
    }
}
