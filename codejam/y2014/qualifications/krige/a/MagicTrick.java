package codejam.y2014.qualifications.krige.a;

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
 * Recently you went to a magic show. You were very impressed by one of the
 * tricks, so you decided to try to figure out the secret behind it!
 * 
 * The magician starts by arranging 16 cards in a square grid: 4 rows of cards,
 * with 4 cards in each row. Each card has a different number from 1 to 16
 * written on the side that is showing. Next, the magician asks a volunteer to
 * choose a card, and to tell him which row that card is in.
 * 
 * Finally, the magician arranges the 16 cards in a square grid again, possibly
 * in a different order. Once again, he asks the volunteer which row her card is
 * in. With only the answers to these two questions, the magician then correctly
 * determines which card the volunteer chose. Amazing, right?
 * 
 * You decide to write a program to help you understand the magician's
 * technique. The program will be given the two arrangements of the cards, and
 * the volunteer's answers to the two questions: the row number of the selected
 * card in the first arrangement, and the row number of the selected card in the
 * second arrangement. The rows are numbered 1 to 4 from top to bottom.
 * 
 * Your program should determine which card the volunteer chose; or if there is
 * more than one card the volunteer might have chosen (the magician did a bad
 * job); or if there's no card consistent with the volunteer's answers (the
 * volunteer cheated).
 * 
 * Solving this problem
 * 
 * Usually, Google Code Jam problems have 1 Small input and 1 Large input. This
 * problem has only 1 Small input. Once you have solved the Small input, you
 * have finished solving this problem.
 * 
 * Input
 * 
 * The first line of the input gives the number of test cases, T. T test cases
 * follow. Each test case starts with a line containing an integer: the answer
 * to the first question. The next 4 lines represent the first arrangement of
 * the cards: each contains 4 integers, separated by a single space. The next
 * line contains the answer to the second question, and the following four lines
 * contain the second arrangement in the same format.
 * 
 * Output
 * 
 * For each test case, output one line containing "Case #x: y", where x is the
 * test case number (starting from 1).
 * 
 * If there is a single card the volunteer could have chosen, y should be the
 * number on the card. If there are multiple cards the volunteer could have
 * chosen, y should be "Bad magician!", without the quotes. If there are no
 * cards consistent with the volunteer's answers, y should be
 * "Volunteer cheated!", without the quotes. The text needs to be exactly right,
 * so consider copying/pasting it from here.
 * 
 * Limits
 * 
 * 1 ≤ T ≤ 100.
 * 
 * 1 ≤ both answers ≤ 4.
 * 
 * Each number from 1 to 16 will appear exactly once in each arrangement.
 * 
 * @author Marco Lackovic <marco.lackovic@gmail.com>
 * @version 1.0, 12/apr/2014
 */
public class MagicTrick {
	private static final String YEAR = "2014";
	private static final String ROUND = "qualifications";
	private static final String FILE_NAME = "A-small-attempt3";

	private static final int BAD_MAGICIAN = -1;
	private static final int VOLUNTEER_CHEATED = -2;

	private static final String BAD_MAGICIAN_STRING = "Bad magician!";
	private static final String VOLUNTEER_CHEATED_STRING = "Volunteer cheated!";

	private final static int DIMENSION = 4;
	private final int[] answer = new int[DIMENSION];
	private final int[][][] grid = new int[DIMENSION][DIMENSION][DIMENSION];

	public MagicTrick(Scanner input) {
		for (int i = 0; i < 2; i++) {
			answer[i] = input.nextInt() - 1;
			grid[i] = new int[DIMENSION][DIMENSION];
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = new int[DIMENSION];
				for (int k = 0; k < grid[i][j].length; k++) {
					grid[i][j][k] = input.nextInt();
				}
			}
		}
	}

	private int solve() {
		int[][] row = new int[2][];
		for (int i = 0; i < 2; i++) {
			row[i] = grid[i][answer[i]];
		}
		int cardNumber = 0;
		for (int i = 0; i < row[0].length; i++) {
			for (int j = 0; j < row[1].length; j++) {
				if (row[0][i] == row[1][j]) {
					if (cardNumber > 0) {
						return BAD_MAGICIAN;
					}
					cardNumber = row[0][i];
				}
			}
		}
		if (cardNumber > 0) {
			return cardNumber;
		}
		return VOLUNTEER_CHEATED;
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
			Future<Integer>[] result = new Future[numTestCases + 1];
			for (int tc = 1; tc < result.length; tc++) {
				final MagicTrick problem = new MagicTrick(input);
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
				String tcResult;
				try {
					switch (result[tc].get()) {
					case BAD_MAGICIAN:
						tcResult = BAD_MAGICIAN_STRING;
						break;
					case VOLUNTEER_CHEATED:
						tcResult = VOLUNTEER_CHEATED_STRING;
						break;
					default:
						tcResult = Integer.toString(result[tc].get());
						break;
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
