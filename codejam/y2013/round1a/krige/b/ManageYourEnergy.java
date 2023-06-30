package codejam.y2013.round1a.krige.b;

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
 * You've got a very busy calendar today, full of important stuff to do. You
 * worked hard to prepare and make sure all the activities don't overlap. Now
 * it's morning, and you're worried that despite all of your enthusiasm, you
 * won't have the energy to do all of this with full engagement.
 * 
 * You will have to manage your energy carefully. You start the day full of
 * energy - E joules of energy, to be precise. You know you can't go below zero
 * joules, or you will drop from exhaustion. You can spend any non-negative,
 * integer number of joules on each activity (you can spend zero, if you feel
 * lazy), and after each activity you will regain R joules of energy. No matter
 * how lazy you are, however, you cannot have more than E joules of energy at
 * any time; any extra energy you would regain past that point is wasted.
 * 
 * Now, some things (like solving Code Jam problems) are more important than
 * others. For the ith activity, you have a value vi that expresses how
 * important this activity is to you. The gain you get from each activity is the
 * value of the activity, multiplied by the amount of energy you spent on the
 * activity (in joules). You want to manage your energy so that your total gain
 * will be as large as possible.
 * 
 * Note that you cannot reorder the activities in your calendar. You just have
 * to manage your energy as well as you can with the calendar you have.
 * 
 * Input
 * 
 * The first line of the input gives the number of test cases, T. T test cases
 * follow. Each test case is described by two lines. The first contains three
 * integers: E, the maximum (and initial) amount of energy, R, the amount you
 * regain after each activity, and N, the number of activities planned for the
 * day. The second line contains N integers vi, describing the values of the
 * activities you have planned for today.
 * 
 * Output
 * 
 * For each test case, output one line containing "Case #x: y", where x is the
 * case number (starting from 1) and y is the maximum gain you can achieve by
 * managing your energy that day.
 * 
 * Limits
 * 
 * 1 ≤ T ≤ 100.
 * 
 * Small dataset
 * 
 * 1 ≤ E ≤ 5. 1 ≤ R ≤ 5. 1 ≤ N ≤ 10. 1 ≤ vi ≤ 10.
 * 
 * Large dataset
 * 
 * 1 ≤ E ≤ 107. 1 ≤ R ≤ 107. 1 ≤ N ≤ 104. 1 ≤ vi ≤ 107.
 * 
 * UNDER CONSTRUCTION
 * 
 */
public class ManageYourEnergy {

    private static final String FILE_NAME = "B-sample";

    private static final String INPUT_FILE_NAME = FILE_NAME + ".in";
    private static final String OUTPUT_FILE_NAME = FILE_NAME + ".out";

    private static final String ROOT_DIR = "dat";
    private static final String YEAR = "2013";
    private static final String ROUND = "round1a";

    private static final String PATH = ROOT_DIR + File.separator + YEAR
            + File.separator + ROUND + File.separator;

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(new BufferedInputStream(
                new FileInputStream(PATH + INPUT_FILE_NAME)));
        PrintWriter output = new PrintWriter(new FileWriter(PATH
                + OUTPUT_FILE_NAME));

        int numTestCases = input.nextInt();

        for (int tc = 1; tc <= numTestCases; tc++) {
            int energy = input.nextInt();
            int regain = input.nextInt();
            int[] activities = new int[input.nextInt()];
            for (int i = 0; i < activities.length; i++) {
                activities[i] = input.nextInt();
            }
            String outputLine = "Case #" + tc + ": "
                    + solve(energy, regain, activities);
            System.out.println(outputLine);
            output.println(outputLine);
        }
        input.close();
        output.close();
    }

    private static int solve(int energy, int regain, int[] activities) {
        int gain = 0;
        for (int i = 0; i < activities.length - 1; i++) {
            int used = 0;
            if (activities[i] < activities[i + 1]) {
                used = energy - regain;
            } else {
                used = energy;
            }
            gain += activities[i] * used;
            energy += regain - used;
        }
        gain += activities[activities.length - 1] * energy;
        return gain;
    }
}
