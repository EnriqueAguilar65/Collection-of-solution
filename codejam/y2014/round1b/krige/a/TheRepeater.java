package codejam.y2014.round1b.krige.a;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Problem
 * 
 * Fegla and Omar like to play games every day. But now they are bored of all
 * games, and they would like to play a new game. So they decided to invent
 * their own game called "The Repeater".
 * 
 * They invented a 2 player game. Fegla writes down N strings. Omar's task is to
 * make all the strings identical, if possible, using the minimum number of
 * actions (possibly 0 actions) of the following two types:
 * 
 * Select any character in any of the strings and repeat it (add another
 * instance of this character exactly after it). For example, in a single move
 * Omar can change "abc" to "abbc" (by repeating the character 'b').
 * 
 * Select any two adjacent and identical characters in any of the strings, and
 * delete one of them. For example, in a single move Omar can change "abbc" to
 * "abc" (delete one of the 'b' characters), but can't convert it to "bbc".
 * 
 * The 2 actions are independent; it's not necessary that an action of the first
 * type should be followed by an action of the second type (or vice versa).
 * 
 * Help Omar to win this game by writing a program to find if it is possible to
 * make the given strings identical, and to find the minimum number of moves if
 * it is possible.
 * 
 * Input
 * 
 * The first line of the input gives the number of test cases, T. T test cases
 * follow. Each test case starts with a line containing an integer N which is
 * the number of strings. Followed by N lines, each line contains a non-empty
 * string (each string will consist of lower case English characters only, from
 * 'a' to 'z').
 * 
 * Output
 * 
 * For each test case, output one line containing "Case #x: y", where x is the
 * test case number (starting from 1) and y is the minimum number of moves to
 * make the strings identical. If there is no possible way to make all strings
 * identical, print "Fegla Won" (quotes for clarity).
 * 
 * Limits
 * 
 * 1 ≤ T ≤ 100. 1 ≤ length of each string ≤ 100.
 * 
 * Small dataset
 * 
 * N = 2. Large dataset
 * 
 * 2 ≤ N ≤ 100.
 * 
 * @author Marco Lackovic <marco.lackovic@gmail.com>
 * @version 0.1, 3/may/2014
 */
public class TheRepeater {
	private static final String YEAR = "2014";
	private static final String ROUND = "round1b";
	private static final String FILE_NAME = "A-sample";

	private final LinkedList<String> strings = new LinkedList<>();

	public TheRepeater(Scanner input) {
		int numStrings = input.nextInt();
		input.nextLine();
		for (; numStrings > 0; numStrings--) {
			strings.add(input.nextLine());
		}
		Collections.sort(strings);
	}

	private int solve() {
		System.out.println(strings);
		String reference = strings.get(strings.size() / 2);
		System.out.println("reference = " + reference);
		return -1;
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException,
			InterruptedException {
		try (Scanner input = new Scanner(new BufferedInputStream(
				new FileInputStream(PATH + INPUT_FILE_NAME)));
				PrintWriter output = new PrintWriter(new FileWriter(PATH
						+ OUTPUT_FILE_NAME))) {

			ExecutorService executorService = Executors
					.newFixedThreadPool(NUM_CPUS);

			int numTestCases = input.nextInt();
			System.out.println(numTestCases);
			Future<Integer>[] result = new Future[numTestCases + 1];
			for (int tc = 1; tc < result.length; tc++) {
				final TheRepeater problem = new TheRepeater(input);
				final int testCase = tc;
				result[testCase] = executorService
						.submit(new Callable<Integer>() {

							@Override
							public Integer call() throws Exception {
								try {
									return problem.solve();
								} catch (Exception e) {
									e.printStackTrace();
									throw new RuntimeException(
											"Failed to solve test case #"
													+ testCase);
								}
							}
						});
			}
			executorService.shutdown();

			for (int tc = 1; tc < result.length; tc++) {
				String outputLine;
				String tcResult = "Fegla Won";
				try {
					int r = result[tc].get();
					if (r >= 0) {
						tcResult = Integer.toString(r);
					}
					outputLine = "Case #" + tc + ": " + tcResult;
				} catch (InterruptedException | ExecutionException e) {
					output.close();
					e.printStackTrace();
					throw new RuntimeException(
							"Failed to get the result of test case #" + tc);
				}
				System.out.println(outputLine);
				output.println(outputLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static final String INPUT_FILE_NAME = FILE_NAME + ".in";
	private static final String OUTPUT_FILE_NAME = FILE_NAME + ".out";
	private static final String ROOT_DIR = "dat";
	private static final String PATH = ROOT_DIR + File.separator + YEAR
			+ File.separator + ROUND + File.separator;
	private static final int NUM_CPUS = Runtime.getRuntime()
			.availableProcessors();

}
