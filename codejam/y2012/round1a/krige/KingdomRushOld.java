package codejam.y2012.round1a.krige;

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
import java.util.StringTokenizer;

public class KingdomRushOld {

	private static final String INPUT_FILE_NAME = "B-sample.in";
	private static final String OUTPUT_FILE_NAME = "B-sample.out";

	private static final String ROOT = "dat";
	private static final String YEAR = "2012";
	private static final String ROUND = "round1a";

	private static final String PATH = ROOT + File.separator + YEAR
			+ File.separator + ROUND + File.separator;

	public static void main(String[] args) throws IOException {
		String[] input = readFromFile(PATH + INPUT_FILE_NAME);
		String[] output = new String[input.length];
		int x = 0;
		for (int i = 0; i < input.length;) {
			int[][] levels = new int[Integer.valueOf(input[i])][2];
			for (int j = 0; j < levels.length; j++) {
				StringTokenizer st = new StringTokenizer(input[i]);
				levels[j][0] = Integer.valueOf(st.nextToken());
				levels[j][1] = Integer.valueOf(st.nextToken());
			}
			System.out.println("Computing case #" + (x + 1));
			// output[x++] = "Case #" + x + ": " + myFunction(a, b);
			i += levels.length;
		}
		writeToFile(output, PATH + OUTPUT_FILE_NAME);
	}

	private static String[] readFromFile(String fileName) throws IOException {
		System.out.println("Reading file " + fileName);
		String[] lines = null;
		FileInputStream fstream = new FileInputStream(fileName);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		lines = new String[countLines(fileName) - 1];
		int i = 0;
		String strLine;
		while ((strLine = br.readLine()) != null) {
			lines[i++] = strLine;
		}
		in.close();
		return lines;
	}

	private static int countLines(String filename) throws IOException {
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
}
