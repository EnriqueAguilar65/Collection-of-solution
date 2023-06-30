package codejam.y2012.qualifications.krige;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

public class Googlerese {
	private static final String[] googlereseLines = {
			"ejp mysljylc kd kxveddknmc re jsicpdrysi",
			"rbcpc ypc rtcsra dkh wyfrepkym veddknkmkrkcd",
			"de kr kd eoya kw aej tysr re ujdr lkgc jv" };
	private static final String[] englishLines = {
			"our language is impossible to understand",
			"there are twenty six factorial possibilities",
			"so it is okay if you want to just give up" };
	private static final String INPUT_FILE_NAME = "A-small-attempt0.in";
	private static final String OUTPUT_FILE_NAME = "A-small-attempt0.out";

	private static HashMap<Character, Character> mapping = new HashMap<Character, Character>();

	private static void init() {
		// The first mapping is given by the first example
		mapping.put('z', 'q');
		for (int i = 0; i < googlereseLines.length; i++) {
			for (int j = 0; j < googlereseLines[i].length(); j++) {
				mapping.put(googlereseLines[i].charAt(j),
						englishLines[i].charAt(j));
			}
		}
		// The last mapping is given by the missing letters
		mapping.put('q', 'z');
	}

	public static String[] readFromFile() throws IOException {
		String[] lines = null;
		FileInputStream fstream = new FileInputStream(INPUT_FILE_NAME);
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

	private static void writeToFile(String[] lines) throws IOException {
		FileWriter outFile = new FileWriter(OUTPUT_FILE_NAME);
		PrintWriter out = new PrintWriter(outFile);
		for (String line : lines) {
			out.println(line);
		}
		out.close();
	}

	private static String translate(String googlerese) {
		StringBuffer english = new StringBuffer();
		for (int i = 0; i < googlerese.length(); i++) {
			english.append(mapping.get(googlerese.charAt(i)));
		}
		return english.toString();
	}

	public static void main(String[] args) throws IOException {
		init();
		String[] googlereseLines = readFromFile();
		String[] englishLines = new String[googlereseLines.length];
		for (int i = 0; i < englishLines.length; i++) {
			englishLines[i] = "Case #" + (i + 1) + ": "
					+ translate(googlereseLines[i]);
		}
		writeToFile(englishLines);
	}
}
