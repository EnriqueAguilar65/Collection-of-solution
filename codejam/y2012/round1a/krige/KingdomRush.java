package codejam.y2012.round1a.krige;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class KingdomRush {

    private static final String INPUT_FILE_NAME = "B-sample.in";
    private static final String OUTPUT_FILE_NAME = "B-sample.out";

    private static final String ROOT_DIR = "dat";
    private static final String YEAR = "2012";
    private static final String ROUND = "round1a";

    private static final String PATH = ROOT_DIR + File.separator + YEAR
            + File.separator + ROUND + File.separator;

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(new BufferedInputStream(
                new FileInputStream(PATH + INPUT_FILE_NAME)));
        PrintWriter output = new PrintWriter(new FileWriter(PATH
                + OUTPUT_FILE_NAME));

        int numTestCases = input.nextInt();
        for (int i = 1; i <= numTestCases; i++) {
            int numLevels = input.nextInt();
            int[][] levels = new int[numLevels][2];
            for (int j = 0; j < levels.length; j++) {
                levels[j][0] = input.nextInt();
                levels[j][1] = input.nextInt();
            }

            String outputLine = "Case #" + i + ": " + solve(levels);

            System.out.println(outputLine);
            output.println(outputLine);
        }
        input.close();
        output.close();
    }

    private static String solve(int[][] levels) {
        String result = "Too Bad";
        System.out.println(levels.length);
        for (int[] level : levels) {
            System.out.println(level[0] + " " + level[1]);
        }
        return result;
    }
}
