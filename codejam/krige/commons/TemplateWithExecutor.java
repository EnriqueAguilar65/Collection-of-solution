package codejam.krige.commons;

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
 * ...
 * 
 * @author Marco Lackovic <marco.lackovic@gmail.com>
 * @version 1.0, 14/apr/2014
 */
public class TemplateWithExecutor {
	private static final String YEAR = "2014";
	private static final String ROUND = "qualifications";
	private static final String FILE_NAME = "A-sample";
	private static String[] testCases;

	// Declare the variables of one test case

	public TemplateWithExecutor(Scanner input, int testCaseID) {
		String line = input.nextLine();
		testCases[testCaseID] = line;
		/*
		 * Initialize the variables of one test case.
		 * 
		 * Use input.nextInt() or input.nextDouble() to read integers or
		 * doubles.
		 */
	}

	private int solve() {

		/*
		 * Write your algorithm.
		 * 
		 * If you change the returning type from int to double or String, change
		 * the template type of the Future and the Callable in the main method
		 * as well.
		 */

		return 0;
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

			ExecutorService executorService = Executors
					.newFixedThreadPool(NUM_CPUS);

			int numTestCases = input.nextInt();
			testCases = new String[numTestCases + 1];
			Future<Integer>[] result = new Future[numTestCases + 1];
			for (int testCaseID = 1; testCaseID < result.length; testCaseID++) {
				final TemplateWithExecutor problem = new TemplateWithExecutor(
						input, testCaseID);
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
