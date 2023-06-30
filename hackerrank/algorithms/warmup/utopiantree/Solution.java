package hackerrank.algorithms.warmup.utopiantree;

import java.util.Scanner;

/**
 * @author Marco Lackovic <marco.lackovic@gmail.com>
 * @version 1.0, 26/jul/2014
 *
 */
public class Solution {

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int numTestCases = in.nextInt();
            for (int i = 0; i < numTestCases; i++) {
                System.out.println(computeHeight(in.nextInt()));
            }
        }
    }

    private static int computeHeight(int numCycles) {
        int height = 1;
        for (int j = 0; j < numCycles; j++) {
            if (j % 2 != 0) {
                height += 1;
            } else {
                height *= 2;
            }
        }
        return height;
    }
}
