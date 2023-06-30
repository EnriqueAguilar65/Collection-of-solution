package codejam.krige.commons;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Utils {

    // === Template ============================================================

    private static final String INPUT_FILE_NAME = "A-sample.in";
    private static final String OUTPUT_FILE_NAME = "A-sample.out";

    private static final String ROOT = "dat";
    private static final String YEAR = "2012";
    private static final String ROUND = "round1a";

    private static final String PATH = ROOT + File.separator + YEAR
            + File.separator + ROUND + File.separator;

    public static void main(String[] args) throws IOException {
        String[] input = Utils.readFromFile(PATH + INPUT_FILE_NAME);
        String[] output = new String[input.length];
        Utils.writeToFile(output, PATH + OUTPUT_FILE_NAME);
    }

    public static String[] readFromFile(String fileName) throws IOException {
        System.out.println("Reading file " + fileName);
        String[] lines = null;
        FileInputStream fstream = new FileInputStream(fileName);
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine = br.readLine();
        lines = new String[Integer.valueOf(strLine)];
        int i = 0;
        while ((strLine = br.readLine()) != null) {
            lines[i++] = strLine;
        }
        in.close();
        return lines;
    }

    public static void writeToFile(String[] lines, String fileName)
            throws IOException {
        System.out.println("Writing file " + fileName);
        FileWriter outFile = new FileWriter(fileName);
        PrintWriter out = new PrintWriter(outFile);
        for (String line : lines) {
            out.println(line);
        }
        out.close();
    }

    // === End template ========================================================

    public static int countLines(String filename) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(filename));
        try {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            while ((readChars = is.read(c)) != -1) {
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n')
                        ++count;
                }
            }
            return count;
        } finally {
            is.close();
        }
    }
}
