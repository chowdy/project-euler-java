package lib;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class PEUtils {

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

    public static BigInteger factorial(BigInteger n) {
        BigInteger answer = BigInteger.ONE;
        while (n.compareTo(BigInteger.ZERO) > 0) {
            answer = answer.multiply(n);
            n = n.subtract(BigInteger.ONE);
        }
        return answer;
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
