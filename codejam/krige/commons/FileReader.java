package codejam.krige.commons;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class FileReader {
	public static String[] readLines(File f) {
		String[] lines = null;
		try {
			FileInputStream fstream = new FileInputStream("textfile.txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine = br.readLine();
			lines = new String[Integer.valueOf(strLine)];
			int i = 0;
			while ((strLine = br.readLine()) != null) {
				lines[i++] = strLine;
			}
			in.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
		return lines;
	}
}
