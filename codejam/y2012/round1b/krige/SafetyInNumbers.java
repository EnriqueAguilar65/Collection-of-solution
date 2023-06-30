package codejam.y2012.round1b.krige;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Scanner;

public class SafetyInNumbers {

    private static final String INPUT_FILE_NAME = "A-small-attempt1.in";
    private static final String OUTPUT_FILE_NAME = "A-small-attempt1.out";

    private static final String ROOT_DIR = "dat";
    private static final String YEAR = "2012";
    private static final String ROUND = "round1b";

    private static final String PATH = ROOT_DIR + File.separator + YEAR
            + File.separator + ROUND + File.separator;

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(new BufferedInputStream(
                new FileInputStream(PATH + INPUT_FILE_NAME)));
        PrintWriter output = new PrintWriter(new FileWriter(PATH
                + OUTPUT_FILE_NAME));

        int numTestCases = input.nextInt();
        for (int i = 1; i <= numTestCases; i++) {
            int numContestants = input.nextInt();
            int[] pointValues = new int[numContestants];
            for (int j = 0; j < pointValues.length; j++) {
                pointValues[j] = input.nextInt();
            }

            String outputLine = "Case #" + i + ": " + solve(pointValues);

            System.out.println(outputLine);
            output.println(outputLine);
        }
        input.close();
        output.close();
    }

    private static String solve(int[] pointValues) {
        DecimalFormat twoDForm = new DecimalFormat("0.0#####");
        StringBuffer result = new StringBuffer();
        int sum = 0;
        for (int i = 0; i < pointValues.length; i++) {
            sum += pointValues[i];
        }
        double optimalSharedScore = (sum * 2) / pointValues.length;
        double max = pointValues[0];
        for (int i = 1; i < pointValues.length; i++) {
            if (max < pointValues[i]) {
                max = pointValues[i];
            }
        }
        if (optimalSharedScore < max) {
            optimalSharedScore = max;
        }
        for (int i = 0; i < pointValues.length; i++) {
            result.append((twoDForm
                    .format((optimalSharedScore - pointValues[i]) / sum * 100))
                    + " ");
        }
        return result.toString();
    }
}
