package hackerrank.algorithms.warmup.gameofthrones1;

import java.util.Scanner;

/**
 * King Robert has 7 kingdoms under his rule. He finds out from a raven that the
 * Dothraki are soon going to wage a war against him. But, he knows the Dothraki
 * need to cross the narrow river to enter his dynasty. There is only one bridge
 * that connects both sides of the river which is sealed by a huge door.
 * 
 * The king wants to lock the door so that the Dothraki can't enter. But, to
 * lock the door he needs a key that is an anagram of a certain palindrome
 * string.
 * 
 * The king has a string composed of lowercase English letters. Help him figure
 * out if any anagram of the string can be a palindrome or not.
 * 
 * Input Format A single line which contains the input string
 * 
 * Constraints 1<=length of string <= 10^5 Each character of the string is a
 * lowercase English letter.
 * 
 * Output Format A single line which contains YES or NO in uppercase.
 * 
 * @author Marco Lackovic <marco.lackovic@gmail.com>
 * @version 1.0, 23/sep/2014
 *
 */
public class Solution {

    private static final int NUM_LETTERS_ENGLISH_ALPHABET = 26;

    public static void main(String[] args) {

        // try {
        // BufferedInputStream file = new BufferedInputStream(
        // new FileInputStream("dat/hackerrank/gameofthrones1.in"));
        // System.setIn(file);
        // } catch (FileNotFoundException e) {
        // e.printStackTrace();
        // }

        try (Scanner in = new Scanner(System.in)) {
            if (canBePalindrome(in.nextLine())) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }

    private static boolean canBePalindrome(String inputString) {
        char[] anagramArray = inputString.toCharArray();
        int[] alphabet = new int[NUM_LETTERS_ENGLISH_ALPHABET];
        int aIndex = Character.getNumericValue('a');
        for (int i = 0; i < anagramArray.length; i++) {
            int charIndex = Character.getNumericValue(anagramArray[i]) - aIndex;
            if (alphabet[charIndex] > 0) {
                alphabet[charIndex]--;
            } else {
                alphabet[charIndex]++;
            }
        }
        if (isEven(anagramArray.length)) {
            return computeSum(alphabet) == 0;
        }
        return computeSum(alphabet) == 1;
    }

    public static boolean isEven(int n) {
        return (n & 1) == 0;
    }

    private static int computeSum(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum;
    }
}
