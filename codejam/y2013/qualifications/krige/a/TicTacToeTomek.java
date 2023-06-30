package codejam.y2013.qualifications.krige.a;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Problem
 * 
 * Tic-Tac-Toe-Tomek is a game played on a 4 x 4 square board. The board starts
 * empty, except that a single 'T' symbol may appear in one of the 16 squares.
 * There are two players: X and O. They take turns to make moves, with X
 * starting. In each move a player puts her symbol in one of the empty squares.
 * Player X's symbol is 'X', and player O's symbol is 'O'.
 * 
 * After a player's move, if there is a row, column or a diagonal containing 4
 * of that player's symbols, or containing 3 of her symbols and the 'T' symbol,
 * she wins and the game ends. Otherwise the game continues with the other
 * player's move. If all of the fields are filled with symbols and nobody won,
 * the game ends in a draw. See the sample input for examples of various winning
 * positions.
 * 
 * Given a 4 x 4 board description containing 'X', 'O', 'T' and '.' characters
 * (where '.' represents an empty square), describing the current state of a
 * game, determine the status of the Tic-Tac-Toe-Tomek game going on. The
 * statuses to choose from are:
 * 
 * "X won" (the game is over, and X won)
 * 
 * "O won" (the game is over, and O won)
 * 
 * "Draw" (the game is over, and it ended in a draw)
 * 
 * "Game has not completed" (the game is not over yet)
 * 
 * If there are empty cells, and the game is not over, you should output
 * "Game has not completed", even if the outcome of the game is inevitable.
 * 
 * Input
 * 
 * The first line of the input gives the number of test cases, T. T test cases
 * follow. Each test case consists of 4 lines with 4 characters each, with each
 * character being 'X', 'O', '.' or 'T' (quotes for clarity only). Each test
 * case is followed by an empty line.
 * 
 * Output
 * 
 * For each test case, output one line containing "Case #x: y", where x is the
 * case number (starting from 1) and y is one of the statuses given above. Make
 * sure to get the statuses exactly right. When you run your code on the sample
 * input, it should create the sample output exactly, including the "Case #1: ",
 * the capital letter "O" rather than the number "0", and so on.
 * 
 * Limits
 * 
 * The game board provided will represent a valid state that was reached through
 * play of the game Tic-Tac-Toe-Tomek as described above.
 * 
 * Small dataset
 * 
 * 1 ≤ T ≤ 10.
 * 
 * Large dataset
 * 
 * 1 ≤ T ≤ 1000.
 * 
 * @author Marco Lackovic <marco.lackovic@gmail.com>
 * @version 1.0, April 13, 2013
 */
public class TicTacToeTomek {

    private static final String FILE_NAME = "A-large";

    private static final String INPUT_FILE_NAME = FILE_NAME + ".in";
    private static final String OUTPUT_FILE_NAME = FILE_NAME + ".out";

    private static final String ROOT_DIR = "dat";
    private static final String YEAR = "2013";
    private static final String ROUND = "qualifications";

    private static final String PATH = ROOT_DIR + File.separator + YEAR
            + File.separator + ROUND + File.separator;

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(new BufferedInputStream(
                new FileInputStream(PATH + INPUT_FILE_NAME)));
        PrintWriter output = new PrintWriter(new FileWriter(PATH
                + OUTPUT_FILE_NAME));

        int numTestCases = input.nextInt();

        for (int tc = 1; tc <= numTestCases; tc++) {
            input.nextLine();
            int[][] m = new int[4][4];
            boolean incomplete = false;
            for (int i = 0; i < m.length; i++) {
                String line = input.nextLine();
                for (int j = 0; j < 4; j++) {
                    char c = line.charAt(j);
                    if (c == 'X') {
                        m[i][j] = 2;
                    } else if (c == 'O') {
                        m[i][j] = 0;
                    } else if (c == '.') {
                        incomplete = true;
                        m[i][j] = 10;
                    } else {
                        m[i][j] = -1;
                    }
                }
            }
            String outputLine = "Case #" + tc + ": " + solve(m, incomplete);
            System.out.println(outputLine);
            output.println(outputLine);
        }
        input.close();
        output.close();
    }

    private static String solve(int[][] m, boolean incomplete) {
        for (int i = 0; i < m.length; i++) {
            int rowSum = 0;
            int colSum = 0;
            for (int j = 0; j < m[i].length; j++) {
                rowSum += m[i][j];
                colSum += m[j][i];
            }
            if (rowSum <= 0 || colSum <= 0) {
                return "O won";
            }
            if (rowSum == 5 || rowSum == 8 || colSum == 5 || colSum == 8) {
                return "X won";
            }
        }
        int diag1Sum = 0;
        int diag2Sum = 0;
        for (int i = 0; i < m.length; i++) {
            diag1Sum += m[i][i];
            diag2Sum += m[i][m.length - i - 1];
        }
        if (diag1Sum <= 0 || diag2Sum <= 0) {
            return "O won";
        }
        if (diag1Sum == 5 || diag1Sum == 8 || diag2Sum == 5 || diag2Sum == 8) {
            return "X won";
        }
        if (incomplete) {
            return "Game has not completed";
        }
        return "Draw";
    }
}
