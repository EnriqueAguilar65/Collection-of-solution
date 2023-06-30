package codejam.y2014.round1c.krige.a;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
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
 * Vida says she's part Elf: that at least one of her ancestors was an Elf. But
 * she doesn't know if it was a parent (1 generation ago), a grandparent (2
 * generations ago), or someone from even more generations ago. Help her out!
 * 
 * Being part Elf works the way you probably expect. People who are Elves,
 * Humans and part-Elves are all created in the same way: two parents get
 * together and have a baby. If one parent is A/B Elf, and the other parent is
 * C/D Elf, then their baby will be (A/B + C/D) / 2 Elf. For example, if someone
 * who is 0/1 Elf and someone who is 1/2 Elf have a baby, that baby will be 1/4
 * Elf.
 * 
 * Vida is certain about one thing: 40 generations ago, she had 240 different
 * ancestors, and each one of them was 1/1 Elf or 0/1 Elf.
 * 
 * Vida says she's P/Q Elf. Tell her what is the minimum number of generations
 * ago that there could have been a 1/1 Elf in her family. If it is not possible
 * for her to be P/Q Elf, tell her that she must be wrong!
 * 
 * Input
 * 
 * The first line of the input gives the number of test cases, T. T lines
 * follow. Each contains a fraction of the form P/Q, where P and Q are integers.
 * 
 * Output
 * 
 * For each test case, output one line containing "Case #x: y", where x is the
 * test case number (starting from 1) and y is the minimum number of generations
 * ago a 1/1 Elf in her family could have been if she is P/Q Elf. If it's
 * impossible that Vida could be P/Q Elf, y should be the string "impossible"
 * (without the quotes).
 * 
 * Limits
 * 
 * 1 ≤ T ≤ 100.
 * 
 * Small dataset
 * 
 * 1 ≤ P < Q ≤ 1000. P and Q have no common factors. That means P/Q is a
 * fraction in lowest terms.
 * 
 * Large dataset
 * 
 * 1 ≤ P < Q ≤ 1012. P and Q may have common factors. P/Q is not guaranteed to
 * be a fraction in lowest terms.
 * 
 * @author Marco Lackovic <marco.lackovic@gmail.com>
 * @version 1.0, 11/may/2014
 */
public class PartElf {
	private static final String YEAR = "2014";
	private static final String ROUND = "round1c";
	private static final String FILE_NAME = "A-small-attempt0";
	private static String[] testCases;

	private static final String IMPOSSIBLE = "impossible";
	private static final int NUM_GENERATIONS = 40;

	private final double p;
	private final double q;

	public PartElf(Scanner input, int testCaseID) {
		String line = input.nextLine();
		testCases[testCaseID] = line;
		String[] tokens = line.split("/");
		p = Integer.parseInt(tokens[0]);
		q = Integer.parseInt(tokens[1]);
	}

	// Returns -1 if impossible
	private int solve() {
		if (q % 2 != 0) {
			return -1;
		}
		double pONq = p / q;
		double step = 1;
		for (int i = 1; i <= NUM_GENERATIONS; i++) {
			step /= 2;
			if (pONq >= step) {
				return i;
			}
		}
		return -1;
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException,
			InterruptedException {
		try (Scanner input = new Scanner(new BufferedInputStream(
				new FileInputStream(PATH + INPUT_FILE_NAME)));
				PrintWriter output = new PrintWriter(new FileWriter(PATH
						+ OUTPUT_FILE_NAME));
				PrintWriter outputTest = new PrintWriter(new FileWriter(PATH
						+ TEST_FILE_NAME))) {

			System.out.println("Input delimiters = " + input.delimiter());

			ExecutorService executorService = Executors
					.newFixedThreadPool(NUM_CPUS);

			int numTestCases = input.nextInt();
			testCases = new String[numTestCases + 1];
			input.nextLine();
			Future<Integer>[] result = new Future[numTestCases + 1];
			for (int testCaseID = 1; testCaseID < result.length; testCaseID++) {
				final PartElf problem = new PartElf(input, testCaseID);
				final int testCase = testCaseID;
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
				String tcResult = IMPOSSIBLE;
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
				outputTest.println(outputLine);
				outputTest.println("Input:\n" + testCases[tc]
						+ "\n------------------------------");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static final String INPUT_FILE_NAME = FILE_NAME + ".in";
	private static final String OUTPUT_FILE_NAME = FILE_NAME + ".out";
	private static final String TEST_FILE_NAME = FILE_NAME + ".test";
	private static final String ROOT_DIR = "dat";
	private static final String PATH = ROOT_DIR + File.separator + YEAR
			+ File.separator + ROUND + File.separator;
	private static final int NUM_CPUS = Runtime.getRuntime()
			.availableProcessors();

}
