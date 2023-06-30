using System.IO;
using System.Collections.Generic;
using System;
using System.Text;

/// https://code.google.com/codejam/contest/6254486/dashboard#s=p1
/// <author>Marco Lackovic</author>
/// Solved in 60 minutes
// 
//U => FOUR
//W => TWO
//X => SIX
//Z => ZERO
//G => EIGHT
//H => THREE (after removed all EIGHT)
//S => SEVEN (after removed all the SIXes)
//V => FIVE (after removed all the SEVENs)
//O => ONE (after removed all the previous)
//I => NINE

namespace CodeJam._2016.Round1B {
	public static class GettingTheDigits {

		private static Dictionary<string, int[]> CoinJams = new Dictionary<string, int[]>();
		private static HashSet<int> ExtractedNumbers = new HashSet<int>();

		private static Random random = new Random();

		private static readonly string Year = "2016";
		private static readonly string Round = "Round1B";
		private static readonly string FileName = "A-large";

		private static readonly string ProjectDir = Directory.GetParent(Directory.GetCurrentDirectory()).Parent.FullName + Path.DirectorySeparatorChar;
		private static readonly string DataDir = ProjectDir + "data";
		private static readonly string FilePath = DataDir + Path.DirectorySeparatorChar + Year + Path.DirectorySeparatorChar + Round + Path.DirectorySeparatorChar + FileName;

		private static readonly string InputFile = FilePath + ".in";
		public static readonly string OutputFile = FilePath + ".out";

		public static void Main2() {

			using (TextReader reader = new StreamReader(InputFile))
			using (TextWriter writer = new StreamWriter(OutputFile)) {
				int numTestCases = int.Parse(reader.ReadLine());
				for (int testCaseId = 1; testCaseId <= numTestCases; testCaseId++) {
					writer.WriteLine("Case #{0}: {1}", testCaseId, Solve(reader.ReadLine()));
					writer.Flush();
				}
			}
		}

		private static string Solve(string line) {
			char[] chars = line.ToCharArray();
			int[] digits = new int[10];
			digits[4] += chars.ExtractAll('U', "FOR");
			digits[2] += chars.ExtractAll('W', "TO");
			digits[6] += chars.ExtractAll('X', "SI");
			digits[0] += chars.ExtractAll('Z', "ERO");
			digits[8] += chars.ExtractAll('G', "EIHT");
			digits[3] += chars.ExtractAll('H', "TREE");
			digits[7] += chars.ExtractAll('S', "EVEN");
			digits[5] += chars.ExtractAll('V', "FIE");
			digits[1] += chars.ExtractAll('O', "NE");
			digits[9] += chars.ExtractAll('I', "NNE");
			return ComposeNumber(digits);
		}

		private static int ExtractAll(this char[] chars, char uniqueLetter, string otherLetters) {
			int count = 0;
			for (int i = 0; i < chars.Length; i++) {
				if (chars[i] == uniqueLetter) {
					count++;
					chars[i] = '*';
				}
			}
			if (!string.IsNullOrEmpty(otherLetters)) {
				for (int i = 0; i < count; i++) {
					for (int j = 0; j < otherLetters.Length; j++) {
						chars.ExtractOne(otherLetters[j]);
					}
				}
			}
			return count;
		}

		private static void ExtractOne(this char[] chars, char uniqueLetter) {
			for (int i = 0; i < chars.Length; i++) {
				if (chars[i] == uniqueLetter) {
					chars[i] = '*';
					return;
				}
			}
		}

		private static string ComposeNumber(int[] digits) {
			StringBuilder strBuilder = new StringBuilder();
			for (int i = 0; i < digits.Length; i++) {
				for (int j = 0; j < digits[i]; j++) {
					strBuilder.Append(i);
				}
			}
			return strBuilder.ToString();
		}
	}
}
