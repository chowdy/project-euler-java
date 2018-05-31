package lib;

import com.google.common.math.BigIntegerMath;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class PEUtils {

    static int[] digits1to9 = new int[] { 1,2,3,4,5,6,7,8,9 };
    static int sumOfDigits = 1+2+3+4+5+6+7+8+9;
    static int productOfDigits = 1*2*3*4*5*6*7*8*9;

    public static boolean isPandigital(String num) {
        IntStream digits = num.chars().sorted();

        if (num.length() != 9 || num.contains("0") || digits.sum() != sumOfDigits ||
                digits.reduce(1, (a,b) -> a * b) != productOfDigits) {
            return false;
        }

        return true;

        //return Arrays.equals(digits.sorted().toArray(), allDigits);
    }

    //public static boolean isPandigital(String num) {
    //    char[] numCharsSorted = num.toCharArray();
    //    Arrays.sort(numCharsSorted);
    //    return Arrays.equals(allDigits, numCharsSorted);
    //}

    /*
    public static boolean isPandigital(String num) {
        if (num.length() != 9) {
            return false;
        } else if (num.contains("0")) {
            return false;
        }

        boolean[] digits = new boolean[9];

        for (char d : num.toCharArray()) {
            if (digits[d - 48 - 1] == true) {
                return false;
            } else {
                digits[d - 48 - 1] = true;
            }
        }
        for (boolean d : digits) {
            if (!d) {
                return false;
            }
        }
        return true;
    }
    */

    public static Iterator<BigInteger> fib = new Iterator<BigInteger>() {

        BigInteger fn_1 = BigInteger.ONE;
        BigInteger fn_2 = BigInteger.ONE;

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public BigInteger next() {
            BigInteger fn = fn_1.add(fn_2);
            fn_2 = fn_1;
            fn_1 = fn;
            return fn;
        }

        @Override
        public void remove() {}

        @Override
        public void forEachRemaining(Consumer<? super BigInteger> action) {}
    };

    // From wikipedia:
    // x is pentagonal if...
    // (sqrt(24*x + 1) + 1) / 6
    // ... is a natural number
    static BigInteger B24 = BigInteger.valueOf(24);
    static BigInteger B6  = BigInteger.valueOf(6);
    public static boolean isPentagonal(long x) {
        try {
            BigInteger X = BigInteger.valueOf(x);

            BigInteger buf = B24.multiply(X);
            buf = buf.add(BigInteger.ONE);
            buf = BigIntegerMath.sqrt(buf, RoundingMode.UNNECESSARY);
            buf = buf.add(BigInteger.ONE);

            if ((buf.mod(B6)).compareTo(BigInteger.ZERO) == 0) {
                return true;
            } else {
                return false;
            }
        } catch (ArithmeticException e) {
            return false;
        }
    }

    public static HashMap<Long,Long> getPentagonalCache = new HashMap<>();
    public static long getPentagonal(long n) {
        return getPentagonalCache.computeIfAbsent(n, (m) -> (m * (3 * m - 1)) / 2);
    }

    public static boolean isDeficient(long n) {
        return sumOfDivisors(n) < n;
    }

    public static boolean isAbundant(long n) {
        return sumOfDivisors(n) > n;
    }

    public static boolean isPerfect(long n) {
        return n == sumOfDivisors(n);
    }

    static Map<Long,Long> sumOfDivisorsCache = new ConcurrentHashMap<>();
    public static Long sumOfDivisors(long n) {
        return sumOfDivisorsCache.computeIfAbsent(n, (m) -> {
            return divisors(m).stream().reduce(0L, (a,b) -> a + b);
        });
    }

    static Map<Long,List<Long>> divisorsCache = new ConcurrentHashMap<>();
    public static List<Long> divisors(long n) {
        return divisorsCache.computeIfAbsent(n,(m) -> {
            List<Long> divs = new ArrayList<>();
            divs.add(1L);
            for (long i = 2; i < m; i++) {
                if (m % i == 0) {
                    divs.add(i);
                }
            }
            return divs;
        });
    }

    static HashMap<BigInteger,BigInteger> factorialsCache = new HashMap<>();
    public static BigInteger factorial(BigInteger n) {
        return factorialsCache.computeIfAbsent(n, (m) -> {
            BigInteger answer = BigInteger.ONE;
            while (m.compareTo(BigInteger.ZERO) > 0) {
                answer = answer.multiply(m);
                m = m.subtract(BigInteger.ONE);
            }
            return answer;
        });
    }

    static HashMap<Long,Long> longFactorialsCache = new HashMap<>();
    public static long factorial(long n) {
        return longFactorialsCache.computeIfAbsent(n, (m) -> {
            long f = 1;
            for (long i = m; i > 0; i--) {
                f *= i;
            }
            return f;
        });
    }

    public enum Directions {
        UP(-1,0),
        DOWN(1,0),
        LEFT(0,-1),
        RIGHT(0,1),
        UP_LEFT(-1,-1),
        UP_RIGHT(-1,1),
        DOWN_LEFT(1,-1),
        DOWN_RIGHT(1,1);

        public final int i, j;

        Directions(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    public static class Node<T> {
        List<Node> nextNodes = new ArrayList<>();
        List<Node> prevNodes = new ArrayList<>();
        public T value;

        public Node() {}

        public Node(T val) {
            this.value = val;
        }

        public List<Node> getNextNodes() {
            return nextNodes;
        }

        public List<Node> getPrevNodes() {
            return prevNodes;
        }
        
        public void addNextNode(Node nextNode) {
            nextNodes.add(nextNode);
            nextNode.addPrevNode(this);
        }

        private void addPrevNode(Node prevNode) {
            prevNodes.add(prevNode);
        }

        public String toString() {
            return value.toString();
        }
    }

    public static <T> List<T> getAdjacentValues(T[][] array, int i, int j) {
        List<T> adjacents = new ArrayList<>();

        // Up
        try {
            adjacents.add(array[i-1][j]);
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {}

        // Down
        try {
            adjacents.add(array[i+1][j]);
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {}

        // Left
        try {
            adjacents.add(array[i][j-1]);
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {}

        // Right
        try {
            adjacents.add(array[i][j+1]);
        } catch (java.lang.ArrayIndexOutOfBoundsException e) {}

        // Up-Left
        // Up-Right
        // Down-Left
        // Down-Right

        return adjacents;
    }

    public static Integer[][] numbersArrayTo2dDigitArray(String[] numbersArray) {
        Integer[][] digitArray = new Integer[numbersArray.length][numbersArray[0].length()];
        for (int i = 0; i < numbersArray.length; i++) {
            for (int j = 0; j < numbersArray[i].length(); j++) {
                // Create new instance of integer so that we can use == to determine two Integers
                // with the same value are different instances.
                //
                // It looks like primitives all share the same instance if they share the same value.
                digitArray[i][j] = new Integer(Character.getNumericValue(numbersArray[i].charAt(j)));
            }
        }
        return digitArray;
    }

    public static boolean isPallindrome(Object o) {
        String str = o.toString();
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    // The prime factors of 13195 are 5, 7, 13 and 29.
    public static List<Long> primeFactors(long num) {
        List<Long> primeFactors = new ArrayList<>();
        for (long i = 2; i <= num; i++) {
            if (num % i == 0) {
                primeFactors.add(i);
                num /= i;
            }
        }
        return primeFactors;
    }

    // 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
    public static List<Long> getFibonacci(long count) {
        List<Long> fibs = new ArrayList<>();
        long f = 1;
        long f_1 = 0;

        for (long i = 0; i < count; i++) {
            long f_next = f + f_1;
            fibs.add(f_next);
            f_1 = f;
            f = f_next;
        }

        return fibs;
    }

    public static List<Long> getFibonacciMax(long max) {
        List<Long> fibs = new ArrayList<>();
        long f = 1;
        long f_1 = 0;

        while (f + f_1 < max) {
            long f_next = f + f_1;
            fibs.add(f_next);
            f_1 = f;
            f = f_next;
        }

        return fibs;
    }
}
