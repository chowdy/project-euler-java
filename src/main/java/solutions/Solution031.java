package solutions;

import lib.AbstractSolution;

/*
 * In England the currency is made up of pound, £, and pence, p, and there are eight coins in general
 * circulation:
 *
 * 1p, 2p, 5p, 10p, 20p, 50p, £1 (100p) and £2 (200p).
 * It is possible to make £2 in the following way:
 *
 * 1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
 * How many different ways can £2 be made using any number of coins?
 */
public class Solution031 extends AbstractSolution {
    @Override
    public String run() throws Exception {
        int answer = 0;
        for (int p200 = 0; p200 <= 1; p200++) {
            for (int p100 = 0; p100 <= 2; p100++) {
                for (int p50 = 0; p50 <= 4; p50++) {
                    for (int p20 = 0; p20 <= 10; p20++) {
                        for (int p10 = 0; p10 <= 20; p10++) {
                            for (int p5 = 0; p5 <= 200 / 5; p5++) {
                                for (int p2 = 0; p2 <= 100; p2++) {
                                    for (int p = 0; p <= 200; p++) {
                                        if (p + 2 * p2 + 5 * p5 + 10 * p10 + 20 * p20 + 50 * p50 + 100 * p100 + 200 * p200 == 200) {
                                            answer++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return answer + "";
    }
}
