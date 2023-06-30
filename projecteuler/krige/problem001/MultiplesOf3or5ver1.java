package projecteuler.krige.problem001;

/**
 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we
 * get 3, 5, 6 and 9. The sum of these multiples is 23.
 * 
 * Find the sum of all the multiples of 3 or 5 below 1000.
 * 
 * @author Marco Lackovic <marco.lackovic@gmail.com>
 * @version 1.0, 01/may/2013
 */
public class MultiplesOf3or5 {

    private static final int UPPER_BOUND = 1000;
    private static final int[] FACTORS = { 3, 5, -15 };

    public static void main(String[] args) {
        int upperBound = UPPER_BOUND - 1;
        int result = 0;
        for (int i = 0; i < FACTORS.length; i++) {
            int limit = Math.abs(upperBound / FACTORS[i]);
            result += FACTORS[i] * triangularNumber(limit);
        }
        System.out.println(result);
    }

    private static int triangularNumber(int n) {
        return n * (n + 1) / 2;
    }
}
