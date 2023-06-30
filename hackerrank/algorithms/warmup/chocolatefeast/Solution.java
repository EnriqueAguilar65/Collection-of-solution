package hackerrank.algorithms.warmup.chocolatefeast;

import java.util.Scanner;

/**
 * Little Bob loves chocolates, and goes to a store with $N in his pocket. The
 * price of each chocolate is $C. The store offers a discount: for every M
 * wrappers he gives to the store, he gets one chocolate for free. How many
 * chocolates does Bob get to eat?
 * 
 * Input Format: The first line contains the number of test cases T(<=1000). T
 * lines follow, each of which contains three integers N, C and M
 * 
 * Output Format: Print the total number of chocolates Bob eats.
 * 
 * Constraints: 2<=N<=105 1<=C<=N 2<=M<=N
 * 
 * @author Marco Lackovic <marco.lackovic@gmail.com>
 * @version 1.0, 25/sep/2014
 *
 */
public class Solution {

    public static void main(String[] args) {

        try (Scanner in = new Scanner(System.in)) {
            int numTestCases = in.nextInt();
            for (int i = 0; i < numTestCases; i++) {
                int pocketMoney = in.nextInt();
                int chocolatePrice = in.nextInt();
                int numWrappersPerChocolate = in.nextInt();
                System.out.println(numChocolates(pocketMoney, chocolatePrice,
                        numWrappersPerChocolate));
            }
        }
    }

    private static int numChocolates(int pocketMoney, int chocolatePrice,
            int numWrappersPerChocolate) {
        int numChocolates = pocketMoney / chocolatePrice;
        int numWrappers = numChocolates;
        while (numWrappers >= numWrappersPerChocolate) {
            int newChocolates = numWrappers / numWrappersPerChocolate;
            numChocolates += newChocolates;
            numWrappers = newChocolates + numWrappers % numWrappersPerChocolate;
        }
        return numChocolates;
    }
}
