package solutions;

import lib.AbstractSolution;

import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;


/*
 * Using names.txt (right click and 'Save Link/Target As...'), a 46K text file containing over five-thousand
 * first names, begin by sorting it into alphabetical order. Then working out the alphabetical value for
 * each name, multiply this value by its alphabetical position in the list to obtain a name score.
 *
 * For example, when the list is sorted into alphabetical order, COLIN, which is worth
 * 3 + 15 + 12 + 9 + 14 = 53, is the 938th name in the list.
 *
 * So, COLIN would obtain a score of 938 × 53 = 49714.
 *
 * What is the total of all the name scores in the file?
 */
public class Solution022 extends AbstractSolution {

    ClassLoader classLoader = getClass().getClassLoader();
    File file = new File(classLoader.getResource("Problem022/names.txt").getFile());

    @Override
    public String run() throws Exception {

        BufferedReader br = Files.newBufferedReader(Paths.get(file.getAbsolutePath()));
        String line;
        String file = "";
        while ((line = br.readLine()) != null) {
            file += line;
        }

        AtomicInteger idx = new AtomicInteger(0);
        int sum = Arrays.stream(file.split("[\",]"))
                .filter((s) -> s.length() > 0)
                .sorted(String::compareTo)
                .map((s) -> stringValue(s))
                .map((i) -> i * idx.incrementAndGet())
                .reduce(0, (a,b) -> a + b);

        return String.valueOf(sum);
    }


    static int diff = (int)'C' - 3;
    static int charValue(char c) {
        return c - diff;
    }

    static int stringValue(String s) {

        // Not sure why Arrays.stream(chars) freaks out
        char[] chars = s.toCharArray();
        int sum = 0;

        for (int i = 0; i < chars.length; i++) {
            sum += charValue(chars[i]);
        }

        return sum;
    }
}
