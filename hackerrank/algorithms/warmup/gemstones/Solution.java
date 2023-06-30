package hackerrank.algorithms.warmup.gemstones;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * John has discovered various rocks. Each rock is composed of various elements,
 * and each element is represented by a lowercase latin letter from 'a' to 'z'.
 * An element can be present multiple times in a rock. An element is called a
 * 'gem-element' if it occurs at least once in each of the rocks.
 * 
 * Given the list of rocks with their compositions, display the number of
 * gem-elements that exist in those rocks.
 * 
 * Input Format: The first line consists of N, the number of rocks. Each of the
 * next N lines contain rocks' composition. Each composition consists of
 * lowercase letters of English alphabet.
 * 
 * Output Format: Print the number of gem-elements that are common in these
 * rocks.
 * 
 * Constraints: 1 <= N <= 100 Each composition consists of only small latin
 * letters ('a'-'z'). 1 <= Length of each composition <= 100
 * 
 * @author Marco Lackovic <marco.lackovic@gmail.com>
 * @version 1.0, 29/aug/2014
 *
 */
public class Solution {

    private static String[] rocks;

    public static void main(String[] args) {

        // BufferedInputStream file = new BufferedInputStream(new
        // FileInputStream(
        // "dat/hackerrank/gemstones.in"));
        // System.setIn(file);

        try (Scanner in = new Scanner(System.in)) {
            int numRocks = in.nextInt();
            in.nextLine();
            rocks = new String[numRocks];
            for (int i = 0; i < rocks.length; i++) {
                rocks[i] = in.nextLine();
            }
            System.out.println(computeNumGemElements());
        }
    }

    private static int computeNumGemElements() {
        int numGemElements = 0;
        char[] firstRock = rocks[0].toCharArray();
        LinkedList<Character> candidates = new LinkedList<>();
        for (int i = 0; i < firstRock.length; i++) {
            if (!candidates.contains(firstRock[i])) {
                candidates.add(firstRock[i]);
            }
        }
        for (Character c : candidates) {
            if (isPresentInAllRocks(c)) {
                numGemElements++;
            }
        }
        return numGemElements;
    }

    private static boolean isPresentInAllRocks(char c) {
        for (int i = 1; i < rocks.length; i++) {
            if (rocks[i].indexOf(c) == -1) {
                return false;
            }
        }
        return true;
    }
}
