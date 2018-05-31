package solutions;

import lib.AbstractSolution;
import org.apache.commons.math3.primes.Primes;

import java.util.*;
import java.util.stream.Collectors;

/*
 * By replacing the 1st digit of the 2-digit number *3, it turns out that six of the nine possible values:
 * 13, 23, 43, 53, 73, and 83, are all prime.
 *
 * By replacing the 3rd and 4th digits of 56**3 with the same digit, this 5-digit number is the first example
 * having seven primes among the ten generated numbers, yielding the family:
 * 56003, 56113, 56333, 56443, 56663, 56773, and 56993.
 *
 * Consequently 56003, being the first member of this family, is the smallest prime with this property.
 *
 * Find the smallest prime which, by replacing part of the number (not necessarily adjacent digits) with the
 * same digit, is part of an eight prime value family.
 */
public class Solution051 extends AbstractSolution {
    @Override
    public String run() throws Exception {


        // Group primes by digit length up to LENGTH
        int MAX_LENGTH = 6;
        Map<Integer,List<String>> primesByLength = new HashMap<>();
        for (int p = Primes.nextPrime(10; p < Math.pow(10, MAX_LENGTH); p = Primes.nextPrime(p+1)) {
            String pString = String.valueOf(p);
            primesByLength.putIfAbsent(pString.length(), new ArrayList<>());
            primesByLength.get(pString.length()).add(pString);
        }

        for (Integer length : primesByLength.keySet()) {
            List<String> primes = primesByLength.get(length);

            for (int i = 0; i < primes.size(); i++) {
                String prime = primes.get(0);

                for (int wildcards = 1; wildcards < primes.size(); wildcards++) {
                    String current = prime;
                    for (int w = wildcards; w < wildcards; w++) {
                        current += "x";
                    }
                }
            }
        }

        return null;
    }
}
