package codejam.y2013.qualifications.krige.d;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Problem
 * 
 * Following an old map, you have stumbled upon the Dread Pirate Larry's secret
 * treasure trove!
 * 
 * The treasure trove consists of N locked chests, each of which can only be
 * opened by a key of a specific type. Furthermore, once a key is used to open a
 * chest, it can never be used again. Inside every chest, you will of course
 * find lots of treasure, and you might also find one or more keys that you can
 * use to open other chests. A chest may contain multiple keys of the same type,
 * and you may hold any number of keys.
 * 
 * You already have at least one key and your map says what other keys can be
 * found inside the various chests. With all this information, can you figure
 * out how to unlock all the chests?
 * 
 * For example, suppose the treasure trove consists of four chests as described
 * below, and you began with exactly one key of type 1:
 * 
 * Chest Number | Key Type To Open Chest | Key Types Inside
 * --------------+--------------------------+------------------ 1 | 1 | None 2 |
 * 1 | 1, 3 3 | 2 | None 4 | 3 | 2
 * 
 * You can open all the chests in this example if you do them in the order 2, 1,
 * 4, 3. If you start by opening chest #1 first, then you will have used up your
 * only key, and you will be stuck.
 * 
 * Input
 * 
 * The first line of the input gives the number of test cases, T. T test cases
 * follow. Each test case begins with a single line containing two positive
 * integers K and N, representing the number of keys you start with and the
 * number of chests you need to open.
 * 
 * This is followed by a line containing K integers, representing the types of
 * the keys that you start with.
 * 
 * After that, there will be N lines, each representing a single chest. Each
 * line will begin with integers Ti and Ki, indicating the key type needed to
 * open the chest and the number of keys inside the chest. These two integers
 * will be followed by Ki more integers, indicating the types of the keys
 * contained within the chest.
 * 
 * Output
 * 
 * For each test case, output one line containing "Case #x: C1 C2 ... CN", where
 * x is the case number (starting from 1), and where Ci represents the index
 * (starting from 1) of the ith chest that you should open.
 * 
 * If there are multiple ways of opening all the chests, choose the
 * "lexicographically smallest" way. In other words, you should choose to make
 * C1 as small as possible, and if there are multiple ways of making C1 as small
 * as possible, choose the one that makes C2 as small as possible, and so on.
 * 
 * If there is no way to open all the chests, you should instead output one line
 * containing "Case #x: IMPOSSIBLE".
 * 
 * Limits
 * 
 * 1 ≤ T ≤ 25. 1 ≤ K. All key types will be integers between 1 and 200
 * inclusive.
 * 
 * Small dataset
 * 
 * 1 ≤ N ≤ 20. In each test case, there will be at most 40 keys altogether.
 * 
 * Large dataset
 * 
 * 1 ≤ N ≤ 200. In each test case, there will be at most 400 keys altogether.
 * 
 * @author Marco Lackovic <marco.lackovic@gmail.com>
 * @version 2.0, April 20, 2013
 */
public class Treasure {

    private static final String FILE_NAME = "D-large-practice";

    private static final String INPUT_FILE_NAME = FILE_NAME + ".in";
    private static final String OUTPUT_FILE_NAME = FILE_NAME + ".out";

    private static final String ROOT_DIR = "dat";
    private static final String YEAR = "2013";
    private static final String ROUND = "qualifications";

    private static final String PATH = ROOT_DIR + File.separator + YEAR
            + File.separator + ROUND + File.separator;

    private static class Chest {
        int id;
        int lock;
        ArrayList<Integer> keys;
    }

    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(new BufferedInputStream(
                new FileInputStream(PATH + INPUT_FILE_NAME)));
        PrintWriter output = new PrintWriter(new FileWriter(PATH
                + OUTPUT_FILE_NAME));

        int numTestCases = input.nextInt();

        for (int tc = 1; tc <= numTestCases; tc++) {
            int numStartingKeys = input.nextInt();
            int numChests = input.nextInt();
            if (tc == 9) {
                System.out.println(numStartingKeys + " " + numChests);
            }
            ArrayList<Integer> keys = new ArrayList<>(numStartingKeys);
            for (int i = 0; i < numStartingKeys; i++) {
                keys.add(input.nextInt());
            }
            ArrayList<Chest> chests = new ArrayList<>(numChests);
            for (int i = 0; i < numChests; i++) {
                Chest chest = new Chest();
                chest.id = i + 1;
                chest.lock = input.nextInt();
                int numKeys = input.nextInt();
                chest.keys = new ArrayList<Integer>(numKeys);
                for (int j = 0; j < numKeys; j++) {
                    chest.keys.add(input.nextInt());
                }
                chests.add(chest);
            }
            ArrayList<Integer> solution;
            if (enoughKeys(keys, chests)) {
                solution = solve(keys, chests);
            } else {
                solution = null;
            }
            String outputLine;
            if (solution == null) {
                outputLine = "Case #" + tc + ": IMPOSSIBLE";
            } else {
                Collections.reverse(solution);
                StringBuilder sb = new StringBuilder();
                for (Integer i : solution) {
                    sb.append(" " + i);
                }
                outputLine = "Case #" + tc + ":" + sb;
            }
            System.out.println(outputLine);
            output.println(outputLine);
        }
        input.close();
        output.close();
    }

    private static boolean enoughKeys(final ArrayList<Integer> keys,
            final ArrayList<Chest> chests) {
        int[] keyTypes = new int[201];
        for (int k : keys) {
            keyTypes[k]++;
        }
        for (Chest chest : chests) {
            for (int k : chest.keys) {
                keyTypes[k]++;
            }
        }
        for (Chest chest : chests) {
            keyTypes[chest.lock]--;
            if (keyTypes[chest.lock] == -1) {
                return false;
            }
        }
        return true;
    }

    private static ArrayList<Integer> solve(final ArrayList<Integer> keys,
            final ArrayList<Chest> chests) {

        for (Chest chest : chests) {
            int id = keys.indexOf(chest.lock);
            if (id == -1) {
                if (!isKeyInOtherChests(chest, chests)) {
                    return null;
                }
                continue;
            }
            if (chests.size() == 1) {
                ArrayList<Integer> solution = new ArrayList<>();
                solution.add(chests.get(0).id);
                return solution;
            }
            ArrayList<Integer> newKeys = new ArrayList<>(keys);
            newKeys.remove(id);
            if (!chest.keys.isEmpty()) {
                newKeys.addAll(new ArrayList<>(chest.keys));
            }
            ArrayList<Chest> newChests = new ArrayList<>(chests);
            newChests.remove(chest);
            boolean possible = true;
            for (Chest chest2 : newChests) {
                if (!newKeys.contains(chest2.lock)
                        && !isKeyInOtherChests(chest2, newChests)) {
                    possible = false;
                    break;
                }
            }
            if (possible) {
                ArrayList<Integer> solution = solve(newKeys, newChests);
                if (solution != null) {
                    solution.add(chest.id);
                    return solution;
                }
            }
        }
        return null;
    }

    private static boolean isKeyInOtherChests(Chest chest,
            ArrayList<Chest> chests) {
        for (Chest otherChest : chests) {
            if (otherChest.id != chest.id) {
                if (otherChest.keys.contains(chest.lock)) {
                    return true;
                }
            }
        }
        return false;
    }
}
