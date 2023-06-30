package hackerrank.algorithms.warmup.loveletter;

import java.util.Scanner;

/**
 * James got hold of a love letter that his friend Harry has written for his
 * girlfriend. Being the prankster that James is, he decides to meddle with it.
 * He changes all the words in the letter into palindromes.
 * 
 * While modifying the letters of the word, he follows 2 rules:
 * 
 * (a) He always reduces the value of a letter, e.g. he changes 'd' to 'c', but
 * he does not change 'c' to 'd'. (b) If he has to repeatedly reduce the value
 * of a letter, he can do it until the letter becomes 'a'. Once a letter has
 * been changed to 'a', it can no longer be changed.
 * 
 * Each reduction in the value of any letter is counted as a single operation.
 * Find the minimum number of operations he carries out to convert a given
 * string into a palindrome.
 * 
 * Input Format The first line contains an integer T, i.e., the number of test
 * cases. The next T lines will contain a string each.
 * 
 * Output Format A single line containing the number of minimum operations
 * corresponding to each test case.
 * 
 * Constraints 1 <= T <= 10 1 <= length of string <= 104 All characters are
 * lower cased english letters.
 * 
 * @author Marco Lackovic <marco.lackovic@gmail.com>
 * @version 1.0, 3/sep/2014
 *
 */
public class Solution {

	public static void main(String[] args) {

		// BufferedInputStream file = new BufferedInputStream(new
		// FileInputStream(
		// "dat/hackerrank/loveletter.in"));
		// System.setIn(file);

		try (Scanner in = new Scanner(System.in)) {
			int numWords = in.nextInt();
			in.nextLine();
			for (int i = 0; i < numWords; i++) {
				System.out.println(findMinNumOperations(in.nextLine()));
			}

		}
	}

	private static int findMinNumOperations(String word) {
		char[] palindrome = word.toCharArray();
		int numOperations = 0;
		for (int bottom = 0, top = palindrome.length - 1; bottom < top; bottom++, top--) {
			numOperations += Math.abs(palindrome[bottom] - palindrome[top]);
		}
		return numOperations;
	}

}
