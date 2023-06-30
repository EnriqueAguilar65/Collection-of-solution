package codejam.y2013.round1c.krige.b;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Problem
 * 
 * You are studying the history of the Great Wall of China, which was built by
 * the Chinese to protect against military incursions from the North. For the
 * purposes of this problem, the Great Wall stretches from infinity in the East
 * to minus infinity in the West. As this is a lot of distance to cover, the
 * Great Wall was not built at once. Instead, for this problem we assume that
 * the builder used a reactive strategy: whenever a part of the border was
 * attacked successfully, the Wall on this part of the border would be raised to
 * the height sufficient to stop an identical attack in the future.
 * 
 * The north border of China was frequently attacked by nomadic tribes. For the
 * purposes of this problem, we assume that each tribe attacks the border on
 * some interval with some strength S. In order to repel the attack, the Wall
 * must have height S all along the defended interval. If even a short stretch
 * of the Wall is lower than needed, the attack will breach the Wall at this
 * point and succeed. Note that even a successful attack does not damage the
 * Wall. After the attack, every attacked fragment of the Wall that was lower
 * than S is raised to height S — in other words, the Wall is increased in the
 * minimal way that would have stopped the attack. Note that if two or more
 * attacks happened on the exact same day, the Wall was raised only after they
 * all resolved, and is raised in the minimum way that would stop all of them.
 * 
 * Since nomadic tribes are nomadic, they did not necessarily restrict
 * themselves to a single attack. Instead, they tended to move (either to the
 * East or to the West), and periodically attack the Wall. To simplify the
 * problem, we assume they moved with constant speed and attacked the Wall at
 * constant intervals; moreover we assume that the strength with which a given
 * tribe attacked the Wall changed by a constant amount after each attack
 * (either decreased from attrition, or grew from experience).
 * 
 * Assuming that initially (in 250 BC) the Wall was nonexistent (i.e., of height
 * zero everywhere), and given the full description of all the nomadic tribes
 * that attacked the Wall, determine how many of the attacks were successful.
 * 
 * Input
 * 
 * The first line of the input gives the number of test cases, T. T test cases
 * follow. Each test case begins with a line containing a single integer N: the
 * number of the tribes attacking the Wall. N lines follow, each describing one
 * tribe. The ith line contains eight integers di, ni, wi, ei, si, delta_di,
 * delta_pi and delta_si separated by spaces, describing a single nomadic tribe:
 * 
 * di – the day of the tribe's first attack (where 1st January, 250BC, is
 * considered day 0) ni – the number of attacks from this tribe wi, ei – the
 * westmost and eastmost points respectively of the Wall attacked on the first
 * attack si – the strength of the first attack delta_di – the number of days
 * between subsequent attacks by this tribe delta_pi – the distance this tribe
 * travels to the east between subsequent attacks (if this is negative, the
 * tribe travels to the west) delta_si – the change in strength between
 * subsequent attacks Output
 * 
 * For each test case, output one line containing "Case #x: y", where x is the
 * case number (starting from 1) and y is the number of attacks that succeed.
 * 
 * Limits
 * 
 * 1 ≤ T ≤ 20. 0 ≤ di. 1 ≤ delta_di ≤ 676060. di + (ni - 1) * delta_di ≤ 676060.
 * 1 ≤ si ≤ 106. -105 ≤ delta_si ≤ 105. si + (ni - 1) * delta_si ≥ 1.
 * 
 * Small dataset
 * 
 * 1 ≤ N ≤ 10. 1 ≤ ni ≤ 10. -100 ≤ wi < ei ≤ 100. -10 ≤ delta_pi ≤ 10.
 * 
 * Large dataset
 * 
 * 1 ≤ N ≤ 1000. 1 ≤ ni ≤ 1000. -106 ≤ wi < ei ≤ 106. -105 ≤ delta_pi ≤ 105.
 * Sample
 * 
 * UNDER CONSTRUCTION
 * 
 */
public class Pogo {

	private static final String FILE_NAME = "B-sample";

	private static final String INPUT_FILE_NAME = FILE_NAME + ".in";
	private static final String OUTPUT_FILE_NAME = FILE_NAME + ".out";

	private static final String ROOT_DIR = "dat";
	private static final String YEAR = "2013";
	private static final String ROUND = "round1b";

	private static final String PATH = ROOT_DIR + File.separator + YEAR
			+ File.separator + ROUND + File.separator;

	public static void main(String[] args) throws IOException {
		Scanner input = new Scanner(new BufferedInputStream(
				new FileInputStream(PATH + INPUT_FILE_NAME)));
		PrintWriter output = new PrintWriter(new FileWriter(PATH
				+ OUTPUT_FILE_NAME));

		int numTestCases = input.nextInt();

		for (int tc = 1; tc <= numTestCases; tc++) {
			long aMote = input.nextLong();
			int numMotes = input.nextInt();
			long[] motes = new long[numMotes];
			for (int i = 0; i < numMotes; i++) {
				motes[i] = input.nextLong();
			}
			Arrays.sort(motes);
			String outputLine = "Case #" + tc + ": " + solve(aMote, motes);
			System.out.println(outputLine);
			output.println(outputLine);
		}
		input.close();
		output.close();
	}

	private static long solve(long aMote, long[] motes) {
		if (aMote == 1) {
			return motes.length;
		}
		long solution1 = solve1(aMote, motes);
		long solution2 = solve2(aMote, motes);
		return solution1 < solution2 ? solution1 : solution2;
	}

	private static long solve1(long aMote, long[] motes) {
		long numOperations = 0;
		for (int i = 0; i < motes.length; i++) {
			if (motes[i] < aMote) {
				aMote += motes[i];
			} else {
				long extraMote = aMote - 1;
				if (motes[i] < aMote + extraMote) {
					aMote += extraMote + motes[i];
				} else {
					numOperations += motes.length - i;
					break;
				}
				numOperations++;
			}
		}
		return numOperations;
	}

	private static long solve2(long aMote, long[] motes) {
		long numOperations = 0;
		long[] jollies = new long[motes.length];
		for (int i = 0; i < motes.length; i++) {
			if (motes[i] < aMote) {
				aMote += motes[i];
				jollies[i] = aMote - 1;
			} else {
				long extraMote = aMote - 1;
				if (motes[i] < aMote + extraMote) {
					aMote += extraMote + motes[i];
				} else {
					boolean found = false;
					int numOpToRemove = 0;
					int extraMoteTemp = 0;
					for (int j = i - 1; j >= 0; j--) {
						if (jollies[j] > 0) {
							extraMoteTemp += jollies[j];
							if (motes[i] < extraMoteTemp + aMote) {
								// go back to that position and use the jolly
								i = j;
								numOperations -= numOpToRemove;
								found = true;
								break;
							}
						} else {
							numOpToRemove++;
						}
					}
					if (!found) {
						numOperations += motes.length - i;
						break;
					}
				}
				numOperations++;
			}
		}
		return numOperations;
	}
}
