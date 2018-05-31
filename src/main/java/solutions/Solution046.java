package solutions;

import lib.AbstractSolution;
import org.apache.commons.math3.primes.Primes;

/*
 * It was proposed by Christian Goldbach that every odd composite number can be written as the sum of a
 * prime and twice a square.
 *
 * 9 = 7 + 2×1^2
 * 15 = 7 + 2×2^2
 * 21 = 3 + 2×3^2
 * 25 = 7 + 2×3^2
 * 27 = 19 + 2×2^2
 * 33 = 31 + 2×1^2
 *
 * It turns out that the conjecture was false.
 *
 * What is the smallest odd composite that cannot be written as the sum of a prime and twice a square?
 */
public class Solution046 extends AbstractSolution {
    @Override
    public String run() throws Exception {

        for (int i = 3; true; i += 2) {
            System.out.println(i);

            if (Primes.isPrime(i)) {
                continue;
            }

            // Loop through all of the primes smaller than i
            boolean doable = false;
            for (int p = Primes.nextPrime(1); p < i; p = Primes.nextPrime(p+1)) {
                int d = i - p;

                // Try to make d with twice a square
                for (int j = 1; j < p; j++) {
                    if (d == 2 * j * j) {
                        doable = true;
                        break;
                    }
                }

                if (doable) {
                    break;
                }
            }

            if (!doable) {
                return i + "";
            }

        }

    }
}
