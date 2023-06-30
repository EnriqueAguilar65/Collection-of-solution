package codejam.y2013.round1a.krige.a;

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
 * Maria has been hired by the Ghastly Chemicals Junkies (GCJ) company to help
 * them manufacture bullseyes. A bullseye consists of a number of concentric
 * rings (rings that are centered at the same point), and it usually represents
 * an archery target. GCJ is interested in manufacturing black-and-white
 * bullseyes.
 * 
 * Maria starts with t millilitres of black paint, which she will use to draw
 * rings of thickness 1cm (one centimetre). A ring of thickness 1cm is the space
 * between two concentric circles whose radii differ by 1cm.
 * 
 * Maria draws the first black ring around a white circle of radius r cm. Then
 * she repeats the following process for as long as she has enough paint to do
 * so:
 * 
 * Maria imagines a white ring of thickness 1cm around the last black ring. Then
 * she draws a new black ring of thickness 1cm around that white ring. Note that
 * each "white ring" is simply the space between two black rings. The area of a
 * disk with radius 1cm is π cm2. One millilitre of paint is required to cover
 * area π cm2. What is the maximum number of black rings that Maria can draw?
 * Please note that:
 * 
 * Maria only draws complete rings. If the remaining paint is not enough to draw
 * a complete black ring, she stops painting immediately. There will always be
 * enough paint to draw at least one black ring.
 * 
 * Input
 * 
 * The first line of the input gives the number of test cases, T. T test cases
 * follow. Each test case consists of a line containing two space separated
 * integers: r and t.
 * 
 * Output
 * 
 * For each test case, output one line containing "Case #x: y", where x is the
 * case number (starting from 1) and y is the maximum number of black rings that
 * Maria can draw.
 * 
 * Limits
 * 
 * Small dataset
 * 
 * 1 ≤ T ≤ 1000. 1 ≤ r, t ≤ 1000.
 * 
 * Large dataset
 * 
 * 1 ≤ T ≤ 6000. 1 ≤ r ≤ 1018. 1 ≤ t ≤ 2 × 1018.
 * 
 */
public class Bullseye {

    private static final String FILE_NAME = "A-large";

    private static final String INPUT_FILE_NAME = FILE_NAME + ".in";
    private static final String OUTPUT_FILE_NAME = FILE_NAME + ".out";

    private static final String ROOT_DIR = "dat";
    private static final String YEAR = "2013";
    private static final String ROUND = "round1a";

    private static final double EPSILON = .0001;

    private static final String PATH = ROOT_DIR + File.separator + YEAR
            + File.separator + ROUND + File.separator;

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(new BufferedInputStream(
                new FileInputStream(PATH + INPUT_FILE_NAME)));
        PrintWriter output = new PrintWriter(new FileWriter(PATH
                + OUTPUT_FILE_NAME));

        int numTestCases = input.nextInt();

        for (int tc = 1; tc <= numTestCases; tc++) {
            double innerRadius = input.nextDouble();
            double millilitres = input.nextDouble();
            String outputLine = "Case #" + tc + ": "
                    + solve(innerRadius, millilitres);
            System.out.println(outputLine);
            output.println(outputLine);
        }
        input.close();
        output.close();
    }

    private static long solve(double innerRadius, double millilitres) {
        long numRings = 0;
        while (true) {
            millilitres -= innerRadius * 2 + 1;
            if (millilitres - EPSILON < 0) {
                if (millilitres + EPSILON > 0) {
                    numRings++;
                }
                break;
            }
            innerRadius += 2.0;
            numRings++;
        }
        return numRings;
    }
}
