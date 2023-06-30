package hackerrank.algorithms.warmup.maximizingxor;

import java.util.Scanner;

/**
 * Given two integers: L and R, find the maximal values of A xor B given, L <= A
 * <= B <= R
 * 
 * Input Format The input contains two lines, L is present in the first line. R
 * in the second line.
 * 
 * Constraints 1 <= L <= R <= 103
 * 
 * Output Format The maximal value as mentioned in the problem statement.
 * 
 * @author Marco Lackovic <marco.lackovic@gmail.com>
 * @version 1.0, 22/sep/2014
 *
 */
public class Solution {

    public static void main(String[] args) {

        // try {
        // BufferedInputStream file = new BufferedInputStream(
        // new FileInputStream("dat/hackerrank/maximizingxor.in"));
        // System.setIn(file);
        // } catch (FileNotFoundException e) {
        // e.printStackTrace();
        // }

        try (Scanner in = new Scanner(System.in)) {
            System.out.println(findMaxXor(in.nextInt(), in.nextInt()));
        }
    }

    private static int findMaxXor(int left, int right) {
        int max = 0;
        for (int i = left; i <= right; i++) {
            for (int j = left; j <= right; j++) {
                int xor = i ^ j;
                if (xor > max) {
                    max = xor;
                }
            }
        }
        return max;
    }
}
