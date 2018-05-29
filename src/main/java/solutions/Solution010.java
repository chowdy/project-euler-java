package solutions;

import lib.AbstractSolution;
import org.apache.commons.math3.primes.Primes;

/*
 * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
 *
 * Find the sum of all the primes below two million.
 */
public class Solution010 extends AbstractSolution {
    @Override
    public String run() {
        int n = 0;
        int max = 2000000;
        long sum = 0;

        while (true) {
            n++;
            n = Primes.nextPrime(n);
            if (n < max) {
                sum += n;
            } else {
                break;
            }
        }

        return Long.toString(sum);
    }
}
