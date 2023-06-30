package codejam.y2014.qualifications.krige.b;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Introduction
 * 
 * Cookie Clicker is a Javascript game by Orteil, where players click on a
 * picture of a giant cookie. Clicking on the giant cookie gives them cookies.
 * They can spend those cookies to buy buildings. Those buildings help them get
 * even more cookies. Like this problem, the game is very cookie-focused. This
 * problem has a similar idea, but it does not assume you have played Cookie
 * Clicker. Please don't go play it now: it might be a long time before you come
 * back.
 * 
 * Problem
 * 
 * In this problem, you start with 0 cookies. You gain cookies at a rate of 2
 * cookies per second, by clicking on a giant cookie. Any time you have at least
 * C cookies, you can buy a cookie farm. Every time you buy a cookie farm, it
 * costs you C cookies and gives you an extra F cookies per second.
 * 
 * Once you have X cookies that you haven't spent on farms, you win! Figure out
 * how long it will take you to win if you use the best possible strategy.
 * 
 * Example
 * 
 * Suppose C=500.0, F=4.0 and X=2000.0. Here's how the best possible strategy
 * plays out:
 * 
 * 1. You start with 0 cookies, but producing 2 cookies per second.
 * 
 * 2. After 250 seconds, you will have C=500 cookies and can buy a farm that
 * produces F=4 cookies per second.
 * 
 * 3. After buying the farm, you have 0 cookies, and your total cookie
 * production is 6 cookies per second.
 * 
 * 4. The next farm will cost 500 cookies, which you can buy after about
 * 83.3333333 seconds.
 * 
 * 5. After buying your second farm, you have 0 cookies, and your total cookie
 * production is 10 cookies per second.
 * 
 * 6. Another farm will cost 500 cookies, which you can buy after 50 seconds.
 * 
 * 7. After buying your third farm, you have 0 cookies, and your total cookie
 * production is 14 cookies per second.
 * 
 * 8. Another farm would cost 500 cookies, but it actually makes sense not to
 * buy it: instead you can just wait until you have X=2000 cookies, which takes
 * about 142.8571429 seconds.
 * 
 * Total time: 250 + 83.3333333 + 50 + 142.8571429 = 526.1904762 seconds.
 * 
 * Notice that you get cookies continuously: so 0.1 seconds after the game
 * starts you'll have 0.2 cookies, and π seconds after the game starts you'll
 * have 2π cookies.
 * 
 * Input
 * 
 * The first line of the input gives the number of test cases, T. T lines
 * follow. Each line contains three space-separated real-valued numbers: C, F
 * and X, whose meanings are described earlier in the problem statement.
 * 
 * C, F and X will each consist of at least 1 digit followed by 1 decimal point
 * followed by from 1 to 5 digits. There will be no leading zeroes.
 * 
 * Output
 * 
 * For each test case, output one line containing "Case #x: y", where x is the
 * test case number (starting from 1) and y is the minimum number of seconds it
 * takes before you can have X delicious cookies.
 * 
 * We recommend outputting y to 7 decimal places, but it is not required. y will
 * be considered correct if it is close enough to the correct number: within an
 * absolute or relative error of 10-6. See the FAQ for an explanation of what
 * that means, and what formats of real numbers we accept. Limits
 * 
 * 1 ≤ T ≤ 100. Small dataset
 * 
 * 1 ≤ C ≤ 500. 1 ≤ F ≤ 4. 1 ≤ X ≤ 2000. Large dataset
 * 
 * 1 ≤ C ≤ 10000. 1 ≤ F ≤ 100. 1 ≤ X ≤ 100000.
 * 
 * @author Marco Lackovic <marco.lackovic@gmail.com>
 * @version 1.0, 12/apr/2014
 */
public class CookieClickerAlpha {
	private static final String YEAR = "2014";
	private static final String ROUND = "qualifications";
	private static final String FILE_NAME = "B-large";

	private final double farmCost;
	private final double extraCookies;
	private final double cookiesToWin;

	public CookieClickerAlpha(Scanner input) {
		farmCost = input.nextDouble();
		extraCookies = input.nextDouble();
		cookiesToWin = input.nextDouble();
	}

	private double solve() {
		double totalTime = 0;
		double cookieProduction = 2.0;
		double timeToBuy = farmCost / cookieProduction;
		double presentTimeToGoal = cookiesToWin / cookieProduction;
		double futureTimeToGoal = timeToBuy + cookiesToWin
				/ (cookieProduction + extraCookies);

		while (presentTimeToGoal > futureTimeToGoal) {
			totalTime += farmCost / cookieProduction;
			cookieProduction += extraCookies;
			presentTimeToGoal = cookiesToWin / cookieProduction;
			timeToBuy = farmCost / cookieProduction;
			futureTimeToGoal = timeToBuy + cookiesToWin
					/ (cookieProduction + extraCookies);
		}
		return totalTime += presentTimeToGoal;
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
			Future<Double>[] result = new Future[numTestCases + 1];
			for (int tc = 1; tc < result.length; tc++) {
				final CookieClickerAlpha problem = new CookieClickerAlpha(input);
				final int testCase = tc;
				result[testCase] = executorService
						.submit(new Callable<Double>() {

							@Override
							public Double call() throws Exception {
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
					DecimalFormat twoDForm = new DecimalFormat("#.#######");
					outputLine = "Case #" + tc + ": "
							+ twoDForm.format(result[tc].get());
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
