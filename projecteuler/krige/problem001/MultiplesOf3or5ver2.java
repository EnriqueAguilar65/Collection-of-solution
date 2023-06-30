package projecteuler.krige.problem001;

import java.util.Scanner;

/**
 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we
 * get 3, 5, 6 and 9. The sum of these multiples is 23.
 * 
 * Find the sum of all the multiples of 3 or 5 below N.
 * 
 * Input Format: First line contains T that denotes the number of test cases.
 * This is followed by T lines, each containing an integer, N.
 * 
 * Output Format: For each test case, print an integer that denotes the sum of
 * all the multiples of 3 or 5 below N.
 * 
 * Constraints: 1<=T<=105 1<=N<=109
 * 
 * @author Marco Lackovic <marco.lackovic@gmail.com>
 * @version 2.0, 2/sep/2014
 *
 */
public class Solution {

    private static final int[] FACTORS = { 3, 5 };
    private static final int lcm = lcm(FACTORS[0], FACTORS[1]);

    private static int lcm(int a, int b) {
        return a * (b / gcd(a, b));
    }

    private static int gcd(int a, int b) {
        while (b > 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static void main(String[] args) {
        // try {
        // BufferedInputStream file = new BufferedInputStream(
        // new FileInputStream("dat/hackerrank/multiplesof3and5.in"));
        // System.setIn(file);
        // } catch (FileNotFoundException e) {
        // }

        try (Scanner in = new Scanner(System.in)) {
            int numTestCases = in.nextInt();
            for (int i = 0; i < numTestCases; i++) {
                System.out.println(computeSumMultiplesOfTwoFactors(in
                        .nextLong() - 1));
            }

        }
    }

    public static long computeSumMultiplesOfTwoFactors(long n) {
        long result = 0;
        for (int i = 0; i < FACTORS.length; i++) {
            result += computeArithmeticSeries(FACTORS[i], n);
        }
        result -= computeArithmeticSeries(lcm, n);
        return result;
    }

    private static long computeArithmeticSeries(long a1, long n) {
        long quantity = n / a1;
        long an = quantity * a1;
        return quantity * (a1 + an) / 2;
    }
}
