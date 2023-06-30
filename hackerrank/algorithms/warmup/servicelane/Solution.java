package hackerrank.algorithms.warmup.servicelane;

import java.util.Scanner;

/**
 * @author Marco Lackovic <marco.lackovic@gmail.com>
 * @version 1.0, 26/jul/2014
 *
 */
public class Solution {
    private static int[] serviceLaneWidth;

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int freewayLength = in.nextInt();
            int numTestCases = in.nextInt();

            serviceLaneWidth = new int[freewayLength];
            for (int i = 0; i < serviceLaneWidth.length; i++) {
                serviceLaneWidth[i] = in.nextInt();
            }

            for (int i = 0; i < numTestCases; i++) {
                System.out.println(computeLargestVehicle(in.nextInt(),
                        in.nextInt()));
            }
        }
    }

    private static int computeLargestVehicle(int entrySegmentId,
            int exitSegmentId) {
        int largestVehicle = serviceLaneWidth[entrySegmentId];
        for (int i = entrySegmentId + 1; i <= exitSegmentId; i++) {
            if (serviceLaneWidth[i] < largestVehicle) {
                largestVehicle = serviceLaneWidth[i];
            }
        }
        return largestVehicle;
    }
}
