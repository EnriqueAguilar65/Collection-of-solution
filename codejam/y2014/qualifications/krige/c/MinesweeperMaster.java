package codejam.y2014.qualifications.krige.c;

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
 * Minesweeper is a computer game that became popular in the 1980s, and is still
 * included in some versions of the Microsoft Windows operating system. This
 * problem has a similar idea, but it does not assume you have played
 * Minesweeper.
 * 
 * In this problem, you are playing a game on a grid of identical cells. The
 * content of each cell is initially hidden. There are M mines hidden in M
 * different cells of the grid. No other cells contain mines. You may click on
 * any cell to reveal it. If the revealed cell contains a mine, then the game is
 * over, and you lose. Otherwise, the revealed cell will contain a digit between
 * 0 and 8, inclusive, which corresponds to the number of neighboring cells that
 * contain mines. Two cells are neighbors if they share a corner or an edge.
 * Additionally, if the revealed cell contains a 0, then all of the neighbors of
 * the revealed cell are automatically revealed as well, recursively. When all
 * the cells that don't contain mines have been revealed, the game ends, and you
 * win.
 * 
 * For example, an initial configuration of the board may look like this ('*'
 * denotes a mine, and 'c' is the first clicked cell):
 * 
 * *..*...**. ....*..... ..c..*.... ........*. ..........
 * 
 * There are no mines adjacent to the clicked cell, so when it is revealed, it
 * becomes a 0, and its 8 adjacent cells are revealed as well. This process
 * continues, resulting in the following board:
 * 
 * ..*...**. 1112*..... 00012*.... 00001111*. 00000001..
 * 
 * At this point, there are still un-revealed cells that do not contain mines
 * (denoted by '.' characters), so the player has to click again in order to
 * continue the game.
 * 
 * You want to win the game as quickly as possible. There is nothing quicker
 * than winning in one click. Given the size of the board (R x C) and the number
 * of hidden mines M, is it possible (however unlikely) to win in one click? You
 * may choose where you click. If it is possible, then print any valid mine
 * configuration and the coordinates of your click, following the specifications
 * in the Output section. Otherwise, print "Impossible".
 * 
 * Input
 * 
 * The first line of the input gives the number of test cases, T. T lines
 * follow. Each line contains three space-separated integers: R, C, and M.
 * 
 * Output
 * 
 * For each test case, output a line containing "Case #x:", where x is the test
 * case number (starting from 1). On the following R lines, output the board
 * configuration with C characters per line, using '.' to represent an empty
 * cell, '*' to represent a cell that contains a mine, and 'c' to represent the
 * clicked cell.
 * 
 * If there is no possible configuration, then instead of the grid, output a
 * line with "Impossible" instead. If there are multiple possible
 * configurations, output any one of them.
 * 
 * Limits
 * 
 * 0 ≤ M < R * C.
 * 
 * Small dataset
 * 
 * 1 ≤ T ≤ 230. 1 ≤ R, C ≤ 5.
 * 
 * Large dataset
 * 
 * 1 ≤ T ≤ 140. 1 ≤ R, C ≤ 50.
 * 
 * @author Marco Lackovic <marco.lackovic@gmail.com>
 * @version 1.0, 3/may/2014
 */
public class MinesweeperMaster {
	private static final String YEAR = "2014";
	private static final String ROUND = "qualifications";
	private static final String FILE_NAME = "C-small-attempt7";

	private final int rows;
	private final int columns;
	private int mines;

	private static final char MINE = '*';
	private static final char CLICK = 'c';
	private static final char EMPTY = '.';
	private static final String IMPOSSIBLE = "Impossible";

	public MinesweeperMaster(Scanner input) {
		rows = input.nextInt();
		columns = input.nextInt();
		mines = input.nextInt();
	}

	private String solve() {
		StringBuilder solution = new StringBuilder();
		if (rows == 1) {
			int i = 0;
			for (; i < mines; i++) {
				solution.append(MINE);
			}
			for (; i < columns - 1; i++) {
				solution.append(EMPTY);
			}
			solution.append(CLICK);
			return solution.toString();
		}
		if (columns == 1) {
			int i = 0;
			for (; i < mines; i++) {
				solution.append(MINE + '\n');
			}
			for (; i < rows - 1; i++) {
				solution.append(EMPTY + '\n');
			}
			solution.append(CLICK);
			return solution.toString();
		}

		char[][] configuration = new char[rows][columns];
		char c = EMPTY;
		int empty = rows * columns - mines;
		if (empty == 1) {
			c = MINE;
		}
		for (int i = 0; i < configuration.length; i++) {
			for (int j = 0; j < configuration[i].length; j++) {
				configuration[i][j] = c;
			}
		}
		if (empty == 1) {
			// This is left intentionally blank
		} else if (empty < 4) {
			return IMPOSSIBLE;
		} else if (rows == 2) {
			if (mines % 2 != 0) {
				return IMPOSSIBLE;
			}
			for (int j = 0; mines > 0; j++, mines -= 2) {
				configuration[rows - 2][j] = MINE;
				configuration[rows - 1][j] = MINE;
			}
		} else if (columns == 2) {
			if (mines % 2 != 0) {
				return IMPOSSIBLE;
			}
			for (int i = 0; mines > 0; i++, mines -= 2) {
				configuration[i][columns - 2] = MINE;
				configuration[i][columns - 1] = MINE;
			}
		} else if (rows % 2 != 0 && mines % rows == 0) {
			if (mines / rows == configuration[0].length - 1) {
				return IMPOSSIBLE;
			}
			for (int i = 0; i < configuration.length; i++) {
				for (int j = 0; j < mines / rows; j++) {
					configuration[i][j] = MINE;
				}
			}
		} else if (columns % 2 != 0 && mines % columns == 0) {
			if (mines / columns == configuration.length - 1) {
				return IMPOSSIBLE;
			}
			for (int i = 0; i < mines / columns; i++) {
				for (int j = 0; j < configuration[i].length; j++) {
					configuration[i][j] = MINE;
				}
			}
		} else if (empty > columns * 2 + 2) {
			for (int i = 0; i < rows && mines > 0; i++) {
				for (int j = 0; j < columns && mines > 0; j++, mines--) {
					configuration[i][j] = MINE;
				}
			}
		} else if (empty > rows * 2 + 2) {
			for (int j = 0; j < columns && mines > 0; j++) {
				for (int i = 0; i < rows && mines > 0; i++, mines--) {
					configuration[i][j] = MINE;
				}
			}
		} else {
			int k1 = 0;
			int k2 = 0;
			for (int i = 0; i < rows - 2 && mines > 0; i++) {
				for (int j = 0; j < columns - 2 && mines > 0; j++, mines--) {
					configuration[i][j] = MINE;
					k1 = i;
					k2 = j;
				}
			}
			if (mines > 0) {
				if (mines % 2 != 0) {
					return IMPOSSIBLE;
				}
				for (int i = 0; i <= k1 && mines > 0; i++, mines -= 2) {
					configuration[i][columns - 2] = MINE;
					configuration[i][columns - 1] = MINE;
				}
				for (int j = 0; j <= k2 && mines > 0; j++, mines -= 2) {
					configuration[rows - 2][j] = MINE;
					configuration[rows - 1][j] = MINE;
				}
			}
		}
		configuration[rows - 1][columns - 1] = CLICK;
		for (int i = 0; i < configuration.length - 1; i++) {
			solution.append(configuration[i]);
			solution.append('\n');
		}
		solution.append(configuration[configuration.length - 1]);
		return solution.toString();
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
			Future<String>[] result = new Future[numTestCases + 1];
			for (int tc = 1; tc < result.length; tc++) {
				final MinesweeperMaster problem = new MinesweeperMaster(input);
				final int testCase = tc;
				result[testCase] = executorService
						.submit(new Callable<String>() {

							@Override
							public String call() throws Exception {
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
					outputLine = "Case #" + tc + ":\n" + result[tc].get();
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
