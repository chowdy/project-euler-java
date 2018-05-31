package solutions;

import com.google.common.collect.Collections2;
import lib.AbstractSolution;
import org.apache.commons.math3.primes.Primes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/*
 * The number, 197, is called a circular prime because all rotations of the digits: 197, 971, and 719,
 * are themselves prime.
 *
 * There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, and 97.
 *
 * How many circular primes are there below one million?
 */
public class Solution035 extends AbstractSolution {
    @Override
    public String run() throws Exception {

        int answer = 0;
        int i = 1;
        while ((i = Primes.nextPrime(i)) < 1000000) {

            List<Long> primeAsLongList = String.valueOf(i).chars()
                    .mapToLong((c) -> Long.valueOf(c) - 48l)
                    .boxed()
                    .collect(Collectors.toList());

            List<List<Long>> rotations = new ArrayList<>();
            for (int r = 0; r < primeAsLongList.size(); r++) {
                Collections.rotate(primeAsLongList, 1);
                rotations.add(new ArrayList<>(primeAsLongList));
            }

            boolean isPrime = true;

            // For each permutation, check and see if it's prime
            for (List<Long> rotation : rotations) {

                int prime = Integer.valueOf(rotation.stream()
                        .map((l) -> String.valueOf(l))
                        .reduce("", (a,b) -> a + b));

                // Turn the permutation into an int and check it
                if (!Primes.isPrime(prime)) {
                    isPrime = false;
                    break;
                }
            }

            if (isPrime) {
                answer++;
            }

            i++;
        }

        return answer + "";
    }
}
