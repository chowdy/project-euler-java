package solutions;

import lib.AbstractSolution;

import java.util.HashMap;

/*
 * If the numbers 1 to 5 are written out in words: one, two, three, four, five, then there are
 * 3 + 3 + 5 + 4 + 4 = 19 letters used in total.
 *
 * If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many
 * letters would be used?
 *
 * NOTE: Do not count spaces or hyphens.
 * For example, 342 (three hundred and forty-two) contains 23 letters and 115 (one hundred and fifteen)
 * contains 20 letters. The use of "and" when writing out numbers is in compliance with British usage.
 */
public class Solution017 extends AbstractSolution {

    static HashMap<Integer,String> numberWordsMap = new HashMap<>();
    {
        numberWordsMap.put(1,"one");
        numberWordsMap.put(2,"two");
        numberWordsMap.put(3,"three");
        numberWordsMap.put(4,"four");
        numberWordsMap.put(5,"five");
        numberWordsMap.put(6,"six");
        numberWordsMap.put(7,"seven");
        numberWordsMap.put(8,"eight");
        numberWordsMap.put(9,"nine");
        numberWordsMap.put(10,"ten");
        numberWordsMap.put(11,"eleven");
        numberWordsMap.put(12,"twelve");
        numberWordsMap.put(13,"thirteen");
        numberWordsMap.put(14,"fourteen");
        numberWordsMap.put(15,"fifteen");
        numberWordsMap.put(16,"sixteen");
        numberWordsMap.put(17,"seventeen");
        numberWordsMap.put(18,"eighteen");
        numberWordsMap.put(19,"nineteen");
        numberWordsMap.put(20,"twenty");
        numberWordsMap.put(30,"thirty");
        numberWordsMap.put(40,"forty");
        numberWordsMap.put(50,"fifty");
        numberWordsMap.put(60,"sixty");
        numberWordsMap.put(70,"seventy");
        numberWordsMap.put(80,"eighty");
        numberWordsMap.put(90,"ninety");
        numberWordsMap.put(100,"hundred");
        numberWordsMap.put(1000,"thousand");
    }

    @Override
    public String run() {
        long sum = 0;
        for (int i = 1; i <= 1000; i++) {
            String word = numberToWord(i);
            System.out.println(word);
            sum += word.length();
        }

        System.out.println(numberToWord(913));

        return "" + sum;
    }

    private String numberToWord(int number) {

        if (number < 100 && numberWordsMap.containsKey(number)) {
            return numberWordsMap.get(number);
        }

        String word = "";

        if (number >= 1000) {
            int thousands = number / 1000;
            word += numberWordsMap.get(thousands);
            word += numberWordsMap.get(1000);
            number -= thousands * 1000;
        }

        if (number >= 100) {
            int hundreds = number / 100;
            word += numberWordsMap.get(hundreds);
            word += numberWordsMap.get(100);
            number -= hundreds * 100;

            if (number > 0) {
                word += "and";
            } else {
                return word;
            }
        }

        if (number >= 10 && number <= 20) {
            word += numberWordsMap.get(number);
            number -= number;
        }

        if (number > 20) {
            int tens = number / 10;
            word += numberWordsMap.get(tens * 10);
            number -= tens * 10;
        }

        if (number > 0) {
            word += numberWordsMap.get(number);
        }

        return word;
    }
}
