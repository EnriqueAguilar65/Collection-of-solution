package codejam.y2013.round1b.krige.c;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Problem
 * 
 * Gagan just got an email from her friend Jorge. The email contains important
 * information, but unfortunately it was corrupted when it was sent: all of the
 * spaces are missing, and after the removal of the spaces, some of the letters
 * have been changed to other letters! All Gagan has now is a string S of
 * lower-case characters.
 * 
 * You know that the email was originally made out of words from the dictionary
 * described below. You also know the letters were changed after the spaces were
 * removed, and that the difference between the indices of any two letter
 * changes is not less than 5. So for example, the string "code jam" could have
 * become "codejam", "dodejbm", "zodejan" or "cidejab", but not "kodezam"
 * (because the distance between the indices of the "k" change and the "z"
 * change is only 4).
 * 
 * What is the minimum number of letters that could have been changed?
 * 
 * Dictionary
 * 
 * In order to solve this problem, you'll need an extra file: a special
 * dictionary that you can find at
 * https://code.google.com/codejam/contest/static/garbled_email_dictionary.txt.
 * It is not a dictionary from any natural language, though it does contain some
 * English words. Each line of the dictionary contains one word. The dictionary
 * file should be 3844492 bytes in size, contain 521196 words, start with the
 * word "a", and end with the word "zymuznh".
 * 
 * When you're submitting the code you used to solve this problem, you shouldn't
 * include the dictionary. As usual, however, you must submit all code you used
 * to solve the problem.
 * 
 * Note that if you are using Windows and want to look at the dictionary file,
 * you should avoid Notepad, and instead use WordPad or another piece of
 * software, or else all the words might appear on the same line.
 * 
 * Input
 * 
 * The first line of the input gives the number of test cases, T. T test cases
 * follow. Each test case consists of a single line containing a string S,
 * consisting of lower-case characters a-z.
 * 
 * Output
 * 
 * For each test case, output one line containing "Case #x: y", where x is the
 * case number (starting from 1) and y is the minimum number of letters that
 * could have been changed in order to make S.
 * 
 * Limits
 * 
 * S is valid: it is possible to make it using the method described above.
 * 
 * Small dataset
 * 
 * 1 ≤ T ≤ 20. 1 ≤ length of S ≤ 50.
 * 
 * Large dataset
 * 
 * 1 ≤ T ≤ 4. 1 ≤ length of S ≤ 4000.
 * 
 * UNDER CONSTRUCTION
 * 
 */
public class GarbledEmail {

    private static final String FILE_NAME = "C-sample";

    private static final String INPUT_FILE_NAME = FILE_NAME + ".in";
    private static final String OUTPUT_FILE_NAME = FILE_NAME + ".out";

    private static final String ROOT_DIR = "dat";
    private static final String YEAR = "2013";
    private static final String ROUND = "round1b";

    private static final String PATH = ROOT_DIR + File.separator + YEAR
            + File.separator + ROUND + File.separator;

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(new BufferedInputStream(
                new FileInputStream(PATH + INPUT_FILE_NAME)));
        PrintWriter output = new PrintWriter(new FileWriter(PATH
                + OUTPUT_FILE_NAME));

        int numTestCases = input.nextInt();

        for (int tc = 1; tc <= numTestCases; tc++) {
            long aMote = input.nextLong();
            int numMotes = input.nextInt();
            long[] motes = new long[numMotes];
            for (int i = 0; i < numMotes; i++) {
                motes[i] = input.nextLong();
            }
            Arrays.sort(motes);
            String outputLine = "Case #" + tc + ": " + solve(aMote, motes);
            System.out.println(outputLine);
            output.println(outputLine);
        }
        input.close();
        output.close();
    }

    private static long solve(long aMote, long[] motes) {
        if (aMote == 1) {
            return motes.length;
        }
        long solution1 = solve1(aMote, motes);
        long solution2 = solve2(aMote, motes);
        return solution1 < solution2 ? solution1 : solution2;
    }

    private static long solve1(long aMote, long[] motes) {
        long numOperations = 0;
        for (int i = 0; i < motes.length; i++) {
            if (motes[i] < aMote) {
                aMote += motes[i];
            } else {
                long extraMote = aMote - 1;
                if (motes[i] < aMote + extraMote) {
                    aMote += extraMote + motes[i];
                } else {
                    numOperations += motes.length - i;
                    break;
                }
                numOperations++;
            }
        }
        return numOperations;
    }

    private static long solve2(long aMote, long[] motes) {
        long numOperations = 0;
        long[] jollies = new long[motes.length];
        for (int i = 0; i < motes.length; i++) {
            if (motes[i] < aMote) {
                aMote += motes[i];
                jollies[i] = aMote - 1;
            } else {
                long extraMote = aMote - 1;
                if (motes[i] < aMote + extraMote) {
                    aMote += extraMote + motes[i];
                } else {
                    boolean found = false;
                    int numOpToRemove = 0;
                    int extraMoteTemp = 0;
                    for (int j = i - 1; j >= 0; j--) {
                        if (jollies[j] > 0) {
                            extraMoteTemp += jollies[j];
                            if (motes[i] < extraMoteTemp + aMote) {
                                // go back to that position and use the jolly
                                i = j;
                                numOperations -= numOpToRemove;
                                found = true;
                                break;
                            }
                        } else {
                            numOpToRemove++;
                        }
                    }
                    if (!found) {
                        numOperations += motes.length - i;
                        break;
                    }
                }
                numOperations++;
            }
        }
        return numOperations;
    }
}
