package projecteuler.krige.problem001;

import java.util.Arrays;

/**
 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we
 * get 3, 5, 6 and 9. The sum of these multiples is 23.
 * 
 * Find the sum of all the multiples of a given set of factors below 1000.
 * 
 * @author Marco Lackovic <marco.lackovic@gmail.com>
 * @version 1.0, 02/may/2013
 */
public class MultiplesOfNi {

    private static final int UPPER_BOUND = 1000;
    private static int[] factors = { 3, 5, 7, 8 };

    public static void main(String[] args) {
        addCommonMultipliers();
        int upperBound = UPPER_BOUND - 1;
        int result = 0;
        for (int i = 0; i < factors.length; i++) {
            int limit = Math.abs(upperBound / factors[i]);
            result += factors[i] * triangularNumber(limit);
        }
        System.out.println(result);
    }

    private static void addCommonMultipliers() {
        int[] newFactors = new int[kCombinations4allK(factors.length) - 1];
        for (int i = 0; i < factors.length; i++) {
            newFactors[i] = factors[i];
        }
        int k = factors.length;
        int a = 0;
        for (int i = 1; i < factors.length; i++) {
            int aLowerLimit = k;
            int aUpperLimit = a + factors.length - i;
            int bLowerLimit = i;
            for (; a < aUpperLimit; a++) {
                for (int b = bLowerLimit; b < factors.length; b++) {
                    newFactors[k++] = -Math.abs(newFactors[a]) * newFactors[b];
                }
                bLowerLimit++;
            }
            a = aLowerLimit;
        }
        System.out.println(Arrays.toString(newFactors));
        factors = newFactors;
    }

    private static int kCombinations4allK(int n) {
        return (int) Math.pow(2, n);
    }

    private static int triangularNumber(int n) {
        return n * (n + 1) / 2;
    }
}
