package hackerrank.algorithms.warmup.sherlockandgcd;

import java.util.Scanner;

/**
 * Sherlock is stuck. He has an array A1,A2,...,AN. He wants to know if there
 * exists a subset, B={Ai1,Ai2,...,Aik} where 1<=i1<i2<...<ik<=N, of this array
 * which follows the property
 * 
 * B is non-empty subset. There exists no integer x(x>1) which divides all
 * elements of B. Note that x may or may not be an element of A.
 * 
 * Input Format First line contains T, the number of testcases. Each testcase
 * consists of N in one line. The next line contains N integers denoting the
 * array A.
 * 
 * Output Print YES or NO, if there exists any such subset or not, respectively.
 * 
 * Constraints 1<=T<=10 1<=N<=100 1<=Ai<=105 ∀1<=i<=N
 * 
 * @author Marco Lackovic <marco.lackovic@gmail.com>
 * @version 1.0, 26/sep/2014
 *
 */
public class Solution {

    public static void main(String[] args) {

        try (Scanner in = new Scanner(System.in)) {
            int numTestCases = in.nextInt();
            for (int i = 0; i < numTestCases; i++) {
                int arraySize = in.nextInt();
                int[] array = new int[arraySize];
                boolean yes = false;
                for (int j = 0; j < array.length; j++) {
                    array[j] = in.nextInt();
                    if (array[j] <= 1) {
                        yes = true;
                        for (int k = j + 1; k < array.length; k++) {
                            in.nextInt();
                        }
                        break;
                    }
                }
                if (yes || subsetExists(array)) {
                    System.out.println("YES");
                } else {
                    System.out.println("NO");
                }
            }
        }
    }

    private static boolean subsetExists(int array[]) {
        for (int start = 0; start < array.length; start++) {
            for (int end = array.length - 1; end >= start; end--) {
                if (gcd(array, start, end) == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int gcd(int[] input, int start, int end) {
        int result = input[start];
        for (int i = start + 1; i <= end; i++) {
            result = gcd(result, input[i]);
        }
        return result;
    }

    private static int gcd(int a, int b) {
        while (b > 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

}
