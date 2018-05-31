package solutions;

import lib.AbstractSolution;

/*
 * The fraction 49/98 is a curious fraction, as an inexperienced mathematician in attempting to simplify it
 * may incorrectly believe that 49/98 = 4/8, which is correct, is obtained by cancelling the 9s.
 *
 * We shall consider fractions like, 30/50 = 3/5, to be trivial examples.
 *
 * There are exactly four non-trivial examples of this type of fraction, less than one in value, and
 * containing two digits in the numerator and denominator.
 *
 * If the product of these four fractions is given in its lowest common terms, find the value of the
 * denominator.
 */
public class Solution033 extends AbstractSolution {
    @Override
    public String run() throws Exception {
        int answerNumer = 1;
        int answerDenom = 1;
        for (int i = 10; i <= 99; i++) {
            for (int j = 10; j < i; j++) {
                float divided = ((float) j) / i;
                String strI = String.valueOf(i);
                String strJ = String.valueOf(j);

                // If it's a "trivial" example, continue
                if (strI.charAt(1) == '0' && strJ.charAt(1) == '0') {
                    continue;
                }

                String j0 = strJ.substring(0,1);
                String j1 = strJ.substring(1,2);

                int numerCancelJ0 = Integer.valueOf(strJ.replaceFirst(j0,""));
                int denomCancelJ0 = Integer.valueOf(strI.replaceFirst(j0,""));
                int numerCancelJ1 = Integer.valueOf(strJ.replaceFirst(j1,""));
                int denomCancelJ1 = Integer.valueOf(strI.replaceFirst(j1,""));

                if (((float)numerCancelJ0) / denomCancelJ0 == divided) {
                    answerNumer *= numerCancelJ0;
                    answerDenom *= denomCancelJ0;

                    System.out.println(j + "/" + i + " = " + divided + " = " +
                            numerCancelJ0 + "/" + denomCancelJ0);
                }

                if (((float)numerCancelJ1) / denomCancelJ1 == divided) {
                    answerNumer *= numerCancelJ1;
                    answerDenom *= denomCancelJ1;
                    System.out.println(j + "/" + i + " = " + divided + " = " +
                            numerCancelJ1 + "/" + denomCancelJ1);
                }
            }
        }
        return answerDenom / answerNumer + "";
    }
}
