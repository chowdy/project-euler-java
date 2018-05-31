package solutions;

import com.google.common.collect.Collections2;
import lib.AbstractSolution;
import org.apache.commons.math3.primes.Primes;

import java.util.*;
import java.util.stream.Collectors;

/*
 * The arithmetic sequence, 1487, 4817, 8147, in which each of the terms increases by 3330, is unusual in
 * two ways: (i) each of the three terms are prime, and, (ii) each of the 4-digit numbers are permutations
 * of one another.
 *
 * There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes, exhibiting this property,
 * but there is one other 4-digit increasing sequence.
 *
 * What 12-digit number do you form by concatenating the three terms in this sequence?
 */
public class Solution049 extends AbstractSolution {
    @Override
    public String run() throws Exception {

        Set<List<Integer>> sequences = new HashSet<>();

        List<Integer> allFourDigitPrimes = new ArrayList<>();
        for (int p = Primes.nextPrime(1000); p < 10000; p = Primes.nextPrime(p+1)) {
            allFourDigitPrimes.add(p);
        }

        for (int p : allFourDigitPrimes) {
            Collection<List<String>> permutations = Collections2.permutations(Arrays.asList(String.valueOf(p).split("")));
            List<Integer> others = new ArrayList<>();
            for (List<String> permutation : permutations) {
                int pp = Integer.valueOf(permutation.stream().collect(Collectors.joining()));
                if (allFourDigitPrimes.contains(pp) && !others.contains(pp)) {
                    others.add(pp);
                }
            }

            if (others.size() >= 3) {
                others.sort(Integer::compareTo);
                sequences.add(others);
            }
        }


        // At this point, sequences contains all sequences of 4-digit primes that are permutations of one
        // another.
        System.out.println(sequences);

        List<List<Integer>> answers = new ArrayList<>();

        // Need to identify subsequences of the sequences that have fixed increasing value...
        for (List<Integer> sequence : sequences) {
            List<Integer> subsequence = new ArrayList<>();
            for (int i = 0; i < sequence.size(); i++) {
                int start = sequence.get(i);

                for (int j = i+1; j < sequence.size(); j++) {
                    int second = sequence.get(j);
                    int d = second - start;
                    for (int k = j+1; k < sequence.size(); k++) {
                        if (sequence.get(k) - second == d) {
                            subsequence.add(start);
                            subsequence.add(second);
                            subsequence.add(sequence.get(k));
                            answers.add(subsequence);
                        }
                    }
                }
            }
        }


        answers = answers.stream().filter((l) -> l.get(0) != 1487).collect(Collectors.toList());

        return answers.get(0).stream().map((i) -> String.valueOf(i)).collect(Collectors.joining());
    }
}
