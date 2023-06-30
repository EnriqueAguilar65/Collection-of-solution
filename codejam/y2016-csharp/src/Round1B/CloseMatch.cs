using System.IO;
using System.Collections.Generic;
using System;
using System.Text;
using System.Linq;

/// https://code.google.com/codejam/contest/6254486/dashboard#s=p1
/// <author>Marco Lackovic</author>
/// Solved in 90 minutes

namespace CodeJam._2016.Round1B {
	public static class CloseMatch {

		private static Dictionary<string, int[]> CoinJams = new Dictionary<string, int[]>();
		private static HashSet<int> ExtractedNumbers = new HashSet<int>();

		private static Random random = new Random();

		private static readonly string Year = "2016";
		private static readonly string Round = "Round1B";
		private static readonly string FileName = "B-small-practice";

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
			string[] scores = line.Split(' ');
			char[] codersScore = scores[0].ToCharArray();
			char[] jammersScore = scores[1].ToCharArray();
			for (int i = codersScore.Length - 1; i >= 0; i--) {
				if (codersScore[i] == '?' || jammersScore[i] == '?') {
					if (i - 1 < 0 || codersScore[i - 1] == jammersScore[i - 1]) {
						if (i == codersScore.Length - 1) {
							if (codersScore[i] == '?') {
								if (jammersScore[i] == '?') {
									codersScore[i] = '0';
									jammersScore[i] = '0';
								}
								else {
									codersScore[i] = jammersScore[i];
								}
							}
							else {
								jammersScore[i] = codersScore[i];
							}
						}
						else {
							int difference = (int)(char.GetNumericValue(codersScore[i + 1]) - char.GetNumericValue(jammersScore[i + 1]));
							if (difference >= 5) {
								if (codersScore[i] == '?') {
									if (jammersScore[i] == '?') {
										codersScore[i] = '0';
										jammersScore[i] = '1';
									}
									else {
										codersScore[i] = jammersScore[i].Smaller();
									}
								}
								else {
									jammersScore[i] = codersScore[i].Bigger();
								}
							}
							else if (difference >= -5) {
								if (codersScore[i] == '?') {
									if (jammersScore[i] == '?') {
										codersScore[i] = '0';
										jammersScore[i] = '0';
									}
									else {
										codersScore[i] = ((int)(char.GetNumericValue(jammersScore[i]))).ToChar();
									}
								}
								else {
									jammersScore[i] = ((int)(char.GetNumericValue(codersScore[i]))).ToChar();
								}
							}
							else {
								if (codersScore[i] == '?') {
									if (jammersScore[i] == '?') {
										codersScore[i] = '1';
										jammersScore[i] = '0';
									}
									else {
										codersScore[i] = jammersScore[i].Bigger();
									}
								}
								else {
									jammersScore[i] = codersScore[i].Smaller();
								}
							}
						}
					}
					else {
						int difference = (int)(char.GetNumericValue(codersScore[i - 1]) - char.GetNumericValue(jammersScore[i - 1]));
						if (difference > 0) {
							if (codersScore[i] == '?') {
								if (jammersScore[i] == '?') {
									codersScore[i] = '0';
									jammersScore[i] = '9';
								}
								else {
									codersScore[i] = '0';
								}
							}
							else {
								jammersScore[i] = '9';
							}

						}
						else {
							if (codersScore[i] == '?') {
								if (jammersScore[i] == '?') {
									codersScore[i] = '9';
									jammersScore[i] = '0';
								}
								else {
									codersScore[i] = '9';
								}
							}
							else {
								jammersScore[i] = '0';
							}
						}
					}
				}
			}
			return new string(codersScore) + " " + new string(jammersScore);
		}

		private static char ToChar(this int num) {
			return num.ToString().ToCharArray()[0];
		}

		private static char Smaller(this char c) {
			int n = ((int)(char.GetNumericValue(c) - 1));
			if (n < 0) {
				n = 0;
			}
			return n.ToChar();
		}

		private static char Bigger(this char c) {
			int n = ((int)(char.GetNumericValue(c) + 1));
			if (n == 10) {
				n = 9;
			}
			return n.ToChar();
		}
	}
}
