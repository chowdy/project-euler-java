package solutions;

import lib.AbstractSolution;
import org.apache.commons.math3.primes.Primes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * The first two consecutive numbers to have two distinct prime factors are:
 *
 * 14 = 2 × 7
 * 15 = 3 × 5
 *
 * The first three consecutive numbers to have three distinct prime factors are:
 *
 * 644 = 2² × 7 × 23
 * 645 = 3 × 5 × 43
 * 646 = 2 × 17 × 19.
 *
 * Find the first four consecutive integers to have four distinct prime factors each.
 * What is the first of these numbers?
 */
public class Solution047 extends AbstractSolution {
    @Override
    public String run() throws Exception {

        int num = 4;

        for (int i = 2; true; i++) {
            System.out.println(i);

            boolean works = true;
            for (int j = 0; j < num; j++) {
                if (new HashSet<>(Primes.primeFactors(i+j)).size() != num) {
                    works = false;
                    break;
                }
            }

            if (works) {
                return i + "";
            }
        }
    }
}
