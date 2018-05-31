package solutions;

import lib.AbstractSolution;
import org.apache.commons.math3.primes.Primes;

import java.util.ArrayList;
import java.util.List;

/*
 * The prime 41, can be written as the sum of six consecutive primes:
 *
 * 41 = 2 + 3 + 5 + 7 + 11 + 13
 * This is the longest sum of consecutive primes that adds to a prime below one-hundred.
 *
 * The longest sum of consecutive primes below one-thousand that adds to a prime, contains 21 terms, and
 * is equal to 953.
 *
 * Which prime, below one-million, can be written as the sum of the most consecutive primes?
 */
public class Solution050 extends AbstractSolution {
    @Override
    public String run() throws Exception {

        int max = 1000000;

        List<Integer> primes = new ArrayList<>();
        for (int p = Primes.nextPrime(1); p < 10000; p = Primes.nextPrime(p+1))  {
            primes.add(p);
        }

        int maxLength = 0;
        int maxPrime = 0;

        for (int i = 0; i < primes.size(); i++) {
            int currentPrime = primes.get(i);
            int currentLength = 1;
            for (int j = i+1; j < primes.size(); j++) {
                currentPrime += primes.get(j);
                currentLength++;
                if (currentPrime > max) {
                    break;
                }
                if (Primes.isPrime(currentPrime)) {
                    if (currentLength > maxLength) {
                        maxLength = currentLength;
                        maxPrime = currentPrime;
                    }
                }
            }
        }

        return maxPrime + "";
    }
}
