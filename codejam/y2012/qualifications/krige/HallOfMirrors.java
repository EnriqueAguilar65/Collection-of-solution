/*
 * Problem D. Hall of Mirrors
 * 
 */

package codejam.y2012.qualifications.krige;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.StringTokenizer;

import codejam.krige.commons.Utils;

/**
 * @author Marco Lackovic <marco.lackovic@gmail.com>
 * @version 1.1, Apr 14, 2012
 */
public class HallOfMirrors {

    private static final String INPUT_FILE_NAME = "D-small-attempt0.in";
    private static final String OUTPUT_FILE_NAME = "D-small-attempt0.out";

    private static final char ME = 'X';

    public static void main(String[] args) throws IOException {
        String[] input = readMirrorsFromFile(INPUT_FILE_NAME);
        String[] output = new String[input.length];
        int x = 0;
        for (int i = 0; i < output.length;) {
            String line = input[i];
            StringTokenizer st = new StringTokenizer(line);
            int height = Integer.valueOf(st.nextToken());
            int width = Integer.valueOf(st.nextToken());
            int distance = Integer.valueOf(st.nextToken());
            char[][] grid = new char[height][width];
            int k = 0;
            for (int j = i + 1; j <= i + height; j++) {
                grid[k++] = input[j].toCharArray();
            }
            i += height + 1;
            System.out.println("Computing case #" + (x + 1));
            output[x++] = "Case #" + x + ": " + numReflections(grid, distance);
        }
        Utils.writeToFile(output, OUTPUT_FILE_NAME);
    }

    public static String[] readMirrorsFromFile(String fileName)
            throws IOException {
        System.out.println("Reading file " + fileName);
        ArrayList<String> linesAL = new ArrayList<String>();
        FileInputStream fstream = new FileInputStream(fileName);
        DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String strLine = br.readLine();
        while ((strLine = br.readLine()) != null) {
            if (!strLine.isEmpty()) {
                linesAL.add(strLine);
            }
        }
        in.close();
        String[] linesAr = new String[linesAL.size()];
        for (int i = 0; i < linesAr.length; i++) {
            linesAr[i] = linesAL.get(i);
        }
        return linesAr;
    }

    private static int numReflections(char[][] grid, int distance) {
        int myX = 0;
        int myY = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == ME) {
                    myX = i;
                    myY = j;
                    break;
                }
            }
        }
        ArrayList<Double> dirToExplore = new ArrayList<Double>();
        for (double angle = 0; angle < 360; angle += 45) {
            dirToExplore.add(angle);
        }
        ListIterator<Double> it = dirToExplore.listIterator();
        while (it.hasNext()) {
            // TODO Complete Double angle = (Double) it.next();
        }
        System.out.println(myX + "," + myY);
        return 0;
    }
}
