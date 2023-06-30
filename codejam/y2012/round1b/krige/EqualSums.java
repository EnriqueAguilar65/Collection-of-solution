package codejam.y2012.round1b.krige;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class EqualSums {

    private static final String INPUT_FILE_NAME = "C-sample.in";
    private static final String OUTPUT_FILE_NAME = "C-sample-template.out";

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
            int numIntegers = input.nextInt();
            int[] integers = new int[numIntegers];
            for (int j = 0; j < integers.length; j++) {
                integers[j] = input.nextInt();
            }
            String outputLine = "Case #" + i + ":\n" + solve(integers);

            System.out.println(outputLine);
            output.println(outputLine);
        }
        input.close();
        output.close();
    }

    private static String solve(int[] integers) {
        int[] set1 = new int[integers.length];
        int[] set2 = new int[integers.length];
        set1[0] = 0;
        set2[0] = 1;
        int set1Length = 1;
        while (set1Length < set1.length) {
            set1Length++;
        }
        return null;
    }
}
