package solutions;

import lib.AbstractSolution;
import org.apache.commons.math3.primes.Primes;

/*
 * The number 3797 has an interesting property. Being prime itself, it is possible to continuously
 * remove digits from left to right, and remain prime at each stage: 3797, 797, 97, and 7.
 * Similarly we can work from right to left: 3797, 379, 37, and 3.
 *
 * Find the sum of the only eleven primes that are both truncatable from left to right and right to left.
 *
 * NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.
 */
public class Solution037 extends AbstractSolution {
    @Override
    public String run() throws Exception {

        int i = Primes.nextPrime(8);
        int seen = 0;
        int answer = 0;

        while(seen < 11) {
            String iStr  = String.valueOf(i);
            boolean isTruncatable = true;

            for (int j = 0; j < iStr.length(); j++) {
                if (!Primes.isPrime(Integer.valueOf(iStr.substring(j))) ||
                    !Primes.isPrime(Integer.valueOf(iStr.substring(0,iStr.length()-j)))) {
                    isTruncatable = false;
                    break;
                }
            }

            if (isTruncatable) {
                System.out.println(i);
                answer += i;
                seen += 1;
            }

            i = Primes.nextPrime(i+1);
        }

        return answer + "";
    }
}
