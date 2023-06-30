package hackerrank.algorithms.warmup.cutthesticks;

import java.util.Scanner;

/**
 * You are given N sticks, where each stick is of positive integral length. A
 * cut operation is performed on the sticks such that all of them are reduced by
 * the length of the smallest stick.
 * 
 * Suppose we have 6 sticks of length
 * 
 * 5 4 4 2 2 8
 * 
 * then in one cut operation we make a cut of length 2 from each of the 6
 * sticks. For next cut operation 4 sticks are left (of non-zero length), whose
 * length are
 * 
 * 3 2 2 6
 * 
 * Above step is repeated till no sticks are left.
 * 
 * Given length of N sticks, print the number of sticks that are cut in
 * subsequent cut operations.
 * 
 * Input Format The first line contains a single integer N. The next line
 * contains N integers: a0, a1,...aN-1 separated by space, where ai represents
 * the length of ith stick.
 * 
 * Output Format For each operation, print the number of sticks that are cut in
 * separate line.
 * 
 * Constraints 1 <= N <= 1000 1 <= ai <= 1000
 * 
 * @author Marco Lackovic <marco.lackovic@gmail.com>
 * @version 1.0, 24/sep/2014
 *
 */
public class Solution {

    public static void main(String[] args) {

        // try {
        // BufferedInputStream file = new BufferedInputStream(
        // new FileInputStream("dat/hackerrank/cutthesticks.in"));
        // System.setIn(file);
        // } catch (FileNotFoundException e) {
        // e.printStackTrace();
        // }

        try (Scanner in = new Scanner(System.in)) {
            int[] sticks = new int[in.nextInt()];
            for (int i = 0; i < sticks.length; i++) {
                sticks[i] = in.nextInt();
            }
            while (sticks.length > 0) {
                System.out.println(sticks.length);
                sticks = cutOperation(sticks);
            }
        }
    }

    private static int[] cutOperation(int[] sticks) {
        int smallestLength = findSmallest(sticks);
        int newSize = sticks.length;
        for (int i = 0; i < sticks.length; i++) {
            sticks[i] -= smallestLength;
            if (sticks[i] == 0) {
                newSize--;
            }
        }
        int[] newSticks = new int[newSize];
        for (int i = 0, j = 0; i < sticks.length; i++) {
            if (sticks[i] > 0) {
                newSticks[j++] = sticks[i];
            }
        }
        return newSticks;
    }

    private static int findSmallest(int[] sticks) {
        int min = sticks[0];
        for (int i = 1; i < sticks.length; i++) {
            if (sticks[i] < min) {
                min = sticks[i];
            }
        }
        return min;
    }
}
