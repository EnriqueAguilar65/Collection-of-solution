using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

// https://code.google.com/codejam/contest/6224486/dashboard#s=p1
namespace CodeJam._2015.Qualifications {
	class Dijkstra {

		private static readonly string Yes = "YES";
		private static readonly string No = "NO";
		private static readonly string CorrectSpelling = "ijk";
		private static readonly string[] letters = { "i", "j", "k" };

		private static readonly string[,] quaternions = { { "-", "k", "-j" }, { "-k", "-", "i" }, { "j", "-i", "-" } };
		private static Dictionary<char, int> dic = new Dictionary<char, int>();

		private static readonly string Year = "2015";
		private static readonly string Round = "Qualifications";
		private static readonly string FileName = "C-large";

		private static readonly string ProjectDir = Directory.GetParent(Directory.GetCurrentDirectory()).Parent.FullName + Path.DirectorySeparatorChar;
		private static readonly string DataDir = ProjectDir + "data";
		private static readonly string FilePath = DataDir + Path.DirectorySeparatorChar + Year + Path.DirectorySeparatorChar + Round + Path.DirectorySeparatorChar + FileName;

		private static readonly string InputFile = FilePath + ".in";
		private static readonly string OutputFile = FilePath + ".out";

		public static void Main2() {

			dic.Add('i', 0);
			dic.Add('j', 1);
			dic.Add('k', 2);

			using (TextReader reader = new StreamReader(InputFile))
			using (TextWriter writer = new StreamWriter(OutputFile)) {
				int numTestCases = int.Parse(reader.ReadLine());
				for (int testCaseId = 1; testCaseId <= numTestCases; testCaseId++) {
					string line1 = reader.ReadLine();
					int[] values = Array.ConvertAll(line1.Split(' '), int.Parse);
					string line2 = reader.ReadLine();

					var inputString = new StringBuilder();
					for (var i = 0; i < values[1]; i++) {
						inputString.Append(line2);
					}

					writer.WriteLine("Case #{0}: {1}", testCaseId, solve(inputString.ToString()));
					writer.Flush();
				}
			}
		}

		private static string solve(string mispelling) {
			int numCharsLeft = 3;
			int nextLetter = 0;
			while (mispelling.Length > 0) {
				if (nextLetter < 3 && mispelling.StartsWith(letters[nextLetter])) {
					numCharsLeft--;
					nextLetter++;
					mispelling = mispelling.Substring(1, mispelling.Length - 1);
				} else if (mispelling.StartsWith("--")) {
					mispelling = mispelling.Substring(2, mispelling.Length - 2);
				} else {
					if (mispelling.Length < 2)
						return No;
					if (mispelling.StartsWith("-")) {
						if (mispelling.Length < 3)
							return No;
						mispelling = "-" + quaternions[dic[mispelling[1]], dic[mispelling[2]]]
							+ mispelling.Substring(3, mispelling.Length - 3);
					} else {
						mispelling = quaternions[dic[mispelling[0]], dic[mispelling[1]]]
							+ mispelling.Substring(2, mispelling.Length - 2);
					}

				}
			}

			if (numCharsLeft == 0)
				return Yes;
			return No;
		}
	}
}
