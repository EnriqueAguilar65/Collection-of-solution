package codejam.y2012.round1c.krige;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class OutOfGas {

    private static final String INPUT_FILE_NAME = "B-sample.in";
    private static final String OUTPUT_FILE_NAME = "B-sample-template.out";

    private static final String ROOT_DIR = "dat";
    private static final String YEAR = "2012";
    private static final String ROUND = "round1c";

    private static final String PATH = ROOT_DIR + File.separator + YEAR
            + File.separator + ROUND + File.separator;

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(new BufferedInputStream(
                new FileInputStream(PATH + INPUT_FILE_NAME)));
        PrintWriter output = new PrintWriter(new FileWriter(PATH
                + OUTPUT_FILE_NAME));

        int numTestCases = input.nextInt();
        for (int i = 1; i <= numTestCases; i++) {
            double distance = input.nextDouble();
            int numSamples = input.nextInt();
            int numAccelerations = input.nextInt();
            double[][] samples = new double[numSamples][2];
            for (int j = 0; j < numSamples; j++) {
                samples[j][0] = input.nextDouble();
                samples[j][1] = input.nextDouble();
            }
            double[] accelerations = new double[numAccelerations];
            for (int j = 0; j < numAccelerations; j++) {
                accelerations[j] = input.nextDouble();
            }
            String outputLine = "Case #" + i + ": "
                    + solve(distance, samples, accelerations);

            System.out.println(outputLine);
            output.println(outputLine);
        }
        input.close();
        output.close();
    }

    private static String solve(double distance, double[][] samples,
            double[] accelerations) {
        System.out.println("Distance = " + distance);
        for (int i = 0; i < samples.length; i++) {
            System.out.println(samples[i][0] + " " + samples[i][1]);
        }
        for (int i = 0; i < accelerations.length; i++) {
            System.out.print(accelerations[i] + " ");
        }
        System.out.println();
        return null;
    }
}
