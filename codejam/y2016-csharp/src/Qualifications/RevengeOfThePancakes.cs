using System.IO;

/// https://code.google.com/codejam/contest/6254486/dashboard#s=p1
/// <author>Marco Lackovic</author>
/// Solved in 35 minutes
namespace CodeJam._2016.Qualifications {
	class RevengeOfThePancakes {

		private static readonly string Year = "2016";
		private static readonly string Round = "Qualifications";
		private static readonly string FileName = "B-large";

		private static readonly string ProjectDir = Directory.GetParent(Directory.GetCurrentDirectory()).Parent.FullName + Path.DirectorySeparatorChar;
		private static readonly string DataDir = ProjectDir + "data";
		private static readonly string FilePath = DataDir + Path.DirectorySeparatorChar + Year + Path.DirectorySeparatorChar + Round + Path.DirectorySeparatorChar + FileName;

		private static readonly string InputFile = FilePath + ".in";
		private static readonly string OutputFile = FilePath + ".out";

		public static void Main2() {

			using (TextReader reader = new StreamReader(InputFile))
			using (TextWriter writer = new StreamWriter(OutputFile)) {
				int numTestCases = int.Parse(reader.ReadLine());
				for (int testCaseId = 1; testCaseId <= numTestCases; testCaseId++) {
					writer.WriteLine("Case #{0}: {1}", testCaseId, solve(reader.ReadLine()));
					writer.Flush();
				}
			}
		}

		private static string solve(string line) {
			int count = 0;
			char lastChar = '+';
			char[] pancakeStack = line.ToCharArray();
			for (int i = pancakeStack.Length - 1; i >= 0; i--) {
				if (pancakeStack[i] != lastChar) {
					count++;
					lastChar = pancakeStack[i];
				}
			}
			return count.ToString();
		}

	}
}
