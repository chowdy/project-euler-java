package solutions;

import com.google.common.collect.Collections2;
import lib.AbstractSolution;
import lib.PEUtils.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
 * The number, 1406357289, is a 0 to 9 pandigital number because it is made up of each of the digits 0 to 9
 * in some order, but it also has a rather interesting sub-string divisibility property.
 *
 * Let d1 be the 1st digit, d2 be the 2nd digit, and so on. In this way, we note the following:
 *
 * d2d3d4=406 is divisible by 2
 * d3d4d5=063 is divisible by 3
 * d4d5d6=635 is divisible by 5
 * d5d6d7=357 is divisible by 7
 * d6d7d8=572 is divisible by 11
 * d7d8d9=728 is divisible by 13
 * d8d9d10=289 is divisible by 17
 *
 * Find the sum of all 0 to 9 pandigital numbers with this property.
 */
public class Solution043 extends AbstractSolution {
    @Override
    public String run() throws Exception {

        long answer = 0;

        List<List<String>> allPandigitals =
                Collections2.permutations(Arrays.asList("0123456789".split(""))).stream()
                        .collect(Collectors.toList());

        for (List<String> pandigital : allPandigitals) {
            String pan = pandigital.stream().collect(Collectors.joining());

            if (Integer.parseInt(pan.substring(1,4))  % 2  == 0 &&
                Integer.parseInt(pan.substring(2,5))  % 3  == 0 &&
                Integer.parseInt(pan.substring(3,6))  % 5  == 0 &&
                Integer.parseInt(pan.substring(4,7))  % 7  == 0 &&
                Integer.parseInt(pan.substring(5,8))  % 11 == 0 &&
                Integer.parseInt(pan.substring(6,9))  % 13 == 0 &&
                Integer.parseInt(pan.substring(7,10)) % 17 == 0)
            {
                answer += Long.parseLong(pan);
            }
        }

        return answer + "";
    }
}
