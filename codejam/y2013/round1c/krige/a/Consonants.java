package codejam.y2013.round1c.krige.a;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Problem
 * 
 * In English, there are 26 letters that are either vowels or consonants. In
 * this problem, we consider a, e, i, o, and u to be vowels, and the other 21
 * letters to be consonants.
 * 
 * A tribe living in the Greatest Colorful Jungle has a tradition of naming
 * their members using English letters. But it is not easy to come up with a
 * good name for a new member because it reflects the member's social status
 * within the tribe. It is believed that the less common the name he or she is
 * given, the more socially privileged he or she is.
 * 
 * The leader of the tribe is a professional linguist. He notices that
 * hard-to-pronounce names are uncommon, and the reason is that they have too
 * many consecutive consonants. Therefore, he announces that the social status
 * of a member in the tribe is determined by its n-value, which is the number of
 * substrings with at least n consecutive consonants in the name. For example,
 * when n = 3, the name "quartz" has the n-value of 4 because the substrings
 * quartz, uartz, artz, and rtz have at least 3 consecutive consonants each. A
 * greater n-value means a greater social status in the tribe. Two substrings
 * are considered different if they begin or end at a different point (even if
 * they consist of the same letters), for instance "tsetse" contains 11
 * substrings with two consecutive consonants, even though some of them (like
 * "tsetse" and "tsetse") contain the same letters.
 * 
 * All members in the tribe must have their names and n given by the leader.
 * Although the leader is a linguist and able to ensure that the given names are
 * meaningful, he is not good at calculating the n-values. Please help the
 * leader determine the n-value of each name. Note that different names may have
 * different values of n associated with them.
 * 
 * Input
 * 
 * The first line of the input gives the number of test cases, T. T test cases
 * follow. The first line of each test case gives the name of a member as a
 * string of length L, and an integer n. Each name consists of one or more
 * lower-case English letters.
 * 
 * Output
 * 
 * For each test case, output one line containing "Case #x: y", where x is the
 * case number (starting from 1) and y is the n-value of the member's name.
 * 
 * Limits
 * 
 * 1 ≤ T ≤ 100. 0 < n ≤ L.
 * 
 * Small dataset
 * 
 * 1 ≤ L ≤ 100.
 * 
 * Large dataset
 * 
 * 1 ≤ L ≤ 106. The input file will be no larger than 6MB.
 */
public class Consonants {
	private static final String YEAR = "2013";
	private static final String ROUND = "round1c";
	private static final String FILE_NAME = "A-large-practice";

	private final String nameString;
	private int minConsonants;
	private boolean[] isConsonant;

	public Consonants(Scanner input) {
		nameString = input.next();
		minConsonants = input.nextInt();
	}

	private int solve() {
		int numSubstrings = 0;
		char[] name = nameString.toCharArray();
		isConsonant = new boolean[name.length];
		for (int i = 0; i < isConsonant.length; i++) {
			isConsonant[i] = !isVowel(name[i]);
		}
		for (int i = 0; i < isConsonant.length; i++) {
			if (areThereNConsConsonants(i)) {
				int succ = isConsonant.length - i - minConsonants + 1;
				numSubstrings += numPrec(i) * succ;
			}
		}
		return numSubstrings;
	}

	private int numPrec(int i) {
		if (i > 0) {
			if (isConsonant[i - 1]) {
				return 1;
			}
		} else {
			return 1;
		}
		int numChars = 1;
		int numConsonantsInCluster = 0;
		for (int j = i - 1; j >= 0; j--) {
			if (isConsonant[j]) {
				numConsonantsInCluster++;
				if (numConsonantsInCluster >= minConsonants) {
					return numChars;
				} else {
					numChars++;
				}
			} else {
				numChars++;
				numConsonantsInCluster = 0;
			}
		}
		return numChars;
	}

	private boolean areThereNConsConsonants(int i) {
		int numConsConsonants = 0;
		for (int j = i; j < isConsonant.length; j++) {
			if (isConsonant[j]) {
				numConsConsonants++;
			} else {
				break;
			}
		}
		return numConsConsonants >= minConsonants;
	}

	private boolean isVowel(char c) {
		return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException,
			InterruptedException {

		Scanner input = new Scanner(new BufferedReader(new FileReader(PATH
				+ INPUT_FILE_NAME)));

		ExecutorService executorService = Executors
				.newFixedThreadPool(NUM_CPUS);

		int numTestCases = input.nextInt();
		Future<Integer>[] result = new Future[numTestCases + 1];
		for (int tc = 1; tc < result.length; tc++) {
			final Consonants problem = new Consonants(input);
			final int testCase = tc;
			result[testCase] = executorService.submit(new Callable<Integer>() {

				@Override
				public Integer call() throws Exception {
					try {
						return problem.solve();
					} catch (Exception e) {
						e.printStackTrace();
						throw new RuntimeException(
								"Failed to solve test case #" + testCase);
					}
				}
			});
		}
		input.close();
		executorService.shutdown();

		PrintWriter output = new PrintWriter(PATH + OUTPUT_FILE_NAME);
		for (int tc = 1; tc < result.length; tc++) {
			String outputLine;
			try {
				outputLine = "Case #" + tc + ": " + result[tc].get();
			} catch (InterruptedException | ExecutionException e) {
				output.close();
				e.printStackTrace();
				throw new RuntimeException(
						"Failed to get the result of test case #" + tc);
			}
			System.out.println(outputLine);
			output.println(outputLine);
		}
		output.close();
	}

	private static final String INPUT_FILE_NAME = FILE_NAME + ".in";
	private static final String OUTPUT_FILE_NAME = FILE_NAME + ".out";
	private static final String ROOT_DIR = "dat";
	private static final String PATH = ROOT_DIR + File.separator + YEAR
			+ File.separator + ROUND + File.separator;
	private static final int NUM_CPUS = Runtime.getRuntime()
			.availableProcessors();

}
