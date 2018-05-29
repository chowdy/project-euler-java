package solutions;

import lib.AbstractSolution;
import org.apache.commons.math3.primes.Primes;

/*
 * Euler discovered the remarkable quadratic formula:
 *
 * n^2 + n + 41
 *
 * It turns out that the formula will produce 40 primes for the consecutive integer values 0≤n≤390≤n≤39.
 * However, when n=40, 40^2 + 40 + 41 = 40(40 + 1) + 41 is divisible by 41, and certainly when
 * n=41, 41^2 + 41 + 41 is clearly divisible by 41.
 *
 * The incredible formula n^2 − 79n + 1601 was discovered, which produces 80 primes for the consecutive
 * values 0≤n≤790≤n≤79. The product of the coefficients, −79 and 1601, is −126479.
 *
 * Considering quadratics of the form:
 *
 * n^2 + an + b, where |a| < 1000 and |b| ≤ 1000
 *
 * where |n| is the modulus/absolute value of n
 * e.g. |11| = 11 and |−4| = 4
 * Find the product of the coefficients, aa and bb, for the quadratic expression that produces the maximum
 * number of primes for consecutive values of n, starting with n=0.
 */
public class Solution027 extends AbstractSolution {
    @Override
    public String run() throws Exception {

        int maxNumPrimes = 0;
        int maxProduct = 0;

        for (int a = -999; Math.abs(a) < 1000; a++) {
            for (int b = -1000; Math.abs(b) <= 1000; b++) {
                int n = 0;
                int primes = 0;
                while(true) {
                    if (Primes.isPrime(n*n + a*n + b)) {
                        primes++;
                        n++;
                    } else {
                        break;
                    }
                }
                if (primes > maxNumPrimes) {
                    maxNumPrimes = primes;
                    maxProduct = a*b;
                }
            }
        }

        return maxProduct + "";
    }
}
