package solutions;

import lib.AbstractSolution;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static lib.PEUtils.isDeficient;
import static lib.PEUtils.isAbundant;
import static lib.PEUtils.isPerfect;

/*
 * A perfect number is a number for which the sum of its proper divisors is exactly equal to the number.
 * For example, the sum of the proper divisors of 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28
 * is a perfect number.
 *
 * A number n is called deficient if the sum of its proper divisors is less than n and it is called abundant
 * if this sum exceeds n.
 *
 * As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest number that can be written as
 * the sum of two abundant numbers is 24. By mathematical analysis, it can be shown that all integers greater
 * than 28123 can be written as the sum of two abundant numbers. However, this upper limit cannot be reduced
 * any further by analysis even though it is known that the greatest number that cannot be expressed as the
 * sum of two abundant numbers is less than this limit.
 *
 * Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.
 */
public class Solution023 extends AbstractSolution {
    @Override
    public String run() throws Exception {
        List<Long> abundants = new ArrayList<>();
        int max = 28123;

        for (long i = 12; i <= max; i++) {
            if (isAbundant(i)) {
                abundants.add(i);
            }
        }

        Set<Long> allAbundantSums = new HashSet<>();
        abundants.forEach((a) -> {
            abundants.forEach((b) -> {
                allAbundantSums.add(a + b);
            });
        });

        return LongStream.rangeClosed(1,max)
                .filter((n) -> !allAbundantSums.contains(n))
                .sum() + "";

    }
}
