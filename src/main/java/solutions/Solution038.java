package solutions;

import com.google.common.collect.Collections2;
import lib.AbstractSolution;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Take the number 192 and multiply it by each of 1, 2, and 3:
 *
 * 192 × 1 = 192
 * 192 × 2 = 384
 * 192 × 3 = 576
 * By concatenating each product we get the 1 to 9 pandigital, 192384576. We will call 192384576 the
 * concatenated product of 192 and (1,2,3)
 *
 * The same can be achieved by starting with 9 and multiplying by 1, 2, 3, 4, and 5, giving the pandigital,
 * 918273645, which is the concatenated product of 9 and (1,2,3,4,5).
 *
 * What is the largest 1 to 9 pandigital 9-digit number that can be formed as the concatenated product of
 * an integer with (1,2, ... , n) where n > 1?
 */
// TODO: Make this less hideous
public class Solution038 extends AbstractSolution {
    @Override
    public String run() throws Exception {

        int max = 0;

        // The largest pandigital must at least start with a 9 given the examples above
        List<List<String>> allPandigitals =
                Collections2.permutations(Arrays.asList("123456789".split(""))).stream()
                        .filter((l) -> l.get(0).equals("9"))
                        .collect(Collectors.toList());

        for (List<String> pandigital : allPandigitals) {
            String pan = pandigital.stream().collect(Collectors.joining());
            int panInt = Integer.valueOf(pan);

            if (panInt < max) {
                continue;
            }

            // 1, 2, 2, 2, 2
            boolean isGood = true;
            int[] vals = new int[] {
                    Integer.valueOf(pan.substring(0, 1)),
                    Integer.valueOf(pan.substring(1, 3)),
                    Integer.valueOf(pan.substring(3, 5)),
                    Integer.valueOf(pan.substring(5, 7)),
                    Integer.valueOf(pan.substring(7, 9)),
            };
            for (int i = 0; i < vals.length - 1; i++) {
                int n = vals[i];
                int n_1 = vals[i+1];
                if (n != vals[0] * (i + 1) || n_1 != vals[0] * (i + 2)) {
                    isGood = false;
                    break;
                }
            }
            if (isGood && panInt > max) {
                System.out.println(Arrays.toString(vals));
                max = panInt;
                continue;
            }

            // 2, 3, 4
            isGood = true;
            vals = new int[] {
                    Integer.valueOf(pan.substring(0, 2)),
                    Integer.valueOf(pan.substring(2, 5)),
                    Integer.valueOf(pan.substring(5, 9)),
            };
            for (int i = 0; i < vals.length - 1; i++) {
                int n = vals[i];
                int n_1 = vals[i+1];
                if (n != vals[0] * (i + 1) || n_1 != vals[0] * (i + 2)) {
                    isGood = false;
                    break;
                }
            }
            if (isGood && panInt > max) {
                System.out.println(Arrays.toString(vals));
                max = panInt;
                continue;
            }

            // 3, 3, 3
            isGood = true;
            vals = new int[] {
                    Integer.valueOf(pan.substring(0,3)),
                    Integer.valueOf(pan.substring(3,6)),
                    Integer.valueOf(pan.substring(6,9)),
            };
            for (int i = 0; i < vals.length - 1; i++) {
                int n = vals[i];
                int n_1 = vals[i+1];
                if (n != vals[0] * (i + 1) || n_1 != vals[0] * (i + 2)) {
                    isGood = false;
                    break;
                }
            }
            if (isGood && panInt > max) {
                System.out.println(Arrays.toString(vals));
                max = panInt;
                continue;
            }

            // 4, 5
            isGood = true;
            vals = new int[]{
                    Integer.valueOf(pan.substring(0, 4)),
                    Integer.valueOf(pan.substring(4, 9)),
            };
            for (int i = 0; i < vals.length - 1; i++) {
                int n = vals[i];
                int n_1 = vals[i+1];
                if (n != vals[0] * (i + 1) || n_1 != vals[0] * (i + 2)) {
                    isGood = false;
                    break;
                }
            }
            if (isGood && panInt > max) {
                System.out.println(Arrays.toString(vals));
                max = panInt;
                continue;
            }

        }

        return max + "";
    }
}
