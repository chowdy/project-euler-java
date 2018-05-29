package solutions;

import lib.AbstractSolution;

/*
 * A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
 *
 * a^2 + b^2 = c^2
 * For example, 3^2 + 4^2 = 9 + 16 = 25 = 52.
 *
 * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
 * Find the product abc.
 */
public class Solution009 extends AbstractSolution {
    @Override
    public String run() {
        for (int a = 0; a < 1000; a++) {
            for (int b = a + 1; b < 1000; b++) {
                for (int c = b + 1; c < 1000; c++) {
                    if ((a*a + b*b == c*c) && (a + b + c == 1000)) {
                        return "" + a * b * c;
                    }
                }
            }
        }
        return null;
    }
}
