package solutions;

import lib.AbstractSolution;

/*
 * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
 *
 * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 */
public class Solution005 extends AbstractSolution {
    @Override
    public String run() {
        long i = 2;
        while (true) {
            for (long j = 2; j <= 20; j++) {
                if (i % j != 0) {
                    break;
                } else if (j == 20) {
                    return i + "";
                }
            }
            i++;
        }
    }
}
