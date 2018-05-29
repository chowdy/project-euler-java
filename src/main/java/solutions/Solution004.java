package solutions;

import lib.AbstractSolution;

import static lib.PEUtils.isPallindrome;

/*
 * A palindromic number reads the same both ways. The largest palindrome made from the product of
 * two 2-digit numbers is 9009 = 91 × 99.
 *
 * Find the largest palindrome made from the product of two 3-digit numbers.
 */
public class Solution004 extends AbstractSolution {
    @Override
    public String run() {
        long max = 0;
        for (long i = 100; i < 1000; i++) {
            for (long j = 100; j < 1000; j++) {
                long prod = i*j;
                if (isPallindrome(prod) && prod > max) {
                    max = prod;
                }
            }
        }
        return "" + max;
    }
}
