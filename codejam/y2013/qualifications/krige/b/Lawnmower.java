package codejam.y2013.qualifications.krige.b;

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
 * Alice and Bob have a lawn in front of their house, shaped like an N metre by
 * M metre rectangle. Each year, they try to cut the lawn in some interesting
 * pattern. They used to do their cutting with shears, which was very
 * time-consuming; but now they have a new automatic lawnmower with multiple
 * settings, and they want to try it out.
 * 
 * The new lawnmower has a height setting - you can set it to any height h
 * between 1 and 100 millimetres, and it will cut all the grass higher than h it
 * encounters to height h. You run it by entering the lawn at any part of the
 * edge of the lawn; then the lawnmower goes in a straight line, perpendicular
 * to the edge of the lawn it entered, cutting grass in a swath 1m wide, until
 * it exits the lawn on the other side. The lawnmower's height can be set only
 * when it is not on the lawn.
 * 
 * Alice and Bob have a number of various patterns of grass that they could have
 * on their lawn. For each of those, they want to know whether it's possible to
 * cut the grass into this pattern with their new lawnmower. Each pattern is
 * described by specifying the height of the grass on each 1m x 1m square of the
 * lawn.
 * 
 * The grass is initially 100mm high on the whole lawn.
 * 
 * Input
 * 
 * The first line of the input gives the number of test cases, T. T test cases
 * follow. Each test case begins with a line containing two integers: N and M.
 * Next follow N lines, with the ith line containing M integers ai,j each, the
 * number ai,j describing the desired height of the grass in the jth square of
 * the ith row.
 * 
 * Output
 * 
 * For each test case, output one line containing "Case #x: y", where x is the
 * case number (starting from 1) and y is either the word "YES" if it's possible
 * to get the x-th pattern using the lawnmower, or "NO", if it's impossible
 * (quotes for clarity only).
 * 
 * Limits
 * 
 * 1 ≤ T ≤ 100.
 * 
 * Small dataset
 * 
 * 1 ≤ N, M ≤ 10. 1 ≤ ai,j ≤ 2.
 * 
 * Large dataset
 * 
 * 1 ≤ N, M ≤ 100. 1 ≤ ai,j ≤ 100.
 * 
 * @author Marco Lackovic <marco.lackovic@gmail.com>
 * @version 2.0, April 21, 2013
 */
public class Lawnmower {
    private static final String FILE_NAME = "B-large-practice";

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
            int n = input.nextInt();
            int m = input.nextInt();
            int[][] g = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    g[i][j] = input.nextInt();
                }
            }
            String outputLine = "Case #" + tc + ": " + solve(g);
            System.out.println(outputLine);
            output.println(outputLine);
        }
        input.close();
        output.close();
    }

    private static String solve(int[][] g) {
        for (int i = 0; i < g.length; i++) {
            int maxRow = 0;
            for (int j = 0; j < g[i].length; j++) {
                if (g[i][j] > maxRow) {
                    maxRow = g[i][j];
                }
            }
            for (int j = 0; j < g[i].length; j++) {
                if (g[i][j] < maxRow) {
                    int diff = maxRow - g[i][j];
                    for (int k = 0; k < g.length; k++) {
                        if (g[k][j] + diff > maxRow) {
                            return "NO";
                        }
                    }
                }
            }
        }
        return "YES";
    }
}
