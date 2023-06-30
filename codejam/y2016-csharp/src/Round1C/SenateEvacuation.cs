using System.IO;
using System;
using System.Text;
using System.Linq;

/// https://code.google.com/codejam/contest/4314486/dashboard#s=p0
/// <author>Marco Lackovic</author>
/// Solved in 4 hours :(

namespace CodeJam._2016.Round1C {
	public static class SenateEvacuation {

		private static readonly string Year = "2016";
		private static readonly string Round = "Round1C";
		private static readonly string FileName = "A-small-practice";

		private static readonly string ProjectDir = Directory.GetParent(Directory.GetCurrentDirectory()).Parent.FullName + Path.DirectorySeparatorChar;
		private static readonly string DataDir = ProjectDir + "data";
		private static readonly string FilePath = DataDir + Path.DirectorySeparatorChar + Year + Path.DirectorySeparatorChar + Round + Path.DirectorySeparatorChar + FileName;

		private static readonly string InputFile = FilePath + ".in";
		public static readonly string OutputFile = FilePath + ".out";

		private static char[] letters = new char[26];
		private static int valueOfA = 65;
		private static int[] numSenatorsPerParty = new int[26];
		private static int numTotSenators;
		private static StringBuilder sBuilder;

		public static void Main() {
			using (TextReader reader = new StreamReader(InputFile))
			using (TextWriter writer = new StreamWriter(OutputFile)) {
				int numTestCases = int.Parse(reader.ReadLine());
				for (int testCaseId = 1; testCaseId <= numTestCases; testCaseId++) {
					writer.WriteLine("Case #{0}:{1}", testCaseId, Solve(reader.ReadLine(), reader.ReadLine()));
					writer.Flush();
				}
			}
		}

		// It can never be the case after any evacuation step that more than half 
		// of the senators in the senate room belong to the same party.
		private static string Solve(string line1, string line2) {
			var numParties = int.Parse(line1);
			numSenatorsPerParty = line2.Split(' ').Select(Int32.Parse).ToArray();
			numTotSenators = numSenatorsPerParty.Sum();
			for (int i = 0; i < letters.Length; i++) {
				letters[i] = (char)(valueOfA + i);
			}
			BubbleSort();
			sBuilder = new StringBuilder();
			var lastParty = numSenatorsPerParty.Length - 1;
			while (numTotSenators > 4) {
				for (int i = 0; i < numSenatorsPerParty.Length - 1 && numTotSenators > 4; i++) {
					if (numSenatorsPerParty[i] > numSenatorsPerParty[i + 1]) {
						if (numSenatorsPerParty[i] == 1) {
							Evacuate(i);
						}
						else if (i > 0 && numSenatorsPerParty[i - 1] == numSenatorsPerParty[i]) {
							Evacuate(i - 1, i);
						}
						else {
							Evacuate(i, i);
						}
					}
					else if (numSenatorsPerParty[i + 1] > numSenatorsPerParty[i]) {
						Evacuate(i + 1);
					}
					else if (numSenatorsPerParty[i] > 0) {
						Evacuate(i, i + 1);
					}
					if (!CheckPassed()) {
						throw new Exception();
					}
				}
				if (numSenatorsPerParty[lastParty] > numSenatorsPerParty[lastParty - 1]) {
					Evacuate(lastParty);
				}
			}
			while (numTotSenators > 0) {
				for (int i = 0; i < numSenatorsPerParty.Length - 1; i++) {
					if (numSenatorsPerParty[i] > 0) {
						LastFour(i);
					}
				}
			}
			return sBuilder.ToString();
		}

		private static bool CheckPassed() {
			for (int i = 0; i < numSenatorsPerParty.Length; i++) {
				double percentage = numSenatorsPerParty[i] / (double)numTotSenators;
				if (percentage > 0.5) {
					return false;
				}
			}
			return true;
		}

		private static void LastFour(int current) {
			int other;
			switch (numTotSenators) {
				case 1:
					Evacuate(current);
					break;
				case 2:
					if (numSenatorsPerParty[current] == 2) {
						Evacuate(current, current);
					}
					else {
						other = GetOtherRemainingParty(current);
						if (numSenatorsPerParty[other] == 2) {
							Evacuate(other, other);
						}
						else {
							Evacuate(current, other);
						}
					}
					break;
				case 3:
					other = GetOtherRemainingParty(current);
					if (numSenatorsPerParty[current] > numSenatorsPerParty[other]) {
						Evacuate(current);
					}
					else {
						Evacuate(other);
					}
					break;
				case 4:
					other = GetOtherRemainingParty(current);
					if (numSenatorsPerParty[current] > numSenatorsPerParty[other]) {
						Evacuate(current, current);
					}
					else if (numSenatorsPerParty[other] > numSenatorsPerParty[current]) {
						Evacuate(other, other);
					}
					else if (numSenatorsPerParty[current] == 2) {
						Evacuate(current, other);
					}
					else {
						for (int i = 0; i < numSenatorsPerParty.Length; i++) {
							if (i != current && i != other && numSenatorsPerParty[i] == 2) {
								Evacuate(i, i);
								break;
							}
						}
					}
					break;
			}
		}

		private static void Evacuate(int first, int? second = null) {
			numSenatorsPerParty[first]--;
			numTotSenators--;
			sBuilder.Append(" " + letters[first]);
			if (second != null) {
				numSenatorsPerParty[second.Value]--;
				sBuilder.Append(letters[second.Value]);
				numTotSenators--;
			}
		}

		private static int GetOtherRemainingParty(int except) {
			for (int i = 0; i < numSenatorsPerParty.Length; i++) {
				if (i != except && numSenatorsPerParty[i] > 0) {
					return i;
				}
			}
			return -1;
		}

		public static void BubbleSort() {
			bool swap = true;
			int n = numSenatorsPerParty.Length - 1;
			for (int i = 0; i < n && swap; i++) {
				swap = false;
				for (int j = n; j > i; j--) {
					if (numSenatorsPerParty[j - 1] < numSenatorsPerParty[j]) {
						int tempNum = numSenatorsPerParty[j - 1];
						numSenatorsPerParty[j - 1] = numSenatorsPerParty[j];
						numSenatorsPerParty[j] = tempNum;
						char tempChar = letters[j - 1];
						letters[j - 1] = letters[j];
						letters[j] = tempChar;
						swap = true;
					}
				}
			}
		}
	}
}
