package solutions;

import lib.AbstractSolution;
import org.apache.commons.math3.primes.Primes;

/* By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
 *
 * What is the 10 001st prime number?
 */
public class Solution007 extends AbstractSolution {

    @Override
    public String run() {
        int count = 1;
        int current = 2;

        while (count < 10001) {
            current = Primes.nextPrime(current + 1);
            count += 1;
        }

        return Integer.toString(current);
    }
}
