/*
 * Problem A. Speaking in Tongues
 * 
 * We have come up with the best possible language here at Google, called
 * Googlerese. To translate text into Googlerese, we take any message and
 * replace each English letter with another English letter. This mapping is
 * one-to-one and onto, which means that the same input letter always gets
 * replaced with the same output letter, and different input letters always get
 * replaced with different output letters. A letter may be replaced by itself.
 * Spaces are left as-is.

 For example (and here is a hint!), our awesome translation algorithm includes the following three mappings: 'a' -> 'y', 'o' -> 'e', and 'z' -> 'q'. This means that "a zoo" will become "y qee".

 Googlerese is based on the best possible replacement mapping, and we will never change it. It will always be the same. In every test case. We will not tell you the rest of our mapping because that would make the problem too easy, but there are a few examples below that may help.

 Given some text in Googlerese, can you translate it to back to normal text?

 Solving this problem

 Usually, Google Code Jam problems have 1 Small input and 1 Large input. This problem has only 1 Small input. Once you have solved the Small input, you have finished solving this problem.

 Input

 The first line of the input gives the number of test cases, T. T test cases follow, one per line.

 Each line consists of a string G in Googlerese, made up of one or more words containing the letters 'a' - 'z'. There will be exactly one space (' ') character between consecutive words and no spaces at the beginning or at the end of any line.

 Output

 For each test case, output one line containing "Case #X: S" where X is the case number and S is the string that becomes G in Googlerese.

 Limits

 1 ≤ T ≤ 30.
 G contains at most 100 characters.
 None of the text is guaranteed to be valid English.
 Sample

 Input
 3
 ejp mysljylc kd kxveddknmc re jsicpdrysi
 rbcpc ypc rtcsra dkh wyfrepkym veddknkmkrkcd
 de kr kd eoya kw aej tysr re ujdr lkgc jv


 Output
 Case #1: our language is impossible to understand
 Case #2: there are twenty six factorial possibilities
 Case #3: so it is okay if you want to just give up
 */
package codejam.y2012.qualifications.krige;


import java.io.IOException;
import java.util.HashMap;

import codejam.krige.commons.Utils;

/**
 * @author Marco Lackovic <marco.lackovic@gmail.com>
 * @version 1.1, Apr 14, 2012
 */
public class SpeakinginTongues {
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

	private static String translate(String googlerese) {
		StringBuffer english = new StringBuffer();
		for (int i = 0; i < googlerese.length(); i++) {
			english.append(mapping.get(googlerese.charAt(i)));
		}
		return english.toString();
	}

	public static void main(String[] args) throws IOException {
		init();
		String[] googlereseLines = Utils.readFromFile(INPUT_FILE_NAME);
		String[] englishLines = new String[googlereseLines.length];
		for (int i = 0; i < englishLines.length; i++) {
			englishLines[i] = "Case #" + (i + 1) + ": "
					+ translate(googlereseLines[i]);
		}
		Utils.writeToFile(englishLines, OUTPUT_FILE_NAME);
	}
}
