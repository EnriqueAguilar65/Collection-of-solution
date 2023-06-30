package hackerrank.algorithms.warmup.halloween;

import java.util.Scanner;

/**
 * Alex is attending a Halloween party with his girlfriend Silvia. At the party,
 * Silvia spots a giant chocolate bar. If the chocolate can be served as only 1
 * x 1 sized pieces and Alex can cut the chocolate bar exactly K times, what is
 * the maximum number of chocolate pieces Alex can cut and give Silvia?
 * 
 * Input Format The first line contains an integer T, the number of test cases.
 * T lines follow. Each line contains an integer K
 * 
 * Output Format T lines. Each line contains an integer that denotes the maximum
 * number of pieces that can be obtained for each test case.
 * 
 * Constraints 1<=T<=10 2<=K<=107
 * 
 * Note Chocolate must be served in size of 1 x 1 size pieces. Alex can't
 * relocate any of the pieces, nor can he place any piece on top of other.
 * 
 * @author Marco Lackovic <marco.lackovic@gmail.com>
 * @version 1.0, 3/sep/2014
 *
 */
public class Solution {

    public static void main(String[] args) {

        // BufferedInputStream file = new BufferedInputStream(new
        // FileInputStream(
        // "dat/hackerrank/halloween.in"));
        // System.setIn(file);

        try (Scanner in = new Scanner(System.in)) {
            int numWords = in.nextInt();
            for (int i = 0; i < numWords; i++) {
                System.out.println(findMinNumOperations(in.nextLong()));
            }

        }
    }

    private static long findMinNumOperations(long numCuts) {
        return numCuts * numCuts / 4;
    }

}
