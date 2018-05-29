package solutions;

import com.google.common.collect.Collections2;
import lib.AbstractSolution;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

/*
 * A permutation is an ordered arrangement of objects. For example, 3124 is one possible permutation of
 * the digits 1, 2, 3 and 4. If all of the permutations are listed numerically or alphabetically, we call it
 * lexicographic order.
 *
 * The lexicographic permutations of 0, 1 and 2 are:
 * 012   021   102   120   201   210
 *
 * What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
 */
public class Solution024 extends AbstractSolution {
    @Override
    public String run() throws Exception {
        return Collections2.orderedPermutations(IntStream.rangeClosed(0,9)
                .boxed()
                .collect(Collectors.toList())).stream()
                .collect(Collectors.toList()).get(1000000-1).stream()
                .map((Integer i) -> "" + i)
                .reduce("", (a,b) -> a + b);
    }
}
