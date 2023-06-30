package codejam.y2013.round1b.krige.b;

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
 * Diamonds are falling from the sky. People are now buying up locations where
 * the diamonds can land, just to own a diamond if one does land there. You have
 * been offered one such place, and want to know whether it is a good deal.
 * 
 * Diamonds are shaped like, you guessed it, diamonds: they are squares with
 * vertices (X-1, Y), (X, Y+1), (X+1, Y) and (X, Y-1) for some X, Y which we
 * call the center of the diamond. All the diamonds are always in the X-Y plane.
 * X is the horizontal direction, Y is the vertical direction. The ground is at
 * Y=0, and positive Y coordinates are above the ground.
 * 
 * The diamonds fall one at a time along the Y axis. This means that they start
 * at (0, Y) with Y very large, and fall vertically down, until they hit either
 * the ground or another diamond.
 * 
 * When a diamond hits the ground, it falls until it is buried into the ground
 * up to its center, and then stops moving. This effectively means that all
 * diamonds stop falling or sliding if their center reaches Y=0.
 * 
 * When a diamond hits another diamond, vertex to vertex, it can start sliding
 * down, without turning, in one of the two possible directions: down and left,
 * or down and right. If there is no diamond immediately blocking either of the
 * sides, it slides left or right with equal probability. If there is a diamond
 * blocking one of the sides, the falling diamond will slide to the other side
 * until it is blocked by another diamond, or becomes buried in the ground. If
 * there are diamonds blocking the paths to the left and to the right, the
 * diamond just stops.
 * 
 * Consider the example in the picture. The first diamond hits the ground and
 * stops when halfway buried, with its center at (0, 0). The second diamond may
 * slide either to the left or to the right with equal probability. Here, it
 * happened to go left. It stops buried in the ground next to the first diamond,
 * at (-2, 0). The third diamond will also hit the first one. Then it will
 * either randomly slide to the right and stop in the ground, or slide to the
 * left, and stop between and above the two already-placed diamonds. It again
 * happened to go left, so it stopped at (-1, 1). The fourth diamond has no
 * choice: it will slide right, and stop in the ground at (2, 0).
 * 
 * Input
 * 
 * The first line of the input gives the number of test cases, T. T lines
 * follow. Each line contains three integers: the number of falling diamonds N,
 * and the position X, Y of the place you are interested in. Note the place that
 * you are interested in buying does not have to be at or near the ground.
 * 
 * Output
 * 
 * For each test case output one line containing "Case #x: p", where x is the
 * case number (starting from 1) and p is the probability that one of the N
 * diamonds will fall so that its center ends up exactly at (X, Y). The answer
 * will be considered correct if it is within an absolute error of 10-6 away
 * from the correct answer. See the FAQ for an explanation of what that means,
 * and what formats of floating-point numbers we accept.
 * 
 * Limits
 * 
 * 1 ≤ T ≤ 100. -10,000 ≤ X ≤ 10,000. 0 ≤ Y ≤ 10,000. X + Y is even.
 * 
 * Small dataset
 * 
 * 1 ≤ N ≤ 20.
 * 
 * Large dataset
 * 
 * 1 ≤ N ≤ 106.
 * 
 * @author Marco Lackovic <marco.lackovic@gmail.com>
 * 
 *         UNDER CONSTRUCTION
 */
public class FallingDiamonds {
    private static final String YEAR = "2013";
    private static final String ROUND = "round1b";
    private static final String FILE_NAME = "B-small-practice";

    private final int numDiamonds;
    private final int x;
    private final int y;

    public FallingDiamonds(Scanner input) {
        numDiamonds = input.nextInt();
        x = input.nextInt();
        y = input.nextInt();
    }

    private float solve() {

        return 0;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException,
            InterruptedException {

        Scanner input = new Scanner(new BufferedReader(new FileReader(PATH
                + INPUT_FILE_NAME)));

        ExecutorService executorService = Executors
                .newFixedThreadPool(NUM_CPUS);

        int numTestCases = input.nextInt();
        Future<Float>[] result = new Future[numTestCases + 1];
        for (int tc = 1; tc < result.length; tc++) {
            final FallingDiamonds problem = new FallingDiamonds(input);
            final int testCase = tc;
            result[testCase] = executorService.submit(new Callable<Float>() {

                @Override
                public Float call() throws Exception {
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
