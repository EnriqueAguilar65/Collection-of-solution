package codejam.y2013.round1b.krige.a;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Problem
 * 
 * Armin is playing Osmos, a physics-based puzzle game developed by Hemisphere
 * Games. In this game, he plays a "mote", moving around and absorbing smaller
 * motes.
 * 
 * A "mote" in English is a small particle. In this game, it's a thing that
 * absorbs (or is absorbed by) other things! The game in this problem has a
 * similar idea to Osmos, but does not assume you have played the game.
 * 
 * When Armin's mote absorbs a smaller mote, his mote becomes bigger by the
 * smaller mote's size. Now that it's bigger, it might be able to absorb even
 * more motes. For example: suppose Armin's mote has size 10, and there are
 * other motes of sizes 9, 13 and 19. At the start, Armin's mote can only absorb
 * the mote of size 9. When it absorbs that, it will have size 19. Then it can
 * only absorb the mote of size 13. When it absorbs that, it'll have size 32.
 * Now Armin's mote can absorb the last mote.
 * 
 * Note that Armin's mote can absorb another mote if and only if the other mote
 * is smaller. If the other mote is the same size as his, his mote can't absorb
 * it.
 * 
 * You are responsible for the program that creates motes for Armin to absorb.
 * The program has already created some motes, of various sizes, and has created
 * Armin's mote. Unfortunately, given his mote's size and the list of other
 * motes, it's possible that there's no way for Armin's mote to absorb them all.
 * 
 * You want to fix that. There are two kinds of operations you can perform, in
 * any order, any number of times: you can add a mote of any positive integer
 * size to the level, or you can remove any one of the existing motes. What is
 * the minimum number of times you can perform those operations in order to make
 * it possible for Armin's mote to absorb every other mote?
 * 
 * For example, suppose Armin's mote is of size 10 and the other motes are of
 * sizes [9, 20, 25, 100]. This level isn't currently solvable, but by adding a
 * mote of size 3 and removing the mote of size 100, you can make it solvable in
 * only 2 operations. The answer here is 2.
 * 
 * Input
 * 
 * The first line of the input gives the number of test cases, T. T test cases
 * follow. The first line of each test case gives the size of Armin's mote, A,
 * and the number of other motes, N. The second line contains the N sizes of the
 * other motes. All the mote sizes given will be integers.
 * 
 * Output
 * 
 * For each test case, output one line containing "Case #x: y", where x is the
 * case number (starting from 1) and y is the minimum number of operations
 * needed to make the level solvable.
 * 
 * Limits
 * 
 * 1 ≤ T ≤ 100. Small dataset
 * 
 * 1 ≤ A ≤ 100. 1 ≤ all mote sizes ≤ 100. 1 ≤ N ≤ 10.
 * 
 * Large dataset
 * 
 * 1 ≤ A ≤ 106. 1 ≤ all mote sizes ≤ 106. 1 ≤ N ≤ 100.
 * 
 * @author Marco Lackovic <marco.lackovic@gmail.com>
 * @version 1.0, 5/may/2013
 */
public class Osmos {
    private static final String YEAR = "2013";
    private static final String ROUND = "round1b";
    private static final String FILE_NAME = "A-large-practice";

    private final int mote;
    private int[] motes;

    public Osmos(Scanner input) {
        mote = input.nextInt();
        motes = new int[input.nextInt()];
        for (int i = 0; i < motes.length; i++) {
            motes[i] = input.nextInt();
        }
    }

    private int solve() {
        if (mote == 1) {
            return motes.length;
        }
        if (motes.length == 1) {
            return mote > motes[0] ? 0 : 1;
        }
        Arrays.sort(motes);
        int currentMote = mote;
        int numOperations = 0;
        for (int i = 0; i < motes.length; i++) {
            if (motes[i] < currentMote) {
                currentMote += motes[i];
            } else {
                int extraMote = 2 * currentMote - 1;
                int extraOperations = 1;
                while (extraMote <= motes[i]) {
                    extraMote += extraMote - 1;
                    extraOperations++;
                }
                if (extraOperations >= motes.length - i) {
                    return numOperations + motes.length - i;
                } else if (extraOperations >= motes.length) {
                    return motes.length;
                }
                currentMote = extraMote + motes[i];
                numOperations += extraOperations;
            }
        }
        return numOperations;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException,
            InterruptedException {

        Scanner input = new Scanner(new BufferedReader(new FileReader(PATH
                + INPUT_FILE_NAME)));

        ExecutorService executorService = Executors
                .newFixedThreadPool(NUM_CPUS);

        int numTestCases = input.nextInt();
        Future<Integer>[] result = new Future[numTestCases + 1];
        for (int tc = 1; tc < result.length; tc++) {
            final Osmos problem = new Osmos(input);
            final int testCase = tc;
            result[testCase] = executorService.submit(new Callable<Integer>() {

                @Override
                public Integer call() throws Exception {
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
