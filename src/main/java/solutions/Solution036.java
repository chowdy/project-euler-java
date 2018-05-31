package solutions;

import lib.AbstractSolution;
import static lib.PEUtils.*;

/*
 * The decimal number, 585 = 10010010012 (binary), is palindromic in both bases.
 *
 * Find the sum of all numbers, less than one million, which are palindromic in base 10 and base 2.
 *
 * (Please note that the palindromic number, in either base, may not include leading zeros.)
 */
public class Solution036 extends AbstractSolution {
    @Override
    public String run() throws Exception {
        int answer = 0;

        for (int i = 1; i < 1000000; i++) {
            if (isPallindrome(i) && isPallindrome(Integer.toString(i, 2))) {
                answer += i;
            }
        }

        return answer + "";
    }
}
